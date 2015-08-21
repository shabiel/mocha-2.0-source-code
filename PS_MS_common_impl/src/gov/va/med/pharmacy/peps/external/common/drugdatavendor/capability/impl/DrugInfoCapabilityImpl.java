package gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.impl;


import java.util.ArrayList;
import java.util.Collection;

import gov.va.med.pharmacy.peps.common.vo.DoseRouteVo;
import gov.va.med.pharmacy.peps.common.vo.DoseTypeVo;
import gov.va.med.pharmacy.peps.common.vo.DrugInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DrugInfoVo;
import gov.va.med.pharmacy.peps.external.common.drugdatavendor.capability.DrugInfoCapability;

import firstdatabank.database.FDBDataManager;
import firstdatabank.database.FDBException;
import firstdatabank.dif.DispensableDrug;
import firstdatabank.dif.DoseRoutes;
import firstdatabank.dif.DoseTypes;
import firstdatabank.dif.FDBDispensableDrugLoadType;
import firstdatabank.dif.FDBDrugType;
import firstdatabank.dif.ScreenDrug;


/**
 * Lookup the dose routes and types for the given drugs.
 */
public class DrugInfoCapabilityImpl implements DrugInfoCapability {
    private FDBDataManager fdbDataManager;

    /**
     * Lookup the dose routes and types for the given drugs.
     * 
     * @param drugs Collection of DrugInfoVo
     * @return DrugInfoResultsVo containing drugs not found and drugs with does routes and types
     */
    public final DrugInfoResultsVo processDrugInfoRequest(Collection<DrugInfoVo> drugs) {
        DrugInfoResultsVo results = new DrugInfoResultsVo();

        for (DrugInfoVo drugInfoVo : drugs) {
            try {
                synchronized (firstdatabank.dif.GlobalObjectManager.class) { // FDB locking contention
                    ScreenDrug screenDrug = newScreenDrugInstance();

                    screenDrug.load(drugInfoVo.getGcnSeqNo(), FDBDrugType.fdbDTGCNSeqNo);

                    drugInfoVo.setDoseRoutes(convertDoseRoutes(screenDrug.getDoseRoutes()));
                    drugInfoVo.setDoseTypes(convertDoseTypes(screenDrug.getDoseTypes()));

                    DispensableDrug dispensableDrug = newDispensableDrugInstance();
                    dispensableDrug.load(Long.valueOf(drugInfoVo.getGcnSeqNo()), FDBDispensableDrugLoadType.fdbDDLTGCNSeqNo,
                        "", "", "");

                    // Strength Units are lower case, but data from database in dose checks are upper case
                    String strengthUnit = dispensableDrug.getStrengthUnit();

                    if (strengthUnit != null) {
                        strengthUnit = strengthUnit.toUpperCase();
                    }

                    drugInfoVo.setStrengthUnit(strengthUnit);
                }

                results.getDrugs().add(drugInfoVo);
            }
            catch (FDBException e) {
                results.getDrugsNotFound().add(drugInfoVo);
            }
        }

        return results;
    }

    /**
     * Convert the FDB DoseTypes type to a collection of strings, representing the description of types.
     * 
     * @param doseTypes DoseTypes FDB object
     * @return Collection of DoseTypeVo each the description of a FDB DoseRoute
     */
    private Collection<DoseTypeVo> convertDoseTypes(DoseTypes doseTypes) {
        Collection<DoseTypeVo> types = new ArrayList<DoseTypeVo>(doseTypes.count());

        for (int i = 0; i < doseTypes.count(); i++) {
            DoseTypeVo doseType = new DoseTypeVo();
            doseType.setId(doseTypes.item(i).getID());
            doseType.setName(doseTypes.item(i).getDescription());
            types.add(doseType);
        }

        return types;
    }

    /**
     * Convert the FDB DoseRoutes type to a collection of strings, representing the description of routes.
     * 
     * @param doseRoutes DoseRoutes FDB object
     * @return Collection of DoseRouteVo
     */
    private Collection<DoseRouteVo> convertDoseRoutes(DoseRoutes doseRoutes) {
        Collection<DoseRouteVo> routes = new ArrayList<DoseRouteVo>(doseRoutes.count());

        for (int i = 0; i < doseRoutes.count(); i++) {
            DoseRouteVo doseRoute = new DoseRouteVo();
            doseRoute.setId(doseRoutes.item(i).getID());
            doseRoute.setName(doseRoutes.item(i).getDescription());
            routes.add(doseRoute);
        }

        return routes;
    }

    /**
     * Provide a new instance of ScreenDrug.
     * 
     * @return new instance of ScreenDrug
     */
    protected final ScreenDrug newScreenDrugInstance() {
        return new ScreenDrug(fdbDataManager);
    }

    /**
     * Provide a new instance of DispensableDrug.
     * 
     * @return new instance of DispensableDrug
     */
    protected final DispensableDrug newDispensableDrugInstance() {
        return new DispensableDrug(fdbDataManager);
    }

    /**
     * Sets the FDB Data Manager to use.
     * 
     * @param fdbDataManager fdbDataManager property
     */
    public final void setFdbDataManager(FDBDataManager fdbDataManager) {
        this.fdbDataManager = fdbDataManager;
    }
}
