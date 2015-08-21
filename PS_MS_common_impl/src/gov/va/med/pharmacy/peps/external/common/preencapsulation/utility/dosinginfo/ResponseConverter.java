package gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.dosinginfo;

import gov.va.med.pharmacy.peps.common.vo.DoseRangeResultVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoResultsVo;
import gov.va.med.pharmacy.peps.common.vo.DosingInfoVo;
import gov.va.med.pharmacy.peps.common.vo.DosingNotFoundVo;
import gov.va.med.pharmacy.peps.common.vo.MinMaxResultVo;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.DoseRange;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.DoseRanges;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.DosingInfo;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.DosingInfoResponse;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.DosingNotFound;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.MinMax;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.MinMaxResults;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.NeonatalDoseRanges;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.NotFound;
import gov.va.med.pharmacy.peps.external.common.preencapsulation.vo.dosing.info.response.ObjectFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Converts a VO into an XML response.
 */
public final class ResponseConverter {

    private static final Integer ENGLISH_LANGUAGE_ID = 1;

    /**
     * Marshal the given DosingInfoResultsVo into an XML message.
     * 
     * @param dosingInfoResultsVo
     *            DosingInfoResultsVo to convert into XML
     * @return String XML message
     */
    public static Object toDosingInfoResponse(DosingInfoResultsVo dosingInfoResultsVo) {
        ObjectFactory objectFactory = new ObjectFactory();
        DosingInfoResponse response = objectFactory.createDosingInfoResponse();

        response.getDosingInfo().addAll(convertDosing(dosingInfoResultsVo.getDosingInfo(), objectFactory));
        response.setDosingNotFound(convertDosingNotFound(dosingInfoResultsVo.getDosingNotFound(), objectFactory));

        return response;
    }

    /**
     * Convert the dosing found in FDB to JAXB Drug objects.
     * 
     * @param dosing
     *            Collection of DosingInfoVo
     * @param objectFactory
     *            JAXB ObjectFactory to create new instances of Dosing
     * @return Collection of JAXB Dosing XML objects
     */
    private static Collection<DosingInfo> convertDosing(Collection<DosingInfoVo> dosing, ObjectFactory objectFactory) {
        Collection<DosingInfo> results = new ArrayList<DosingInfo>(dosing.size());

        for (DosingInfoVo dosingInfoVo : dosing) {
            DosingInfo dosingInfo = objectFactory.createDosingInfo();
            dosingInfo.setGcnSeqNo(dosingInfoVo.getGcnSeqNo());
            dosingInfo.setFdbdx(dosingInfoVo.getFdbdx());
            dosingInfo.setDispensableDrugName(dosingInfoVo.getDispensableDrugName());
            dosingInfo.setDispensableDrugDescription(dosingInfoVo.getDispensableDrugDescription());

            if (dosingInfoVo.getDoseRangeResults().size() > 0) {
                DoseRanges doseRanges = objectFactory.createDoseRanges();
                dosingInfo.getDoseRanges().add(doseRanges);

                for (DoseRangeResultVo resultVo : dosingInfoVo.getDoseRangeResults()) {

                    doseRanges.getDoseRange().add(convertDoseRange(resultVo, objectFactory));
                }
            }

            if (dosingInfoVo.getMinMaxResults().size() > 0) {
                MinMaxResults minMaxResults = objectFactory.createMinMaxResults();
                dosingInfo.getMinMaxResults().add(minMaxResults);
                for (MinMaxResultVo mmResultVo : dosingInfoVo.getMinMaxResults()) {
                    minMaxResults.getMinMax().add(convertMinMax(mmResultVo, objectFactory));
                }
            }

            if (dosingInfoVo.getNeonatalDoseRangeResults().size() > 0) {
                NeonatalDoseRanges neonatalResults = objectFactory.createNeonatalDoseRanges();
                dosingInfo.getNeonatalDoseRanges().add(neonatalResults);
                for (DoseRangeResultVo resultVo : dosingInfoVo.getNeonatalDoseRangeResults()) {
                    neonatalResults.getDoseRange().add(convertDoseRange(resultVo, objectFactory));
                }
            }

            results.add(dosingInfo);
        }

        return results;
    }

    /**
     * Converts a DoseRangeResultVo object in to a DoseRange object.
     * 
     * @param resultVo
     *            DoseRangeResultVo object.
     * @param objectFactory
     *            the ObjectFactory used to create the DoseRange object.
     * @return DoseRange
     */
    private static DoseRange convertDoseRange(DoseRangeResultVo resultVo, ObjectFactory objectFactory) {
        DoseRange doseRange = objectFactory.createDoseRange();
        doseRange.setCustom(resultVo.getCustom());
        doseRange.setAgeHighInDays(resultVo.getAgeHighInDays());
        doseRange.setAgeLowInDays(resultVo.getAgeLowInDays());
        doseRange.setBsaRequired(resultVo.getBsaRequired());
        doseRange.setCategory(resultVo.getCategory());
        doseRange.setCrclThreshold(BigInteger.valueOf(resultVo.getCrclThreshold().getValue()));
        doseRange.setCrclThresholdUnit(resultVo.getCrclThreshold().getUnit());
        doseRange.setDoseFormHigh(BigDecimal.valueOf(resultVo.getDoseFormHigh().getValue()));
        doseRange.setDoseFormHighUnit(resultVo.getDoseFormHigh().getUnit());
        doseRange.setDoseFormLow(BigDecimal.valueOf(resultVo.getDoseFormLow().getValue()));
        doseRange.setDoseFormLowUnit(resultVo.getDoseFormLow().getUnit());
        doseRange.setDoseHigh(BigDecimal.valueOf(resultVo.getDoseHigh().getValue()));
        doseRange.setDoseHighUnit(resultVo.getDoseHigh().getUnit());
        doseRange.setDoseLow(BigDecimal.valueOf(resultVo.getDoseLow().getValue()));
        doseRange.setDoseLowUnit(resultVo.getDoseLow().getUnit());
        doseRange.setDoseRouteDescription(resultVo.getDoseRouteDescription());
        doseRange.setDoseRouteId(resultVo.getDoseRouteId());
        doseRange.setDoseTypeDescription(resultVo.getDoseTypeDescription());
        doseRange.setDoseTypeId(resultVo.getDoseTypeId());
        doseRange.setDurationHigh(resultVo.getDurationHigh());
        doseRange.setDurationLow(resultVo.getDurationLow());
        doseRange.setFrequencyHigh(BigDecimal.valueOf(resultVo.getFrequencyHigh()));
        doseRange.setFrequencyLow(BigDecimal.valueOf(resultVo.getFrequencyLow()));
        doseRange.setHalfLifeUnit(resultVo.getHalfLifeUnit());
        doseRange.setHepaticImpairment(resultVo.getHepaticImpairment());
        doseRange.setHighEliminationHalfLife(BigDecimal.valueOf(resultVo.getHighEliminationHalfLife()));
        doseRange.setHitIndicationDescription(resultVo.getHitIndicationDescription());
        doseRange.setHitIndicationId(resultVo.getHitIndicationId().toString());
        doseRange.setIndicationDescription(resultVo.getIndicationDescription());
        doseRange.setIndicationId(resultVo.getIndicationId());
        doseRange.setIndicationIdType(resultVo.getIndicationIdType().toString());
        doseRange.setIntlDoseRouteDescription(resultVo.getIntlDoseRouteDescription().getItem(ENGLISH_LANGUAGE_ID));
        doseRange.setIntlDoseTypeDescription(resultVo.getIntlDoseTypeDescription().getItem(ENGLISH_LANGUAGE_ID));
        doseRange.setHitIndicationDescription(resultVo.getIntlHitIndicationDescription().getItem(ENGLISH_LANGUAGE_ID));
        doseRange.setLowEliminationHalfLife(BigDecimal.valueOf(resultVo.getLowEliminationHalfLife()));
        doseRange.setMaxDailyDose(BigDecimal.valueOf(resultVo.getMaxDailyDose().getValue()));
        doseRange.setMaxDailyDoseUnit(resultVo.getMaxDailyDose().getUnit());
        doseRange.setMaxDailyDoseForm(BigDecimal.valueOf(resultVo.getMaxDailyDoseForm().getValue()));
        doseRange.setMaxDailyDoseFormUnit(resultVo.getMaxDailyDoseForm().getUnit());
        doseRange.setMaxDuration(resultVo.getMaxDuration());
        doseRange.setMaxLifetimeDose(BigDecimal.valueOf(resultVo.getMaxLifetimeDose().getValue()));
        doseRange.setMaxLifetimeDoseUnit(resultVo.getMaxLifetimeDose().getUnit());
        doseRange.setMaxLifetimeDoseForm(BigDecimal.valueOf(resultVo.getMaxLifetimeDoseForm().getValue()));
        doseRange.setMaxLifetimeDoseFormUnit(resultVo.getMaxLifetimeDoseForm().getUnit());
        doseRange.setMaxSingleDose(BigDecimal.valueOf(resultVo.getMaxSingleDose().getValue()));
        doseRange.setMaxSingleDoseUnit(resultVo.getMaxSingleDose().getUnit());
        doseRange.setMaxSingleDoseForm(BigDecimal.valueOf(resultVo.getMaxSingleDoseForm().getValue()));
        doseRange.setMaxSingleDoseFormUnit(resultVo.getMaxDailyDoseForm().getUnit());
        doseRange.setRenalImpairment(resultVo.getRenalImpairment());
        doseRange.setWeightRequired(resultVo.getWeightRequired());
        doseRange.setMaxSingleNTEDose(BigDecimal.valueOf(resultVo.getMaxSingleNTEDose().getValue()));
        doseRange.setMaxSingleNTEDoseUnit(resultVo.getMaxSingleNTEDose().getUnit());
        doseRange.setMaxSingleNTEDoseForm(BigDecimal.valueOf(resultVo.getMaxSingleNTEDoseForm().getValue()));
        doseRange.setMaxSingleNTEDoseFormUnit(resultVo.getMaxSingleNTEDoseForm().getUnit());
        return doseRange;
    }

    /**
     * Converts a MinMaxResultVo to a MinMax object.
     * 
     * @param mmResultVo
     *            the MinMaxResultVo to convert.
     * @param objectFactory
     *            the ObjectFactory to create the MinMax object.
     * @return MinMax
     */
    private static MinMax convertMinMax(MinMaxResultVo mmResultVo, ObjectFactory objectFactory) {
        MinMax mm = objectFactory.createMinMax();

        mm.setAgeHighInDays(mmResultVo.getAgeInDaysHigh());
        mm.setAgeLowInDays(mmResultVo.getAgeInDaysLow());
        mm.setBsaRequired(mmResultVo.getBsaRequired());
        mm.setDoseFormHigh(BigDecimal.valueOf(mmResultVo.getDoseFormHigh()));
        mm.setDoseFormHighUnit(mmResultVo.getDoseFormHighUnit());
        mm.setDoseFormLow(BigDecimal.valueOf(mmResultVo.getDoseFormLow()));
        mm.setDoseFormLowUnit(mmResultVo.getDoseFormLowUnit());
        mm.setDoseHigh(BigDecimal.valueOf(mmResultVo.getDoseHigh()));
        mm.setDoseHighUnit(mmResultVo.getDoseHighUnit());
        mm.setDoseLow(BigDecimal.valueOf(mmResultVo.getDoseLow()));
        mm.setDoseLowUnit(mmResultVo.getDoseLowUnit());
        mm.setMaxDailyDose(BigDecimal.valueOf(mmResultVo.getMaxDailyDose()));
        mm.setMaxDailyDoseForm(BigDecimal.valueOf(mmResultVo.getMaxDailyDoseForm()));
        mm.setMaxDailyDoseFormUnit(mmResultVo.getMaxDailyDoseFormUnit());
        mm.setMaxDailyDoseUnit(mmResultVo.getMaxDailyDoseUnit());
        mm.setResultType(mmResultVo.getResultType().toString());
        mm.setWarningCode(BigInteger.valueOf(mmResultVo.getWarningCode()));
        mm.setWarningMessage(mmResultVo.getWarningMessage());
        mm.setWeightRequired(mmResultVo.getWeightRequired());
        return mm;
    }

    /**
     * Convert a Collection of DrugInfoVo representing drugs that could not be
     * loaded by FDB into a DrugsNotFound JAXB object.
     * 
     * @param dosingNotFound
     *            Collection of DosingInfoVo
     * @param objectFactory
     *            ObjectFactory used to create JAXB object instances
     * @return DrugsNotFound
     */
    private static DosingNotFound convertDosingNotFound(Collection<DosingNotFoundVo> dosingNotFound, 
            ObjectFactory objectFactory) {
        DosingNotFound results = null;

        if (!dosingNotFound.isEmpty()) {
            results = objectFactory.createDosingNotFound();

            for (DosingNotFoundVo dosingInfoVo : dosingNotFound) {
                NotFound notFound = objectFactory.createNotFound();
                results.getNotFound().add(notFound);

                notFound.setGcnSeqNo(dosingInfoVo.getGcnSeqNo());
                notFound.setFdbdx(dosingInfoVo.getFdbdx());
                notFound.setNotFoundReason(dosingInfoVo.getNotFoundReason());
            }
        }

        return results;
    }

    /**
     * Cannot instantiate
     */
    private ResponseConverter() {
        super();
    }
}
