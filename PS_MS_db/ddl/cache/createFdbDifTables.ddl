-- Create Table Script
-- For DIF v.3.2US, Build Version: 5, Build Date: 200503071108
-- DIF 3.2 US
-- Drug Information Framework
-- First DataBank
-- (Includes User-Content tables.)
-- Created Mon Mar 07 11:11:42 EST 2005

create table fdb_agid_axsid (
	agid INTEGER not null,
	axsid SMALLINT not null, 
	constraint pkfdbagidaxsid primary key (agid, axsid)
);

create table fdb_ahfsclass_drugconcept (
	ahfsclassid VARCHAR(12) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null, 
	constraint pkfdbahfsclassdrugconcept primary key (ahfsclassid, conceptid, concepttype)
);

create index ixfdbahfsclassdrugconcept1 on fdb_ahfsclass_drugconcept (conceptid);

create table fdb_ahfsclassification_drugs (
	classid VARCHAR(12) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	commonuseind SMALLINT,
	defaultuseind SMALLINT, 
	constraint pkfdbahfsclassificationdrugs primary key (classid, conceptid, concepttype)
);

create table fdb_ahfsclassification_link (
	classid VARCHAR(12) not null,
	linkedchild VARCHAR(12) not null, 
	constraint pkfdbahfsclassificationlink primary key (classid, linkedchild)
);

create table fdb_allergen_hicseqno (
	allergenid VARCHAR(12) not null,
	hicseqno INTEGER not null, 
	constraint pkfdballergenhicseqno primary key (allergenid, hicseqno)
);

create index ixfdballergenhicseqno1 on fdb_allergen_hicseqno (hicseqno);

create table fdb_allergengroup (
	agid INTEGER not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150), 
	constraint pkfdballergengroup primary key (agid)
);

create table fdb_allergengroup_ii (
	agid INTEGER not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	statuscode VARCHAR(1),
	potentiallyinactiveind SMALLINT, 
	constraint pkfdballergengroupii primary key (agid)
);

create table fdb_allergengroupsearch (
	languageid SMALLINT not null,
	agid INTEGER not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdballergengroupsearch primary key (languageid, agid)
);

create index ixfdballergengroupsearch1 on fdb_allergengroupsearch (descsearch);

create index ixfdballergengroupsearch2 on fdb_allergengroupsearch (descaltsearch);

create table fdb_allergengroupsearch_ii (
	languageid SMALLINT not null,
	agid INTEGER not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdballergengroupsearchii primary key (languageid, agid)
);

create index ixfdballergengroupsearchii1 on fdb_allergengroupsearch_ii (descsearch);

create index ixfdballergengroupsearchii2 on fdb_allergengroupsearch_ii (descaltsearch);

create table fdb_allergenpicklist (
	conceptid INTEGER not null,
	concepttype SMALLINT not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	tm_descdisplay VARCHAR(150), 
	constraint pkfdballergenpicklist primary key (conceptid, concepttype)
);

create table fdb_allergenpicklistsearch (
	languageid SMALLINT not null,
	conceptid INTEGER not null,
	concepttype SMALLINT not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdballergenpicklistsearch primary key (languageid, conceptid, concepttype)
);

create index ixfdballergenpicklistsearch1 on fdb_allergenpicklistsearch (descsearch);

create index ixfdballergenpicklistsearch2 on fdb_allergenpicklistsearch (descaltsearch);

create table fdb_allergens (
	allergenid VARCHAR(22) not null,
	gcnseqno INTEGER,
	hicl INTEGER,
	clinicallinkind SMALLINT,
	statuscode VARCHAR(1), 
	constraint pkfdballergens primary key (allergenid)
);

create table fdb_allergens_ii (
	allergenid VARCHAR(22) not null,
	gcnseqno INTEGER,
	hicl INTEGER,
	clinicallinkind SMALLINT,
	statuscode VARCHAR(1),
	tm_descdisplay VARCHAR(125),
	description1 VARCHAR(255),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255), 
	constraint pkfdballergensii primary key (allergenid)
);

create table fdb_allergycrosssensitivity (
	axsid SMALLINT not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150), 
	constraint pkfdballergycrosssensitivity primary key (axsid)
);

create table fdb_class_ahfs (
	ahfsclassid VARCHAR(12) not null,
	description1 VARCHAR(40),
	description2 VARCHAR(120),
	description3 VARCHAR(120),
	description4 VARCHAR(120),
	description5 VARCHAR(120),
	description6 VARCHAR(120),
	parentid VARCHAR(12),
	haschildrenind SMALLINT, 
	constraint pkfdbclassahfs primary key (ahfsclassid)
);

create table fdb_class_ahfssearch (
	languageid SMALLINT not null,
	ahfsclassid VARCHAR(12) not null,
	descsearch VARCHAR(125),
	descaltsearch VARCHAR(125),
	baselangid SMALLINT, 
	constraint pkfdbclassahfssearch primary key (languageid, ahfsclassid)
);

create index ixfdbclassahfssearch1 on fdb_class_ahfssearch (descsearch);

create index ixfdbclassahfssearch2 on fdb_class_ahfssearch (descaltsearch);

create table fdb_class_fdb (
	fdbclassid INTEGER not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	parentid INTEGER,
	haschildrenind SMALLINT,
	hic3 VARCHAR(3), 
	constraint pkfdbclassfdb primary key (fdbclassid)
);

create table fdb_class_fdbsearch (
	languageid SMALLINT not null,
	fdbclassid INTEGER not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdbclassfdbsearch primary key (languageid, fdbclassid)
);

create index ixfdbclassfdbsearch1 on fdb_class_fdbsearch (descsearch);

create index ixfdbclassfdbsearch2 on fdb_class_fdbsearch (descaltsearch);

create table fdb_classification_ahfs (
	classid VARCHAR(12) not null,
	description1 VARCHAR(40),
	description2 VARCHAR(120),
	description3 VARCHAR(120),
	description4 VARCHAR(120),
	description5 VARCHAR(120),
	description6 VARCHAR(120),
	ultimatechildind SMALLINT,
	directdruglinkind SMALLINT,
	parentid VARCHAR(12),
	formularylevelind SMALLINT,
	presentationseqno INTEGER,
	ultiparentid VARCHAR(12),
	hierarchylevel SMALLINT,
	sortnumber INTEGER,
	retiredind SMALLINT,
	retireddate VARCHAR(8),
	altcode VARCHAR(20), 
	constraint pkfdbclassificationahfs primary key (classid)
);

create index ixfdbclassificationahfs1 on fdb_classification_ahfs (parentid);

create index ixfdbclassificationahfs2 on fdb_classification_ahfs (ultiparentid);

create table fdb_classification_ahfssearch (
	languageid SMALLINT not null,
	classid VARCHAR(12) not null,
	descsearch VARCHAR(125),
	descaltsearch VARCHAR(125),
	baselangid SMALLINT, 
	constraint pkfdbclassificationahfssearch primary key (languageid, classid)
);

create index ixfdbclassificationahfssear1 on fdb_classification_ahfssearch (descsearch);

create index ixfdbclassificationahfssear2 on fdb_classification_ahfssearch (descaltsearch);

create table fdb_classification_etc (
	classid INTEGER not null,
	description1 VARCHAR(70),
	description2 VARCHAR(210),
	description3 VARCHAR(210),
	description4 VARCHAR(210),
	description5 VARCHAR(210),
	description6 VARCHAR(210),
	ultimatechildind SMALLINT,
	directdruglinkind SMALLINT,
	parentid INTEGER,
	formularylevelind SMALLINT,
	presentationseqno INTEGER,
	ultiparentid INTEGER,
	hierarchylevel SMALLINT,
	sortnumber INTEGER,
	retiredind SMALLINT,
	retireddate VARCHAR(8), 
	constraint pkfdbclassificationetc primary key (classid)
);

create index ixfdbclassificationetc1 on fdb_classification_etc (parentid);

create index ixfdbclassificationetc2 on fdb_classification_etc (ultiparentid);

create table fdb_classification_etcsearch (
	languageid SMALLINT not null,
	classid INTEGER not null,
	descsearch VARCHAR(215),
	descaltsearch VARCHAR(215),
	baselangid SMALLINT, 
	constraint pkfdbclassificationetcsearch primary key (languageid, classid)
);

create index ixfdbclassificationetcsearc1 on fdb_classification_etcsearch (descsearch);

create index ixfdbclassificationetcsearc2 on fdb_classification_etcsearch (descaltsearch);

create table fdb_classification_ext (
	classtypecode VARCHAR(2) not null,
	classid VARCHAR(15) not null,
	description1 VARCHAR(75),
	description2 VARCHAR(225),
	description3 VARCHAR(225),
	description4 VARCHAR(225),
	description5 VARCHAR(225),
	description6 VARCHAR(225),
	ultimatechildind SMALLINT,
	directdruglinkind SMALLINT,
	parentid VARCHAR(15),
	formularylevelind SMALLINT,
	presentationseqno INTEGER,
	ultiparentid VARCHAR(15),
	hierarchylevel SMALLINT,
	sortnumber INTEGER,
	retiredind SMALLINT,
	retireddate VARCHAR(8),
	altcode VARCHAR(20), 
	constraint pkfdbclassificationext primary key (classtypecode, classid)
);

create index ixfdbclassificationext1 on fdb_classification_ext (parentid);

create index ixfdbclassificationext2 on fdb_classification_ext (ultiparentid);

create table fdb_classification_extsearch (
	languageid SMALLINT not null,
	classtypecode VARCHAR(2) not null,
	classid VARCHAR(15) not null,
	descsearch VARCHAR(230),
	descaltsearch VARCHAR(230),
	baselangid SMALLINT, 
	constraint pkfdbclassificationextsearch primary key (languageid, classtypecode, classid)
);

create index ixfdbclassificationextsearc1 on fdb_classification_extsearch (descsearch);

create index ixfdbclassificationextsearc2 on fdb_classification_extsearch (descaltsearch);

create table fdb_classification_fdb (
	classid INTEGER not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	ultimatechildind SMALLINT,
	directdruglinkind SMALLINT,
	parentid INTEGER,
	formularylevelind SMALLINT,
	presentationseqno INTEGER,
	ultiparentid INTEGER,
	hierarchylevel SMALLINT,
	sortnumber INTEGER,
	retiredind SMALLINT,
	retireddate VARCHAR(8),
	altcode VARCHAR(20), 
	constraint pkfdbclassificationfdb primary key (classid)
);

create index ixfdbclassificationfdb1 on fdb_classification_fdb (parentid);

create index ixfdbclassificationfdb2 on fdb_classification_fdb (ultiparentid);

create table fdb_classification_fdbsearch (
	languageid SMALLINT not null,
	classid INTEGER not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdbclassificationfdbsearch primary key (languageid, classid)
);

create index ixfdbclassificationfdbsearc1 on fdb_classification_fdbsearch (descsearch);

create index ixfdbclassificationfdbsearc2 on fdb_classification_fdbsearch (descaltsearch);

create table fdb_codedefinition (
	codetype SMALLINT not null,
	codevalue VARCHAR(10) not null,
	codedescription1 VARCHAR(255),
	codedescription2 VARCHAR(255),
	codedescription3 VARCHAR(255),
	codedescription4 VARCHAR(255),
	codedescription5 VARCHAR(255),
	codedescription6 VARCHAR(255), 
	constraint pkfdbcodedefinition primary key (codetype, codevalue)
);

-- Table fdb_compound will contain customer content.
create table fdb_compound (
	compoundid INTEGER not null,
	description1 VARCHAR(255),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255), 
	constraint pkfdbcompound primary key (compoundid)
);

-- Table fdb_compound_rtgen will contain customer content.
create table fdb_compound_rtgen (
	compoundid INTEGER not null,
	rtgenid INTEGER not null, 
	constraint pkfdbcompoundrtgen primary key (compoundid, rtgenid)
);

-- Table fdb_compoundsearch will contain customer content.
create table fdb_compoundsearch (
	languageid SMALLINT not null,
	compoundid INTEGER not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbcompoundsearch primary key (languageid, compoundid)
);

create table fdb_concept_iisurvey (
	conceptid INTEGER not null,
	concepttype SMALLINT not null,
	percentsurveyed NUMERIC(16, 6), 
	constraint pkfdbconceptiisurvey primary key (conceptid, concepttype)
);

create table fdb_concept_pemdruglink (
	conceptid VARCHAR(20) not null,
	concepttype SMALLINT not null,
	monographid INTEGER not null, 
	constraint pkfdbconceptpemdruglink primary key (conceptid, concepttype, monographid)
);

create table fdb_concept_withinactives (
	conceptid VARCHAR(20) not null,
	concepttype SMALLINT not null,
	hicseqno INTEGER not null,
	alwayspresentind SMALLINT, 
	constraint pkfdbconceptwithinactives primary key (conceptid, concepttype, hicseqno)
);

-- Table fdb_custom_allergenpicklist will contain customer content.
create table fdb_custom_allergenpicklist (
	category VARCHAR(255) not null,
	conceptid VARCHAR(20) not null,
	concepttype SMALLINT not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	tm_descdisplay VARCHAR(150), 
	constraint pkfdbcustomallergenpicklist primary key (category, conceptid, concepttype)
);

-- Table fdb_custom_allergenpicklstsrch will contain customer content.
create table fdb_custom_allergenpicklstsrch (
	languageid SMALLINT not null,
	category VARCHAR(255) not null,
	conceptid VARCHAR(20) not null,
	concepttype SMALLINT not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdbcustomallergenpicklstsrch primary key (languageid, category, conceptid, concepttype)
);

create index ixfdbcstallergenpicklstsrch1 on fdb_custom_allergenpicklstsrch (descsearch);

create index ixfdbcstallergenpicklstsrch2 on fdb_custom_allergenpicklstsrch (descaltsearch);

-- Table fdb_custom_attributenames will contain customer content.
create table fdb_custom_attributenames (
	attributeid VARCHAR(5) not null,
	description VARCHAR(50), 
	constraint pkfdbcustomattributenames primary key (attributeid)
);

-- Table fdb_custom_attributevalues will contain customer content.
create table fdb_custom_attributevalues (
	concepttype SMALLINT not null,
	conceptidstring VARCHAR(20) not null,
	attributeid VARCHAR(5) not null,
	conceptidnumeric INTEGER,
	attributevalue VARCHAR(50), 
	constraint pkfdbcustomattributevalues primary key (conceptidstring, concepttype, attributeid)
);

create index ixfdbcustomattributevalues1 on fdb_custom_attributevalues (conceptidstring);

create index ixfdbcustomattributevalues2 on fdb_custom_attributevalues (conceptidnumeric);

-- Table fdb_custom_class will contain customer content.
create table fdb_custom_class (
	category VARCHAR(10) not null,
	classid VARCHAR(10) not null,
	description1 VARCHAR(50),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	parentid VARCHAR(10),
	haschildrenind SMALLINT, 
	constraint pkfdbcustomclass primary key (category, classid)
);

-- Table fdb_custom_class_drugconcept will contain customer content.
create table fdb_custom_class_drugconcept (
	category VARCHAR(10) not null,
	classid VARCHAR(10) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null, 
	constraint pkfdbcustomclassdrugconcept primary key (category, classid, conceptid, concepttype)
);

create index ixfdbcustomclassdrugconcept1 on fdb_custom_class_drugconcept (conceptid);

-- Table fdb_custom_class_search will contain customer content.
create table fdb_custom_class_search (
	languageid SMALLINT not null,
	category VARCHAR(10) not null,
	classid VARCHAR(10) not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdbcustomclasssearch primary key (languageid, category, classid)
);

create index ixfdbcustomclasssearch1 on fdb_custom_class_search (descsearch);

create index ixfdbcustomclasssearch2 on fdb_custom_class_search (descaltsearch);

-- Table fdb_custom_ddim will contain customer content.
create table fdb_custom_ddim (
	rtgenid1 INTEGER not null,
	rtgenid2 INTEGER not null,
	interactionid INTEGER not null,
	seqno SMALLINT not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbcustomddim primary key (rtgenid1, rtgenid2, interactionid, seqno)
);

-- Table fdb_custom_ddimdruglinkcat will contain customer content.
create table fdb_custom_ddimdruglinkcat (
	rtgenid1 INTEGER not null,
	rtgenid2 INTEGER not null,
	interactionid INTEGER not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbcustomddimdruglinkcat primary key (rtgenid1, rtgenid2, interactionid)
);

-- Table fdb_custom_ddiminteraction will contain customer content.
create table fdb_custom_ddiminteraction (
	interactionid INTEGER not null,
	seqno SMALLINT not null,
	description1 VARCHAR(60),
	description2 VARCHAR(180),
	description3 VARCHAR(180),
	description4 VARCHAR(180),
	description5 VARCHAR(180),
	description6 VARCHAR(180),
	severitylevelcode VARCHAR(1),
	monographid INTEGER,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150),
	clinicaleffectcode1 VARCHAR(3),
	clinicaleffectcode2 VARCHAR(3), 
	constraint pkfdbcustomddiminteraction primary key (interactionid, seqno)
);

-- Table fdb_custom_ddimstrings will contain customer content.
-- SwRI removed primary key to accomodate PECS many-to-many mapping!
create table fdb_custom_ddimstrings (
	category VARCHAR(10) not null,
	interactionid INTEGER not null,
	customstring VARCHAR(255)
	--constraint pkfdbcustomddimstrings primary key (category, interactionid)
);

create index ixfdbcustomddimstrings1 on fdb_custom_ddimstrings (interactionid);

-- Table fdb_custom_doserangecheck will contain customer content.
create table fdb_custom_doserangecheck (
	category VARCHAR(10) not null,
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(20),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(20),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(20),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(20),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(20),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(20),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(20),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(20),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(20),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(20),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(20),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(20),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(20),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(20),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(20),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(20),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(20),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(20),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(20),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(20), 
	constraint pkfdbcustomdoserangecheck primary key (category, gcnseqno, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx)
);

-- Table fdb_custom_dosing will contain customer content.
create table fdb_custom_dosing (
	category VARCHAR(10) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(30),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(30),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(30),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(30),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(30),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(30),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(30),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(30),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(30),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(30),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(30),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(30),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(30),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(30),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(30),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(30),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(30),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(30),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(30),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(30),
	dxid VARCHAR(8), 
	constraint pkfdbcustomdosing primary key (category, conceptid, concepttype, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx)
);

-- Table fdb_custom_dosingneo will contain customer content.
create table fdb_custom_dosingneo (
	category VARCHAR(10) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	gablowinweeks SMALLINT not null,
	gabhighinweeks SMALLINT not null,
	weightlowingrams INTEGER not null,
	weighthighingrams INTEGER not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(20),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(20),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(20),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(20),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(20),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(20),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(20),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(20),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(20),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(20),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(20),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(20),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	gabrequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(20),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(20),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(20),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(20),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(20),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(20),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(20),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(20),
	dxid VARCHAR(8), 
	constraint pkfdbcustomdosingneo primary key (category, conceptid, concepttype, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx, gablowinweeks, gabhighinweeks, weightlowingrams, weighthighingrams)
);

-- Table fdb_custom_drugmapping will contain customer content.
create table fdb_custom_drugmapping (
	category VARCHAR(10) not null,
	customdrugid VARCHAR(20) not null,
	concepttype SMALLINT,
	conceptidstring VARCHAR(20),
	conceptidnumeric INTEGER, 
	constraint pkfdbcustomdrugmapping primary key (category, customdrugid)
);

create index ixfdbcustomdrugmapping1 on fdb_custom_drugmapping (conceptidstring);

create index ixfdbcustomdrugmapping2 on fdb_custom_drugmapping (conceptidnumeric);

-- Table fdb_custom_dtcat will contain customer content.
create table fdb_custom_dtcat (
	category VARCHAR(10) not null,
	dtcid INTEGER not null,
	customstring VARCHAR(255),
	customdupallowance SMALLINT,
	customdupallowind SMALLINT, 
	constraint pkfdbcustomdtcat primary key (category, dtcid)
);

create index ixfdbcustomdtcat1 on fdb_custom_dtcat (dtcid);

-- Table fdb_custom_indication will contain customer content.
create table fdb_custom_indication (
	category VARCHAR(10) not null,
	rtgenid INTEGER not null,
	vocabtypecode VARCHAR(1) not null,
	medcondid VARCHAR(20) not null,
	comment1 VARCHAR(50),
	comment2 VARCHAR(150),
	comment3 VARCHAR(150),
	comment4 VARCHAR(150),
	comment5 VARCHAR(150),
	comment6 VARCHAR(150), 
	constraint pkfdbcustomindication primary key (category, rtgenid, vocabtypecode, medcondid)
);

-- Table fdb_custom_interactcategory will contain customer content.
create table fdb_custom_interactcategory (
	interactiontypecode VARCHAR(1) not null,
	interactionid INTEGER not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbcustominteractcategory primary key (interactiontypecode, interactionid)
);

-- Table fdb_custom_monograph will contain customer content.
create table fdb_custom_monograph (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	categorycode VARCHAR(10) not null,
	monographid INTEGER not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(1),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbcustommonograph primary key (languageid, versioncode, categorycode, monographid, sequencenumber)
);

-- Table fdb_custom_msg_catdef will contain customer content.
create table fdb_custom_msg_catdef (
	categoryid VARCHAR(10) not null,
	description VARCHAR(50), 
	constraint pkfdbcustommsgcatdef primary key (categoryid)
);

-- Table fdb_custom_msg_def will contain customer content.
create table fdb_custom_msg_def (
	messageid INTEGER not null,
	categoryid VARCHAR(10), 
	constraint pkfdbcustommsgdef primary key (messageid)
);

-- Table fdb_custom_msg_link will contain customer content.
create table fdb_custom_msg_link (
	concepttype SMALLINT not null,
	conceptidstring VARCHAR(20) not null,
	messageid INTEGER not null,
	conceptidnumeric INTEGER, 
	constraint pkfdbcustommsglink primary key (conceptidstring, concepttype, messageid)
);

create index ixfdbcustommsglink1 on fdb_custom_msg_link (conceptidstring);

create index ixfdbcustommsglink2 on fdb_custom_msg_link (conceptidnumeric);

-- Table fdb_custom_msg_text will contain customer content.
create table fdb_custom_msg_text (
	languageid SMALLINT not null,
	messageid INTEGER not null,
	sequencenumber SMALLINT not null,
	linetext VARCHAR(255),
	beginparagraphind SMALLINT, 
	constraint pkfdbcustommsgtext primary key (languageid, messageid, sequencenumber)
);

-- Table fdb_custom_packageddrugpricing will contain customer content.
create table fdb_custom_packageddrugpricing (
	category VARCHAR(10) not null,
	pmid VARCHAR(20) not null,
	pricetypecode VARCHAR(2) not null,
	effectivedate VARCHAR(8) not null,
	price NUMERIC(16, 6),
	currentpriceind SMALLINT, 
	constraint pkfdbcustompackageddrugpricing primary key (category, pmid, pricetypecode, effectivedate)
);

create table fdb_damscreen (
	drugallergyid VARCHAR(12) not null,
	hitval VARCHAR(8) not null,
	inactivehicls VARCHAR(255), 
	constraint pkfdbdamscreen primary key (drugallergyid, hitval)
);

create table fdb_damscreen_ii (
	drugallergyid VARCHAR(22) not null,
	hitval VARCHAR(8) not null,
	inactivehicls VARCHAR(255),
	inactiveingredienthitind SMALLINT, 
	constraint pkfdbdamscreenii primary key (drugallergyid, hitval)
);

create table fdb_damscreeninfo (
	hitval VARCHAR(8) not null,
	hitid INTEGER,
	hittype SMALLINT,
	hitdescription1 VARCHAR(100),
	hitdescription2 VARCHAR(150),
	hitdescription3 VARCHAR(150),
	hitdescription4 VARCHAR(150),
	hitdescription5 VARCHAR(150),
	hitdescription6 VARCHAR(150),
	allergengroupind SMALLINT, 
	constraint pkfdbdamscreeninfo primary key (hitval)
);

create index ixfdbdamscreeninfo1 on fdb_damscreeninfo (hittype);

create index ixfdbdamscreeninfo2 on fdb_damscreeninfo (hitid);

create table fdb_damscreeninfo_ii (
	hitval VARCHAR(8) not null,
	hitid INTEGER,
	hittype SMALLINT,
	hitdescription1 VARCHAR(100),
	hitdescription2 VARCHAR(150),
	hitdescription3 VARCHAR(150),
	hitdescription4 VARCHAR(150),
	hitdescription5 VARCHAR(150),
	hitdescription6 VARCHAR(150), 
	constraint pkfdbdamscreeninfoii primary key (hitval)
);

create index ixfdbdamscreeninfoii1 on fdb_damscreeninfo_ii (hittype);

create index ixfdbdamscreeninfoii2 on fdb_damscreeninfo_ii (hitid);

create table fdb_ddcm (
	contraindid INTEGER not null,
	sequencenumber SMALLINT not null,
	descdrug1 VARCHAR(100),
	descdrug2 VARCHAR(255),
	descdrug3 VARCHAR(255),
	descdrug4 VARCHAR(255),
	descdrug5 VARCHAR(255),
	descdrug6 VARCHAR(255),
	descdisease1 VARCHAR(100),
	descdisease2 VARCHAR(255),
	descdisease3 VARCHAR(255),
	descdisease4 VARCHAR(255),
	descdisease5 VARCHAR(255),
	descdisease6 VARCHAR(255),
	fdbdx VARCHAR(9),
	sevlevelcode VARCHAR(1),
	pagereference VARCHAR(26),
	dxid VARCHAR(8), 
	constraint pkfdbddcm primary key (contraindid, sequencenumber)
);

create table fdb_ddcmdruglink (
	drugid VARCHAR(12) not null,
	contraindid INTEGER not null,
	gcnseqno INTEGER, 
	constraint pkfdbddcmdruglink primary key (drugid, contraindid)
);

create table fdb_ddim_catseverities (
	category VARCHAR(10) not null,
	interactionid INTEGER not null,
	severitycode VARCHAR(5), 
	constraint pkfdbddimcatseverities primary key (category, interactionid)
);

create index ixfdbddimcatseverities1 on fdb_ddim_catseverities (interactionid);

create table fdb_ddimdruglink (
	rtgenid1 INTEGER not null,
	rtgenid2 INTEGER not null,
	interactionid INTEGER not null, 
	constraint pkfdbddimdruglink primary key (rtgenid1, rtgenid2, interactionid)
);

create index ixfdbddimdruglink1 on fdb_ddimdruglink (rtgenid2);

create table fdb_ddiminactivedruglink (
	pmid VARCHAR(20) not null,
	interactionid INTEGER not null,
	interactionidrev INTEGER, 
	constraint pkfdbddiminactivedruglink primary key (pmid, interactionid)
);

create table fdb_ddiminteraction (
	interactionid INTEGER not null,
	description1 VARCHAR(60),
	description2 VARCHAR(180),
	description3 VARCHAR(180),
	description4 VARCHAR(180),
	description5 VARCHAR(180),
	description6 VARCHAR(180),
	severitylevelcode VARCHAR(1),
	monographid INTEGER,
	edipageref VARCHAR(9),
	docmfgind SMALLINT,
	dochumantrialind SMALLINT,
	docanimalstudyind SMALLINT,
	docmtgabstractind SMALLINT,
	docrvwarticleind SMALLINT,
	doccaserptind SMALLINT,
	clinicaleffectcode1 VARCHAR(3),
	clinicaleffectcode2 VARCHAR(3), 
	constraint pkfdbddiminteraction primary key (interactionid)
);

create table fdb_dfimdruglink (
	rtgenid INTEGER not null,
	interactionid INTEGER not null, 
	constraint pkfdbdfimdruglink primary key (rtgenid, interactionid)
);

create table fdb_dfiminteraction (
	interactionid INTEGER not null,
	interactor VARCHAR(21),
	monographid INTEGER,
	interactionresult1 VARCHAR(45),
	interactionresult2 VARCHAR(135),
	interactionresult3 VARCHAR(135),
	interactionresult4 VARCHAR(135),
	interactionresult5 VARCHAR(135),
	interactionresult6 VARCHAR(135),
	interactionmessage1 VARCHAR(55),
	interactionmessage2 VARCHAR(165),
	interactionmessage3 VARCHAR(165),
	interactionmessage4 VARCHAR(165),
	interactionmessage5 VARCHAR(165),
	interactionmessage6 VARCHAR(165),
	significancelevelcode VARCHAR(1), 
	constraint pkfdbdfiminteraction primary key (interactionid)
);

create table fdb_disclaimer (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	categorycode VARCHAR(10) not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(1),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbdisclaimer primary key (languageid, versioncode, categorycode, sequencenumber)
);

create table fdb_discreen (
	drugid VARCHAR(12) not null,
	hicseqno INTEGER not null, 
	constraint pkfdbdiscreen primary key (drugid, hicseqno)
);

create table fdb_dispensable (
	medid INTEGER not null,
	descdisplay VARCHAR(125),
	descsearch VARCHAR(125),
	descaltsearch VARCHAR(125),
	mnid INTEGER,
	rtid INTEGER,
	dfid INTEGER,
	strength VARCHAR(15),
	strengthunits VARCHAR(15),
	rmid INTEGER,
	rdfmid INTEGER,
	gcnseqno INTEGER,
	rtgenid INTEGER,
	hicl INTEGER,
	genericlinkindicator SMALLINT,
	nametypecode VARCHAR(1),
	namesourcecode VARCHAR(1),
	genderspecificdrugcode VARCHAR(1),
	reffederallegendcode VARCHAR(1),
	refmultisourcecode VARCHAR(1),
	reffederaldeaclasscode VARCHAR(1),
	refgenericdrugnamecode VARCHAR(1),
	refgenericcomppricecode VARCHAR(1),
	refgenericpricespreadcode VARCHAR(1),
	refinnovatorcode VARCHAR(1),
	refgenerictheraequivcode VARCHAR(1),
	refdesicode VARCHAR(1),
	refdesi2code VARCHAR(1),
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	obsoletedate VARCHAR(8),
	replacedindicator SMALLINT,
	singledoserouteind SMALLINT,
	haspackageddrugsind SMALLINT,
	hasequivpackageddrugsind SMALLINT,
	hasimagesind SMALLINT,
	genericmnid INTEGER,
	genericmedid INTEGER,
	refrepackagercode VARCHAR(1),
	refprivlabeledprodcode VARCHAR(1),
	statuscode VARCHAR(1),
	numstrength NUMERIC(16, 6),
	numstrengthuom VARCHAR(10),
	numvolume NUMERIC(16, 6),
	numvolumeuom VARCHAR(5),
	tm_descdisplay VARCHAR(125),
	tm_confusiongroupid INTEGER,
	tm_groupdesc VARCHAR(250),
	tm_sourcecode VARCHAR(1),
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	inactiveingredientreviewind SMALLINT,
	safenumstrengthuom VARCHAR(50),
	safenumvolumeuom VARCHAR(50), 
	constraint pkfdbdispensable primary key (medid)
);

create index ixfdbdispensable1 on fdb_dispensable (gcnseqno);

create index ixfdbdispensable2 on fdb_dispensable (descaltsearch);

create index ixfdbdispensable3 on fdb_dispensable (rmid);

create index ixfdbdispensable4 on fdb_dispensable (descsearch);

create table fdb_dispensable_image (
	medid INTEGER not null,
	labelerid VARCHAR(6) not null,
	imagefilename VARCHAR(8) not null,
	refrepackagercode VARCHAR(1), 
	constraint pkfdbdispensableimage primary key (medid, labelerid, imagefilename)
);

create table fdb_dispensable_labeler (
	medid INTEGER not null,
	labelerid VARCHAR(6) not null,
	refrepackagercode VARCHAR(1), 
	constraint pkfdbdispensablelabeler primary key (medid, labelerid)
);

create table fdb_doseform (
	dfid INTEGER not null,
	description VARCHAR(30),
	abbrev VARCHAR(4), 
	constraint pkfdbdoseform primary key (dfid)
);

create table fdb_doserangecheck (
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(20),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(20),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(20),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(20),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(20),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(20),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(20),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(20),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(20),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(20),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(20),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(20),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(20),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(20),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(20),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(20),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(20),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(20),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(20),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(20), 
	constraint pkfdbdoserangecheck primary key (gcnseqno, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx)
);

create table fdb_doseroute (
	doserouteid VARCHAR(3) not null,
	description1 VARCHAR(22),
	description2 VARCHAR(66),
	description3 VARCHAR(66),
	description4 VARCHAR(66),
	description5 VARCHAR(66),
	description6 VARCHAR(66), 
	constraint pkfdbdoseroute primary key (doserouteid)
);

create table fdb_dosetype (
	dosetypeid VARCHAR(2) not null,
	description1 VARCHAR(25),
	description2 VARCHAR(75),
	description3 VARCHAR(75),
	description4 VARCHAR(75),
	description5 VARCHAR(75),
	description6 VARCHAR(75), 
	constraint pkfdbdosetype primary key (dosetypeid)
);

create table fdb_doseunitsconversion (
	units1 VARCHAR(20) not null,
	units2 VARCHAR(20) not null,
	conversionfactor NUMERIC(16, 6), 
	constraint pkfdbdoseunitsconversion primary key (units1, units2)
);

create table fdb_doseunitstype (
	units VARCHAR(20) not null,
	unitstype SMALLINT, 
	constraint pkfdbdoseunitstype primary key (units)
);

create table fdb_dosing (
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	hittype SMALLINT not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(30),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(30),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(30),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(30),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(30),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(30),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(30),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(30),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(30),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(30),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(30),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(30),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	warningcode VARCHAR(1),
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(30),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(30),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(30),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(30),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(30),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(30),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(30),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(30),
	dxid VARCHAR(8), 
	constraint pkfdbdosing primary key (conceptid, concepttype, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx, hittype)
);

create index ixfdbdosing1 on fdb_dosing (hittype);

create table fdb_dosingneo (
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	gablowinweeks SMALLINT not null,
	gabhighinweeks SMALLINT not null,
	weightlowingrams INTEGER not null,
	weighthighingrams INTEGER not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(20),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(20),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(20),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(20),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(20),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(20),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(20),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(20),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(20),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(20),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(20),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(20),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	gabrequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(20),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(20),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(20),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(20),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(20),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(20),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(20),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(20),
	dxid VARCHAR(8), 
	constraint pkfdbdosingneo primary key (conceptid, concepttype, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx, gablowinweeks, gabhighinweeks, weightlowingrams, weighthighingrams)
);

create table fdb_drcneo (
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doserouteid VARCHAR(3) not null,
	dosetypeid VARCHAR(2) not null,
	fdbdx VARCHAR(9) not null,
	gablowinweeks SMALLINT not null,
	gabhighinweeks SMALLINT not null,
	weightlowingrams INTEGER not null,
	weighthighingrams INTEGER not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(20),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(20),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(20),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(20),
	maxsingledose NUMERIC(16, 6),
	maxsingledoseunits VARCHAR(20),
	maxsingledoseform NUMERIC(16, 6),
	maxsingledoseformunits VARCHAR(20),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(20),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(20),
	maxlifetimedose NUMERIC(16, 6),
	maxlifetimedoseunits VARCHAR(20),
	maxlifetimedoseform NUMERIC(16, 6),
	maxlifetimedoseformunits VARCHAR(20),
	loweliminationhalflife NUMERIC(16, 6),
	higheliminationhalflife NUMERIC(16, 6),
	halflifeunits VARCHAR(20),
	frequencylow NUMERIC(16, 6),
	frequencyhigh NUMERIC(16, 6),
	durationlow INTEGER,
	durationhigh INTEGER,
	maxduration INTEGER,
	hepaticimpairmentind SMALLINT,
	renalimpairmentind SMALLINT,
	crclthreshhold SMALLINT,
	crclthreshholdunits VARCHAR(20),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	gabrequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(20),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(20),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(20),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(20),
	maxsingledoserate NUMERIC(16, 6),
	maxsingledoserateunits VARCHAR(20),
	maxsingledoseformrate NUMERIC(16, 6),
	maxsingledoseformrateunits VARCHAR(20),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(20),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(20),
	dxid VARCHAR(8), 
	constraint pkfdbdrcneo primary key (gcnseqno, agelowindays, agehighindays, doserouteid, dosetypeid, fdbdx, gablowinweeks, gabhighinweeks, weightlowingrams, weighthighingrams)
);

create table fdb_drug_monograph_ext (
	concepttype SMALLINT not null,
	conceptid VARCHAR(13) not null,
	versioncode VARCHAR(10) not null,
	monographid INTEGER not null, 
	constraint pkfdbdrugmonographext primary key (conceptid, concepttype, versioncode, monographid)
);

create table fdb_drug_pcm (
	concepttype SMALLINT not null,
	conceptid VARCHAR(11) not null,
	languageid SMALLINT not null,
	pcmid SMALLINT not null,
	priority SMALLINT, 
	constraint pkfdbdrugpcm primary key (conceptid, concepttype, languageid, pcmid)
);

create table fdb_drug_plblw (
	concepttype SMALLINT not null,
	conceptid VARCHAR(11) not null,
	lwid VARCHAR(4) not null,
	priority SMALLINT, 
	constraint pkfdbdrugplblw primary key (conceptid, concepttype, lwid)
);

create table fdb_drug_rtgenid (
	drugid VARCHAR(12) not null,
	rtgenid INTEGER not null, 
	constraint pkfdbdrugrtgenid primary key (drugid, rtgenid)
);

create index ixfdbdrugrtgenid1 on fdb_drug_rtgenid (rtgenid);

create table fdb_drugname (
	mnid INTEGER not null,
	descdisplay VARCHAR(35),
	descsearch VARCHAR(35),
	descaltsearch VARCHAR(35),
	hicl_unique INTEGER,
	rmid_unique INTEGER,
	rdfmid_unique INTEGER,
	medid_unique INTEGER,
	nametypecode VARCHAR(1),
	reffederallegendcode VARCHAR(1),
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	obsoletedate VARCHAR(8),
	replacedindicator SMALLINT,
	clinicallinkind SMALLINT,
	haspackageddrugsind SMALLINT,
	hasequivpackageddrugsind SMALLINT,
	genericmnid INTEGER,
	statuscode VARCHAR(1),
	tm_descdisplay VARCHAR(70),
	tm_confusiongroupid INTEGER,
	tm_groupdesc VARCHAR(250),
	tm_sourcecode VARCHAR(1), 
	constraint pkfdbdrugname primary key (mnid)
);

create index ixfdbdrugname1 on fdb_drugname (descaltsearch);

create index ixfdbdrugname2 on fdb_drugname (descsearch);

create table fdb_drugvalidation (
	drugid VARCHAR(22) not null,
	rtgenid INTEGER not null,
	rtdfgenid INTEGER not null,
	gcnseqno INTEGER,
	hicl INTEGER,
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdrc SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	singleingredientind SMALLINT,
	clinicallinkind SMALLINT,
	singledoserouteind SMALLINT,
	hasgestagedosingind SMALLINT,
	statuscode VARCHAR(1),
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	tm_descdisplay VARCHAR(125),
	percentsurveyed NUMERIC(16, 6),
	description1 VARCHAR(255),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255), 
	constraint pkfdbdrugvalidation primary key (drugid, rtgenid, rtdfgenid)
);

create index ixfdbdrugvalidation1 on fdb_drugvalidation (rtdfgenid);

create table fdb_dtdruglink (
	drugid VARCHAR(12) not null,
	dtcid INTEGER not null,
	rtgenid INTEGER, 
	constraint pkfdbdtdruglink primary key (drugid, dtcid)
);

create table fdb_duplicatetherapy (
	dtcid INTEGER not null,
	description1 VARCHAR(60),
	description2 VARCHAR(180),
	description3 VARCHAR(180),
	description4 VARCHAR(180),
	description5 VARCHAR(180),
	description6 VARCHAR(180),
	duplicationallowance SMALLINT, 
	constraint pkfdbduplicatetherapy primary key (dtcid)
);

create table fdb_dxid_relateddxid (
	dxid VARCHAR(8) not null,
	relateddxid VARCHAR(8) not null,
	clinicalcode VARCHAR(2) not null,
	navcode VARCHAR(2),
	fdbdx VARCHAR(9), 
	constraint pkfdbdxidrelateddxid primary key (dxid, relateddxid, clinicalcode)
);

create index ixfdbdxidrelateddxid1 on fdb_dxid_relateddxid (dxid);

create index ixfdbdxidrelateddxid2 on fdb_dxid_relateddxid (relateddxid);

create table fdb_etclassification_drugs (
	classid INTEGER not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	commonuseind SMALLINT,
	defaultuseind SMALLINT, 
	constraint pkfdbetclassificationdrugs primary key (classid, conceptid, concepttype)
);

create table fdb_etclassification_link (
	classid INTEGER not null,
	linkedchild INTEGER not null, 
	constraint pkfdbetclassificationlink primary key (classid, linkedchild)
);

create table fdb_extclassification_drugs (
	classtypecode VARCHAR(2) not null,
	classid VARCHAR(15) not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	commonuseind SMALLINT,
	defaultuseind SMALLINT, 
	constraint pkfdbextclassificationdrugs primary key (classtypecode, classid, conceptid, concepttype)
);

create table fdb_extclassification_idrugs (
	classtypecode VARCHAR(2) not null,
	classid VARCHAR(15) not null,
	concepttype SMALLINT not null,
	conceptid VARCHAR(11) not null,
	commonuseind SMALLINT,
	defaultuseind SMALLINT, 
	constraint pkfdbextclassificationidrugs primary key (classtypecode, classid, conceptid, concepttype)
);

create table fdb_extclassification_link (
	classtypecode VARCHAR(2) not null,
	classid VARCHAR(15) not null,
	linkedchild VARCHAR(15) not null, 
	constraint pkfdbextclassificationlink primary key (classtypecode, classid, linkedchild)
);

create table fdb_extvocab_relateddxid (
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	relateddxid VARCHAR(8) not null,
	clinicalcode VARCHAR(2) not null,
	navcode VARCHAR(2),
	prefhexid VARCHAR(44), 
	constraint pkfdbextvocabrelateddxid primary key (vocabtypecode, hexid, relateddxid, clinicalcode)
);

create index ixfdbextvocabrelateddxid1 on fdb_extvocab_relateddxid (relateddxid);

create index ixfdbextvocabrelateddxid2 on fdb_extvocab_relateddxid (vocabtypecode);

create index ixfdbextvocabrelateddxid3 on fdb_extvocab_relateddxid (prefhexid, relateddxid, clinicalcode, navcode);

create table fdb_fdbclass_drugconcept (
	fdbclassid INTEGER not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null, 
	constraint pkfdbfdbclassdrugconcept primary key (fdbclassid, conceptid, concepttype)
);

create index ixfdbfdbclassdrugconcept1 on fdb_fdbclass_drugconcept (conceptid);

create table fdb_fdbclassification_drugs (
	classid INTEGER not null,
	concepttype SMALLINT not null,
	conceptid INTEGER not null,
	commonuseind SMALLINT,
	defaultuseind SMALLINT, 
	constraint pkfdbfdbclassificationdrugs primary key (classid, conceptid, concepttype)
);

create table fdb_fdbclassification_link (
	classid INTEGER not null,
	linkedchild INTEGER not null, 
	constraint pkfdbfdbclassificationlink primary key (classid, linkedchild)
);

create table fdb_gcnseqno_pcm (
	gcnseqno INTEGER not null,
	languageid SMALLINT not null,
	pcmid SMALLINT not null,
	priority SMALLINT, 
	constraint pkfdbgcnseqnopcm primary key (gcnseqno, languageid, pcmid)
);

create table fdb_gcnseqno_pem (
	gcnseqno INTEGER not null,
	monographid INTEGER not null, 
	constraint pkfdbgcnseqnopem primary key (gcnseqno, monographid)
);

create table fdb_gcnseqno_plblw (
	gcnseqno INTEGER not null,
	lwid VARCHAR(4) not null,
	priority SMALLINT, 
	constraint pkfdbgcnseqnoplblw primary key (gcnseqno, lwid)
);

create table fdb_generic_dispensable (
	gcnseqno INTEGER not null,
	description1 VARCHAR(185),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255),
	strength1 VARCHAR(10),
	strength2 VARCHAR(30),
	strength3 VARCHAR(30),
	strength4 VARCHAR(30),
	strength5 VARCHAR(30),
	strength6 VARCHAR(30),
	rtid INTEGER,
	gendfid SMALLINT,
	rtdfgenid INTEGER,
	rtgenid INTEGER,
	hicl INTEGER,
	genderspecificdrugcode VARCHAR(1),
	genericmedid INTEGER,
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	singledoserouteind SMALLINT,
	regionalind SMALLINT,
	rxotccode VARCHAR(1),
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdrc SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	hasgestagedosingind SMALLINT,
	numstrength NUMERIC(16, 6),
	numstrengthuom VARCHAR(10),
	numvolume NUMERIC(16, 6),
	numvolumeuom VARCHAR(5),
	haspackageddrugsind SMALLINT,
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	inactiveingredientreviewind SMALLINT,
	safestrength1 VARCHAR(60),
	safestrength2 VARCHAR(180),
	safestrength3 VARCHAR(180),
	safestrength4 VARCHAR(180),
	safestrength5 VARCHAR(180),
	safestrength6 VARCHAR(180),
	safenumstrengthuom VARCHAR(50),
	safenumvolumeuom VARCHAR(50), 
	constraint pkfdbgenericdispensable primary key (gcnseqno)
);

create table fdb_generic_dispensable_temp (
	tempid INTEGER not null,
	gcnseqno INTEGER, 
	constraint pkfdbgenericdispensabletemp primary key (tempid)
);

create table fdb_generic_dispsearch (
	languageid SMALLINT not null,
	gcnseqno INTEGER not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbgenericdispsearch primary key (languageid, gcnseqno, nametypecode, sequencenumber)
);

create index ixfdbgenericdispsearch1 on fdb_generic_dispsearch (descsearch);

create index ixfdbgenericdispsearch2 on fdb_generic_dispsearch (descaltsearch);

create table fdb_generic_drugname (
	hicl INTEGER not null,
	description1 VARCHAR(60),
	description2 VARCHAR(180),
	description3 VARCHAR(180),
	description4 VARCHAR(180),
	description5 VARCHAR(180),
	description6 VARCHAR(180),
	rtgenid_unique INTEGER,
	rtdfgenid_unique INTEGER,
	gcnseqno_unique INTEGER,
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	regionalind SMALLINT,
	rxotccode VARCHAR(1),
	compid VARCHAR(6),
	haspackageddrugsind SMALLINT, 
	constraint pkfdbgenericdrugname primary key (hicl)
);

create table fdb_generic_drugnamesearch (
	languageid SMALLINT not null,
	hicl INTEGER not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(185),
	descaltsearch VARCHAR(185),
	descdisplay VARCHAR(180),
	baselangid SMALLINT, 
	constraint pkfdbgenericdrugnamesearch primary key (languageid, hicl, nametypecode, sequencenumber)
);

create index ixfdbgenericdrugnamesearch1 on fdb_generic_drugnamesearch (descsearch);

create index ixfdbgenericdrugnamesearch2 on fdb_generic_drugnamesearch (descaltsearch);

create table fdb_generic_routeddfdrug (
	rtdfgenid INTEGER not null,
	description1 VARCHAR(125),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255),
	rtid INTEGER,
	gendfid SMALLINT,
	rtgenid INTEGER,
	hicl INTEGER,
	gcnseqno_unique INTEGER,
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	regionalind SMALLINT,
	rxotccode VARCHAR(1),
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	statusdrc SMALLINT,
	singledoserouteind SMALLINT,
	canbedosedind SMALLINT,
	haspackageddrugsind SMALLINT,
	neocanbedosedind SMALLINT,
	neosingledoserouteind SMALLINT, 
	constraint pkfdbgenericrouteddfdrug primary key (rtdfgenid)
);

create table fdb_generic_routeddfdrugsearch (
	languageid SMALLINT not null,
	rtdfgenid INTEGER not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbgenericrouteddfdrugsearch primary key (languageid, rtdfgenid, nametypecode, sequencenumber)
);

create index ixfdbgenericrouteddfdrugsea1 on fdb_generic_routeddfdrugsearch (descsearch);

create index ixfdbgenericrouteddfdrugsea2 on fdb_generic_routeddfdrugsearch (descaltsearch);

create table fdb_generic_routeddrug (
	rtgenid INTEGER not null,
	rtgenidstring VARCHAR(8),
	description1 VARCHAR(95),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255),
	rtid INTEGER,
	hicl INTEGER,
	gcnseqno_unique INTEGER,
	rtdfgenid_unique INTEGER,
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	regionalind SMALLINT,
	rxotccode VARCHAR(1),
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	statusdrc SMALLINT,
	singledoserouteind SMALLINT,
	canbedosedind SMALLINT,
	haspackageddrugsind SMALLINT,
	neocanbedosedind SMALLINT,
	neosingledoserouteind SMALLINT, 
	constraint pkfdbgenericrouteddrug primary key (rtgenid)
);

create table fdb_generic_routeddrug_temp (
	tempid INTEGER not null,
	rtgenid INTEGER, 
	constraint pkfdbgenericrouteddrugtemp primary key (tempid)
);

create table fdb_generic_routeddrugsearch (
	languageid SMALLINT not null,
	rtgenid INTEGER not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbgenericrouteddrugsearch primary key (languageid, rtgenid, nametypecode, sequencenumber)
);

create index ixfdbgenericrouteddrugsearc1 on fdb_generic_routeddrugsearch (descsearch);

create index ixfdbgenericrouteddrugsearc2 on fdb_generic_routeddrugsearch (descaltsearch);

create table fdb_genericdoseform (
	gendfid SMALLINT not null,
	gendfcode VARCHAR(2),
	description1 VARCHAR(30),
	description2 VARCHAR(90),
	description3 VARCHAR(90),
	description4 VARCHAR(90),
	description5 VARCHAR(90),
	description6 VARCHAR(90), 
	constraint pkfdbgenericdoseform primary key (gendfid)
);

create table fdb_hicl_hicseqno (
	hicl INTEGER not null,
	hicseqno INTEGER not null, 
	constraint pkfdbhiclhicseqno primary key (hicl, hicseqno)
);

create table fdb_hicseqno_agid (
	hicseqno INTEGER not null,
	agid INTEGER not null, 
	constraint pkfdbhicseqnoagid primary key (hicseqno, agid)
);

create table fdb_hicseqno_axsid (
	hicseqno INTEGER not null,
	axsid SMALLINT not null, 
	constraint pkfdbhicseqnoaxsid primary key (hicseqno, axsid)
);

create table fdb_hicseqno_baseingredient (
	hicseqno INTEGER not null,
	baseingredientid INTEGER, 
	constraint pkfdbhicseqnobaseingredient primary key (hicseqno)
);

create index ixfdbhicseqnobaseingredient1 on fdb_hicseqno_baseingredient (baseingredientid);

create table fdb_image (
	pmid VARCHAR(20) not null,
	startdate VARCHAR(8) not null,
	stopdate VARCHAR(8),
	imagefilename VARCHAR(8), 
	constraint pkfdbimage primary key (pmid, startdate)
);

create table fdb_impcolorpalette (
	paletteid SMALLINT not null,
	description VARCHAR(30), 
	constraint pkfdbimpcolorpalette primary key (paletteid)
);

create table fdb_impdesccolors (
	pmid VARCHAR(20) not null,
	startdate VARCHAR(8) not null,
	colorid SMALLINT not null, 
	constraint pkfdbimpdesccolors primary key (pmid, startdate, colorid)
);

create table fdb_imprint (
	pmid VARCHAR(20) not null,
	startdate VARCHAR(8) not null,
	imprint1 VARCHAR(40),
	imprint2 VARCHAR(40),
	shapeid SMALLINT,
	flavorcode VARCHAR(4),
	formcode VARCHAR(4),
	coatingcode VARCHAR(4),
	scoringcode VARCHAR(4),
	claritycode VARCHAR(4),
	searchdescription VARCHAR(85),
	currentimprintind SMALLINT,
	colorpaletteidlist VARCHAR(32), 
	constraint pkfdbimprint primary key (pmid, startdate)
);

create index ixfdbimprint1 on fdb_imprint (searchdescription);

create table fdb_imprintcolors (
	colorid SMALLINT not null,
	description VARCHAR(30),
	paletteid SMALLINT, 
	constraint pkfdbimprintcolors primary key (colorid)
);

create table fdb_imprintdescription (
	pmid VARCHAR(20) not null,
	startdate VARCHAR(8) not null,
	imptextid INTEGER not null, 
	constraint pkfdbimprintdescription primary key (pmid, startdate, imptextid)
);

create table fdb_imprintdesctext (
	imptextid INTEGER not null,
	linenumber SMALLINT not null,
	linetext VARCHAR(70), 
	constraint pkfdbimprintdesctext primary key (imptextid, linenumber)
);

create table fdb_imprintshape (
	shapeid SMALLINT not null,
	description VARCHAR(30),
	paletteid SMALLINT, 
	constraint pkfdbimprintshape primary key (shapeid)
);

create table fdb_impshapepalette (
	paletteid SMALLINT not null,
	description VARCHAR(30), 
	constraint pkfdbimpshapepalette primary key (paletteid)
);

create table fdb_ind (
	indid INTEGER not null,
	sequencenumber SMALLINT not null,
	descdrug1 VARCHAR(100),
	descdrug2 VARCHAR(255),
	descdrug3 VARCHAR(255),
	descdrug4 VARCHAR(255),
	descdrug5 VARCHAR(255),
	descdrug6 VARCHAR(255),
	descindication1 VARCHAR(100),
	descindication2 VARCHAR(255),
	descindication3 VARCHAR(255),
	descindication4 VARCHAR(255),
	descindication5 VARCHAR(255),
	descindication6 VARCHAR(255),
	labeledcode VARCHAR(1),
	fdbdx VARCHAR(9),
	dxid VARCHAR(8),
	predictorcode VARCHAR(1), 
	constraint pkfdbind primary key (indid, sequencenumber)
);

create table fdb_inddruglink (
	drugid VARCHAR(12) not null,
	indid INTEGER not null,
	gcnseqno INTEGER,
	rtdfgenid INTEGER,
	rtgenid INTEGER, 
	constraint pkfdbinddruglink primary key (drugid, indid)
);

create index ixfdbinddruglink on fdb_inddruglink (gcnseqno);

create table fdb_ingredient (
	hicseqno INTEGER not null,
	description1 VARCHAR(100),
	description2 VARCHAR(150),
	description3 VARCHAR(150),
	description4 VARCHAR(150),
	description5 VARCHAR(150),
	description6 VARCHAR(150),
	allergengroupind SMALLINT,
	statuscode VARCHAR(1),
	potentiallyinactiveind SMALLINT, 
	constraint pkfdbingredient primary key (hicseqno)
);

create table fdb_ingredientsearch (
	languageid SMALLINT not null,
	hicseqno INTEGER not null,
	descsearch VARCHAR(155),
	descaltsearch VARCHAR(155),
	baselangid SMALLINT, 
	constraint pkfdbingredientsearch primary key (languageid, hicseqno)
);

create index ixfdbingredientsearch1 on fdb_ingredientsearch (descsearch);

create index ixfdbingredientsearch2 on fdb_ingredientsearch (descaltsearch);

create table fdb_ivm (
	grouptestid VARCHAR(10) not null,
	admix VARCHAR(6) not null,
	compid VARCHAR(6) not null,
	strength NUMERIC(16, 6),
	struom VARCHAR(3),
	volume NUMERIC(16, 6),
	voluom VARCHAR(3),
	mfgid VARCHAR(3),
	testtypecode VARCHAR(1),
	resultcode VARCHAR(1), 
	constraint pkfdbivm primary key (grouptestid, admix, compid)
);

create table fdb_ivm_compdesc (
	compid VARCHAR(6) not null,
	description VARCHAR(50),
	tpnind SMALLINT, 
	constraint pkfdbivmcompdesc primary key (compid)
);

create table fdb_ivm_mfgdesc (
	mfgid VARCHAR(3) not null,
	description VARCHAR(50), 
	constraint pkfdbivmmfgdesc primary key (mfgid)
);

create table fdb_ivm_remarklink (
	grouptestid VARCHAR(10) not null,
	seqno SMALLINT not null,
	remarkid VARCHAR(6), 
	constraint pkfdbivmremarklink primary key (grouptestid, seqno)
);

create index ixfdbivmremarklink1 on fdb_ivm_remarklink (grouptestid);

create index ixfdbivmremarklink2 on fdb_ivm_remarklink (remarkid);

create table fdb_ivm_remarks (
	remarkid VARCHAR(6) not null,
	remark VARCHAR(255), 
	constraint pkfdbivmremarks primary key (remarkid)
);

create table fdb_ivm_testcompcount (
	grouptestid VARCHAR(10) not null,
	compidcount SMALLINT, 
	constraint pkfdbivmtestcompcount primary key (grouptestid)
);

create table fdb_ivm_tpn_ingred (
	compid VARCHAR(6) not null,
	seqno SMALLINT not null,
	descdisplay VARCHAR(50),
	descsearch VARCHAR(55),
	descaltsearch VARCHAR(55),
	strength NUMERIC(16, 6),
	struom VARCHAR(3),
	volume NUMERIC(16, 6),
	voluom VARCHAR(3),
	mfgid VARCHAR(3), 
	constraint pkfdbivmtpningred primary key (compid, seqno)
);

create table fdb_labeler (
	labelerid VARCHAR(6) not null,
	mfgname VARCHAR(100),
	ndcmfgcode VARCHAR(5), 
	constraint pkfdblabeler primary key (labelerid)
);

create table fdb_language (
	languageid SMALLINT not null,
	baselangid SMALLINT,
	description VARCHAR(50),
	regionaldescription VARCHAR(150),
	abbrev VARCHAR(10),
	columnindex SMALLINT,
	altsearchalgorithm SMALLINT, 
	constraint pkfdblanguage primary key (languageid)
);

create table fdb_manufdrug (
	imdid VARCHAR(11) not null,
	description1 VARCHAR(255),
	description2 VARCHAR(90),
	description3 VARCHAR(90),
	description4 VARCHAR(90),
	description5 VARCHAR(90),
	description6 VARCHAR(90),
	addldescriptor1 VARCHAR(20),
	addldescriptor2 VARCHAR(60),
	addldescriptor3 VARCHAR(60),
	addldescriptor4 VARCHAR(60),
	addldescriptor5 VARCHAR(60),
	addldescriptor6 VARCHAR(60),
	labelerid VARCHAR(6),
	strength1 VARCHAR(10),
	strength2 VARCHAR(30),
	strength3 VARCHAR(30),
	strength4 VARCHAR(30),
	strength5 VARCHAR(30),
	strength6 VARCHAR(30),
	medid INTEGER,
	gcnseqno INTEGER,
	rtid INTEGER,
	gendfid INTEGER,
	rtdfgenid INTEGER,
	rtgenid INTEGER,
	hicl INTEGER,
	medicaldeviceind SMALLINT,
	singledoserouteind SMALLINT,
	singleingredientind SMALLINT,
	obsoletedate VARCHAR(8),
	numstrength NUMERIC(16, 6),
	numstrengthuom VARCHAR(10),
	numvolume NUMERIC(16, 6),
	numvolumeuom VARCHAR(5),
	adddate VARCHAR(8),
	statuscode VARCHAR(1),
	genericimdid VARCHAR(11),
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	inactiveingredientreviewind SMALLINT,
	safestrength1 VARCHAR(60),
	safestrength2 VARCHAR(180),
	safestrength3 VARCHAR(180),
	safestrength4 VARCHAR(180),
	safestrength5 VARCHAR(180),
	safestrength6 VARCHAR(180),
	safenumstrengthuom VARCHAR(50),
	safenumvolumeuom VARCHAR(50), 
	constraint pkfdbmanufdrug primary key (imdid)
);

create table fdb_manufdrugextid (
	extidtype SMALLINT not null,
	hexid VARCHAR(40) not null,
	extid VARCHAR(20),
	imdid VARCHAR(11), 
	constraint pkfdbmanufdrugextid primary key (extidtype, hexid)
);

create table fdb_manufdrugpropname (
	propertyname VARCHAR(32) not null,
	description VARCHAR(50),
	regionaldescription VARCHAR(150), 
	constraint pkfdbmanufdrugpropname primary key (propertyname)
);

create table fdb_manufdrugpropvalue (
	imdid VARCHAR(11) not null,
	propertyname VARCHAR(32) not null,
	sequencenumber SMALLINT not null,
	propertyvalue VARCHAR(255), 
	constraint pkfdbmanufdrugpropvalue primary key (imdid, propertyname, sequencenumber)
);

create table fdb_manufdrugsearch (
	languageid SMALLINT not null,
	imdid VARCHAR(11) not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbmanufdrugsearch primary key (languageid, imdid, nametypecode, sequencenumber)
);

create index ixfdbmanufdrugsearch1 on fdb_manufdrugsearch (descsearch);

create index ixfdbmanufdrugsearch2 on fdb_manufdrugsearch (descaltsearch);

create table fdb_medcond (
	fdbdx VARCHAR(9) not null,
	dxid VARCHAR(8),
	description1 VARCHAR(100),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255),
	acutechroniccode VARCHAR(1),
	hasdrugstotreatind SMALLINT,
	hasdrugstoavoidind SMALLINT,
	hasdrugsthatcauseind SMALLINT,
	diseasedurcode VARCHAR(1),
	dxidstatuscode VARCHAR(1),
	toplevelind SMALLINT,
	lowestlevelind SMALLINT,
	drugstotreatind SMALLINT,
	drugstoavoidind SMALLINT,
	drugsthatcauseind SMALLINT, 
	constraint pkfdbmedcond primary key (fdbdx)
);

create index ixfdbmedcond1 on fdb_medcond (dxid);

create table fdb_medcond_hierarchy (
	dxid VARCHAR(8) not null,
	broaderdxid VARCHAR(8) not null,
	toplevelrelind SMALLINT not null, 
	constraint pkfdbmedcondhierarchy primary key (dxid, broaderdxid, toplevelrelind)
);

create table fdb_medcondext_hierarchy (
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	broaderhexid VARCHAR(40) not null,
	toplevelrelind SMALLINT not null,
	extvocabid VARCHAR(20),
	broaderextvocabid VARCHAR(20), 
	constraint pkfdbmedcondexthierarchy primary key (vocabtypecode, hexid, broaderhexid, toplevelrelind)
);

create table fdb_medcondextvocab (
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	extvocabid VARCHAR(20),
	descprimary1 VARCHAR(255),
	descprimary2 VARCHAR(50),
	descprimary3 VARCHAR(50),
	descprimary4 VARCHAR(50),
	descprimary5 VARCHAR(50),
	descprimary6 VARCHAR(50),
	descsecondary1 VARCHAR(255),
	descsecondary2 VARCHAR(50),
	descsecondary3 VARCHAR(50),
	descsecondary4 VARCHAR(50),
	descsecondary5 VARCHAR(50),
	descsecondary6 VARCHAR(50),
	desctertiary1 VARCHAR(255),
	desctertiary2 VARCHAR(50),
	desctertiary3 VARCHAR(50),
	desctertiary4 VARCHAR(50),
	desctertiary5 VARCHAR(50),
	desctertiary6 VARCHAR(50),
	descabbrev1 VARCHAR(50),
	descabbrev2 VARCHAR(50),
	descabbrev3 VARCHAR(50),
	descabbrev4 VARCHAR(50),
	descabbrev5 VARCHAR(50),
	descabbrev6 VARCHAR(50),
	extvocabnametypecode VARCHAR(2),
	clinicallinkind SMALLINT,
	parentind SMALLINT,
	hasdrugstotreatind SMALLINT,
	hasdrugstoavoidind SMALLINT,
	hasdrugsthatcauseind SMALLINT,
	toplevelind SMALLINT,
	lowestlevelind SMALLINT,
	drugstotreatind SMALLINT,
	drugstoavoidind SMALLINT,
	drugsthatcauseind SMALLINT,
	druglinkind SMALLINT, 
	constraint pkfdbmedcondextvocab primary key (vocabtypecode, hexid)
);

create index ixfdbmedcondextvocab1 on fdb_medcondextvocab (extvocabid);

create index ixfdbmedcondextvocab2 on fdb_medcondextvocab (hexid);

create table fdb_medcondextvocablink (
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	fdbdx VARCHAR(9) not null,
	dxid VARCHAR(8),
	extvocabid VARCHAR(20), 
	constraint pkfdbmedcondextvocablink primary key (vocabtypecode, hexid, fdbdx)
);

create table fdb_medcondextvocabsearch (
	languageid SMALLINT not null,
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	extvocabid VARCHAR(20),
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbmedcondextvocabsearch primary key (languageid, vocabtypecode, hexid)
);

create index ixfdbmedcondextvocabsearch1 on fdb_medcondextvocabsearch (descsearch);

create index ixfdbmedcondextvocabsearch2 on fdb_medcondextvocabsearch (descaltsearch);

create table fdb_medcondsearch (
	languageid SMALLINT not null,
	fdbdx VARCHAR(9) not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbmedcondsearch primary key (languageid, fdbdx)
);

create index ixfdbmedcondsearch1 on fdb_medcondsearch (descsearch);

create index ixfdbmedcondsearch2 on fdb_medcondsearch (descaltsearch);

create table fdb_medcondsearchfml (
	languageid SMALLINT not null,
	dxid VARCHAR(8) not null,
	synid INTEGER not null,
	syntypecode VARCHAR(2),
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(100),
	baselangid SMALLINT, 
	constraint pkfdbmedcondsearchfml primary key (languageid, dxid, synid)
);

create index ixfdbmedcondsearchfml1 on fdb_medcondsearchfml (descsearch);

create index ixfdbmedcondsearchfml2 on fdb_medcondsearchfml (descaltsearch);

create table fdb_medcondxref (
	vocabtypecode VARCHAR(1) not null,
	hexid VARCHAR(40) not null,
	vocabtypecode2 VARCHAR(1) not null,
	hexid2 VARCHAR(40) not null,
	vocabid VARCHAR(20),
	vocabid2 VARCHAR(20), 
	constraint pkfdbmedcondxref primary key (vocabtypecode, hexid, vocabtypecode2, hexid2)
);

create index ixfdbmedcondxref1 on fdb_medcondxref (hexid);

create index ixfdbmedcondxref2 on fdb_medcondxref (hexid2);

create table fdb_minmaxdosing (
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	doselow NUMERIC(16, 6),
	doselowunits VARCHAR(30),
	dosehigh NUMERIC(16, 6),
	dosehighunits VARCHAR(30),
	maxdailydose NUMERIC(16, 6),
	maxdailydoseunits VARCHAR(30),
	doseformlow NUMERIC(16, 6),
	doseformlowunits VARCHAR(30),
	doseformhigh NUMERIC(16, 6),
	doseformhighunits VARCHAR(30),
	maxdailydoseform NUMERIC(16, 6),
	maxdailydoseformunits VARCHAR(30),
	weightrequiredind SMALLINT,
	bsarequiredind SMALLINT,
	doseratelow NUMERIC(16, 6),
	doseratelowunits VARCHAR(30),
	doseratehigh NUMERIC(16, 6),
	doseratehighunits VARCHAR(30),
	maxdailydoserate NUMERIC(16, 6),
	maxdailydoserateunits VARCHAR(30),
	doseformratelow NUMERIC(16, 6),
	doseformratelowunits VARCHAR(30),
	doseformratehigh NUMERIC(16, 6),
	doseformratehighunits VARCHAR(30),
	maxdailydoseformrate NUMERIC(16, 6),
	maxdailydoseformrateunits VARCHAR(30), 
	constraint pkfdbminmaxdosing primary key (gcnseqno, agelowindays, agehighindays)
);

create table fdb_minmaxwarnings (
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	warningcode VARCHAR(1), 
	constraint pkfdbminmaxwarnings primary key (gcnseqno, agelowindays, agehighindays)
);

create table fdb_mnid_hicl (
	mnid INTEGER not null,
	hicl INTEGER not null, 
	constraint pkfdbmnidhicl primary key (mnid, hicl)
);

create index ixfdbmnidhicl1 on fdb_mnid_hicl (hicl);

create table fdb_monograph_ddim (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	monographid INTEGER not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(1),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbmonographddim primary key (languageid, versioncode, monographid, sequencenumber)
);

create table fdb_monograph_dfim (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	monographid INTEGER not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(1),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbmonographdfim primary key (languageid, versioncode, monographid, sequencenumber)
);

create table fdb_monograph_ext (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	monographid INTEGER not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(3),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbmonographext primary key (languageid, versioncode, monographid, sequencenumber)
);

create table fdb_monograph_pem (
	languageid SMALLINT not null,
	versioncode VARCHAR(10) not null,
	monographid INTEGER not null,
	sequencenumber SMALLINT not null,
	sectioncode VARCHAR(1),
	formatcode VARCHAR(1),
	linetext VARCHAR(255), 
	constraint pkfdbmonographpem primary key (languageid, versioncode, monographid, sequencenumber)
);

create table fdb_packageddrug (
	pmid VARCHAR(20) not null,
	descdisplay VARCHAR(35),
	descsearch VARCHAR(35),
	descaltsearch VARCHAR(35),
	mnid INTEGER,
	rtid INTEGER,
	dfid INTEGER,
	strength VARCHAR(15),
	strengthunits VARCHAR(15),
	labelerid VARCHAR(6),
	medid INTEGER,
	rdfmid INTEGER,
	rmid INTEGER,
	gcnseqno INTEGER,
	rtgenid INTEGER,
	hicl INTEGER,
	previousndc VARCHAR(20),
	replacementndc VARCHAR(20),
	bbawpchangedate VARCHAR(8),
	formatcode VARCHAR(1),
	adddate VARCHAR(8),
	updatedate VARCHAR(8),
	desicode VARCHAR(1),
	desieffectivedate VARCHAR(8),
	desi2code VARCHAR(1),
	desi2effectivedate VARCHAR(8),
	federaldeaclasscode VARCHAR(1),
	federallegendcode VARCHAR(1),
	gencomppricecode VARCHAR(1),
	gencomppricechangedate VARCHAR(8),
	hospitalind SMALLINT,
	innovatorind SMALLINT,
	homehealthind SMALLINT,
	institutionalprodind SMALLINT,
	maintenancedrugind SMALLINT,
	miniind SMALLINT,
	patientpkginsertind SMALLINT,
	top200rank VARCHAR(3),
	top50genrank VARCHAR(2),
	multisourcecode VARCHAR(1),
	genmfgcode VARCHAR(1),
	gennameddrugcode VARCHAR(1),
	genpricespreadcode VARCHAR(1),
	gentherequivcode VARCHAR(1),
	privlabeledprodind SMALLINT,
	repackagerind SMALLINT,
	orangebookcode VARCHAR(3),
	innerpackind SMALLINT,
	outerpackind SMALLINT,
	packagedescription VARCHAR(10),
	packagesize NUMERIC(16, 6),
	packagesizeunitscode VARCHAR(1),
	thirdpartyrestrictioncode VARCHAR(1),
	unitdosepackagingind SMALLINT,
	unitofusepackagingind SMALLINT,
	adddescriptor VARCHAR(20),
	packagequantity INTEGER,
	packagesizeequiv NUMERIC(16, 6),
	needlegauge NUMERIC(16, 6),
	needlelength NUMERIC(16, 6),
	syringecapacity NUMERIC(16, 6),
	shelfpack INTEGER,
	shipperpack INTEGER,
	standardpackind SMALLINT,
	labelname25 VARCHAR(25),
	labelname25gennamecode VARCHAR(1),
	hcfafdaapprovaldate VARCHAR(8),
	hcfadrugcategorycode VARCHAR(1),
	hcfadrugtypecode VARCHAR(1),
	hcfamarketentrydate VARCHAR(8),
	hcfaterminationdate VARCHAR(8),
	hcfafdatheraequivcode VARCHAR(2),
	hcfaunitsperpackage NUMERIC(16, 6),
	hcfaunittypecode VARCHAR(3),
	hcfacommonproccode VARCHAR(5),
	nametypecode VARCHAR(1),
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	obsoletedate VARCHAR(8),
	ndc VARCHAR(13),
	currentimagefilename VARCHAR(8),
	genericmnid INTEGER,
	gcrtsystemic VARCHAR(1),
	ndcformatted VARCHAR(13),
	numstrength NUMERIC(16, 6),
	numstrengthuom VARCHAR(10),
	numvolume NUMERIC(16, 6),
	numvolumeuom VARCHAR(5),
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	inactiveingredientreviewind SMALLINT,
	safedescdisplay VARCHAR(60),
	safedescsearch VARCHAR(65),
	safedescaltsearch VARCHAR(65),
	safenumstrengthuom VARCHAR(50),
	safenumvolumeuom VARCHAR(50), 
	constraint pkfdbpackageddrug primary key (pmid)
);

create index ixfdbpackageddrug1 on fdb_packageddrug (medid);

create index ixfdbpackageddrug2 on fdb_packageddrug (descsearch);

create index ixfdbpackageddrug3 on fdb_packageddrug (descaltsearch);

create index ixfdbpackageddrug4 on fdb_packageddrug (safedescsearch);

create index ixfdbpackageddrug5 on fdb_packageddrug (safedescaltsearch);

create table fdb_packageddrugcurrentpricing (
	pmid VARCHAR(20) not null,
	pricetypecode VARCHAR(2) not null,
	currenteffectivedate VARCHAR(8),
	price NUMERIC(16, 6), 
	constraint pkfdbpackageddrugcurrentpricin primary key (pmid, pricetypecode)
);

create table fdb_packageddrugpricinghistory (
	pmid VARCHAR(20) not null,
	pricetypecode VARCHAR(2) not null,
	effectivedate VARCHAR(8) not null,
	price NUMERIC(16, 6), 
	constraint pkfdbpackageddrugpricinghistor primary key (pmid, pricetypecode, effectivedate)
);

create table fdb_patientcounseling (
	languageid SMALLINT not null,
	pcmid SMALLINT not null,
	profmessage1 VARCHAR(102),
	profmessage2 VARCHAR(102),
	patmessage1 VARCHAR(75),
	patmessage2 VARCHAR(75), 
	constraint pkfdbpatientcounseling primary key (languageid, pcmid)
);

create table fdb_pediatricweight (
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	agedesc VARCHAR(30),
	male5thpctweight NUMERIC(16, 6),
	male25thpctweight NUMERIC(16, 6),
	male50thpctweight NUMERIC(16, 6),
	male75thpctweight NUMERIC(16, 6),
	male95thpctweight NUMERIC(16, 6),
	female5thpctweight NUMERIC(16, 6),
	female25thpctweight NUMERIC(16, 6),
	female50thpctweight NUMERIC(16, 6),
	female75thpctweight NUMERIC(16, 6),
	female95thpctweight NUMERIC(16, 6), 
	constraint pkfdbpediatricweight primary key (agelowindays, agehighindays)
);

create table fdb_plblwarn_vendcode (
	lwid VARCHAR(4) not null,
	vendversioncode VARCHAR(3) not null,
	vendorcode VARCHAR(10), 
	constraint pkfdbplblwarnvendcode primary key (lwid, vendversioncode)
);

create table fdb_plblwarnings (
	languageid SMALLINT not null,
	lwid VARCHAR(4) not null,
	sequencenumber SMALLINT not null,
	warningtext VARCHAR(100),
	gendercode VARCHAR(1),
	agecode VARCHAR(1),
	pregnancyind SMALLINT,
	informationalcode VARCHAR(1), 
	constraint pkfdbplblwarnings primary key (languageid, lwid, sequencenumber)
);

create table fdb_poem (
	gcnseqno INTEGER not null,
	agelowindays INTEGER not null,
	agehighindays INTEGER not null,
	fdbdx VARCHAR(9) not null,
	orderstringid INTEGER not null,
	dxid VARCHAR(8), 
	constraint pkfdbpoem primary key (gcnseqno, agelowindays, agehighindays, fdbdx, orderstringid)
);

create table fdb_poem_adminrates (
	orderstringid INTEGER not null,
	sequencenumber SMALLINT not null,
	adminrate NUMERIC(16, 6),
	adminrateunitsid SMALLINT, 
	constraint pkfdbpoemadminrates primary key (orderstringid, sequencenumber)
);

create table fdb_poem_default (
	gcnseqno INTEGER not null,
	orderstringid INTEGER, 
	constraint pkfdbpoemdefault primary key (gcnseqno)
);

create table fdb_poem_doseformunits (
	unitsid SMALLINT not null,
	descriptiona1 VARCHAR(20),
	descriptionb1 VARCHAR(20),
	descriptiona2 VARCHAR(60),
	descriptionb2 VARCHAR(60),
	descriptiona3 VARCHAR(60),
	descriptionb3 VARCHAR(60),
	descriptiona4 VARCHAR(60),
	descriptionb4 VARCHAR(60),
	descriptiona5 VARCHAR(60),
	descriptionb5 VARCHAR(60),
	descriptiona6 VARCHAR(60),
	descriptionb6 VARCHAR(60),
	perdayconversion NUMERIC(16, 6),
	dispquantityind SMALLINT,
	mlconversion SMALLINT, 
	constraint pkfdbpoemdoseformunits primary key (unitsid)
);

create table fdb_poem_orderstring (
	orderstringid INTEGER not null,
	doserouteid VARCHAR(3),
	dosetypeid VARCHAR(2),
	calculationind SMALLINT,
	routeinstruction1 VARCHAR(20),
	routeinstruction2 VARCHAR(60),
	routeinstruction3 VARCHAR(60),
	routeinstruction4 VARCHAR(60),
	routeinstruction5 VARCHAR(60),
	routeinstruction6 VARCHAR(60),
	doselow NUMERIC(16, 6),
	doselowunitsid SMALLINT,
	dosehigh NUMERIC(16, 6),
	dosehighunitsid SMALLINT,
	doseformlow NUMERIC(16, 6),
	doseformlowunitsid SMALLINT,
	doseformhigh NUMERIC(16, 6),
	doseformhighunitsid SMALLINT,
	frequencylow SMALLINT,
	intervallow SMALLINT,
	intervallowunitsid SMALLINT,
	frequencyhigh SMALLINT,
	intervalhigh SMALLINT,
	intervalhighunitsid SMALLINT,
	frequency2low SMALLINT,
	interval2low SMALLINT,
	interval2lowunitsid SMALLINT,
	frequency2high SMALLINT,
	interval2high SMALLINT,
	interval2highunitsid SMALLINT,
	durationlow SMALLINT,
	durationlowunitsid SMALLINT,
	durationhigh SMALLINT,
	durationhighunitsid SMALLINT,
	ordertextid NUMERIC(16, 6), 
	constraint pkfdbpoemorderstring primary key (orderstringid)
);

create table fdb_poem_orderstring_addltext (
	orderstringid INTEGER not null,
	textlocationcode VARCHAR(1) not null,
	poemtextid NUMERIC(16, 6), 
	constraint pkfdbpoemorderstringaddltext primary key (orderstringid, textlocationcode)
);

create table fdb_poem_units (
	unitsid SMALLINT not null,
	description1 VARCHAR(30),
	description2 VARCHAR(90),
	description3 VARCHAR(90),
	description4 VARCHAR(90),
	description5 VARCHAR(90),
	description6 VARCHAR(90),
	calculationtypecode VARCHAR(1), 
	constraint pkfdbpoemunits primary key (unitsid)
);

create table fdb_poemtext (
	languageid SMALLINT not null,
	poemtextid NUMERIC(16, 6) not null,
	linenumber SMALLINT not null,
	linetext VARCHAR(70), 
	constraint pkfdbpoemtext primary key (languageid, poemtextid, linenumber)
);

create table fdb_precgeri (
	gericode INTEGER not null,
	description1 VARCHAR(41),
	description2 VARCHAR(123),
	description3 VARCHAR(123),
	description4 VARCHAR(123),
	description5 VARCHAR(123),
	description6 VARCHAR(123),
	severitylevelcode VARCHAR(1),
	sysrenalind SMALLINT,
	syshepaticind SMALLINT,
	syscardioind SMALLINT,
	syspulmind SMALLINT,
	sysneurind SMALLINT,
	sysendoind SMALLINT,
	addcomment1 VARCHAR(68),
	addcomment2 VARCHAR(204),
	addcomment3 VARCHAR(204),
	addcomment4 VARCHAR(204),
	addcomment5 VARCHAR(204),
	addcomment6 VARCHAR(204), 
	constraint pkfdbprecgeri primary key (gericode)
);

create table fdb_precgeri_druglink (
	drugid VARCHAR(12) not null,
	gericode INTEGER not null,
	concepttype SMALLINT,
	conceptid INTEGER, 
	constraint pkfdbprecgeridruglink primary key (drugid, gericode)
);

create index ixfdbprecgeridruglink1 on fdb_precgeri_druglink (conceptid);

create table fdb_precgeri_gcnseqno (
	gcnseqno INTEGER not null,
	gericode INTEGER not null, 
	constraint pkfdbprecgerigcnseqno primary key (gcnseqno, gericode)
);

create table fdb_precgeri_rtdfgen (
	rtdfgenid INTEGER not null,
	gericode INTEGER not null, 
	constraint pkfdbprecgerirtdfgen primary key (rtdfgenid, gericode)
);

create table fdb_precgeri_rtgen (
	rtgenid INTEGER not null,
	gericode INTEGER not null, 
	constraint pkfdbprecgerirtgen primary key (rtgenid, gericode)
);

create table fdb_preclact (
	lactcode INTEGER not null,
	description1 VARCHAR(40),
	description2 VARCHAR(120),
	description3 VARCHAR(120),
	description4 VARCHAR(120),
	description5 VARCHAR(120),
	description6 VARCHAR(120),
	severitylevelcode VARCHAR(1),
	excreffectcode VARCHAR(1),
	lacteffectcode VARCHAR(1),
	addcomment1 VARCHAR(77),
	addcomment2 VARCHAR(231),
	addcomment3 VARCHAR(231),
	addcomment4 VARCHAR(231),
	addcomment5 VARCHAR(231),
	addcomment6 VARCHAR(231), 
	constraint pkfdbpreclact primary key (lactcode)
);

create table fdb_preclact_druglink (
	drugid VARCHAR(12) not null,
	lactcode INTEGER not null,
	concepttype SMALLINT,
	conceptid INTEGER, 
	constraint pkfdbpreclactdruglink primary key (drugid, lactcode)
);

create index ixfdbpreclactdruglink1 on fdb_preclact_druglink (conceptid);

create table fdb_preclact_gcnseqno (
	gcnseqno INTEGER not null,
	lactcode INTEGER not null, 
	constraint pkfdbpreclactgcnseqno primary key (gcnseqno, lactcode)
);

create table fdb_preclact_rtdfgen (
	rtdfgenid INTEGER not null,
	lactcode INTEGER not null, 
	constraint pkfdbpreclactrtdfgen primary key (rtdfgenid, lactcode)
);

create table fdb_preclact_rtgen (
	rtgenid INTEGER not null,
	lactcode INTEGER not null, 
	constraint pkfdbpreclactrtgen primary key (rtgenid, lactcode)
);

create table fdb_precpedi (
	pedicode INTEGER not null,
	description1 VARCHAR(34),
	description2 VARCHAR(102),
	description3 VARCHAR(102),
	description4 VARCHAR(102),
	description5 VARCHAR(102),
	description6 VARCHAR(102),
	severitylevelcode VARCHAR(1),
	ageindayslow SMALLINT,
	ageindayshigh SMALLINT,
	addcomment1 VARCHAR(77),
	addcomment2 VARCHAR(231),
	addcomment3 VARCHAR(231),
	addcomment4 VARCHAR(231),
	addcomment5 VARCHAR(231),
	addcomment6 VARCHAR(231), 
	constraint pkfdbprecpedi primary key (pedicode)
);

create table fdb_precpedi_druglink (
	drugid VARCHAR(12) not null,
	pedicode INTEGER not null,
	concepttype SMALLINT,
	conceptid INTEGER, 
	constraint pkfdbprecpedidruglink primary key (drugid, pedicode)
);

create index ixfdbprecpedidruglink1 on fdb_precpedi_druglink (conceptid);

create table fdb_precpedi_gcnseqno (
	gcnseqno INTEGER not null,
	pedicode INTEGER not null, 
	constraint pkfdbprecpedigcnseqno primary key (gcnseqno, pedicode)
);

create table fdb_precpedi_rtdfgen (
	rtdfgenid INTEGER not null,
	pedicode INTEGER not null, 
	constraint pkfdbprecpedirtdfgen primary key (rtdfgenid, pedicode)
);

create table fdb_precpedi_rtgen (
	rtgenid INTEGER not null,
	pedicode INTEGER not null, 
	constraint pkfdbprecpedirtgen primary key (rtgenid, pedicode)
);

create table fdb_precpreg (
	pregcode INTEGER not null,
	description1 VARCHAR(41),
	description2 VARCHAR(123),
	description3 VARCHAR(123),
	description4 VARCHAR(123),
	description5 VARCHAR(123),
	description6 VARCHAR(123),
	significancelevelcode VARCHAR(1),
	addcomment1 VARCHAR(80),
	addcomment2 VARCHAR(240),
	addcomment3 VARCHAR(240),
	addcomment4 VARCHAR(240),
	addcomment5 VARCHAR(240),
	addcomment6 VARCHAR(240), 
	constraint pkfdbprecpreg primary key (pregcode)
);

create table fdb_precpreg_druglink (
	drugid VARCHAR(12) not null,
	pregcode INTEGER not null,
	concepttype SMALLINT,
	conceptid INTEGER, 
	constraint pkfdbprecpregdruglink primary key (drugid, pregcode)
);

create index ixfdbprecpregdruglink1 on fdb_precpreg_druglink (conceptid);

create table fdb_precpreg_gcnseqno (
	gcnseqno INTEGER not null,
	pregcode INTEGER not null, 
	constraint pkfdbprecpreggcnseqno primary key (gcnseqno, pregcode)
);

create table fdb_precpreg_rtdfgen (
	rtdfgenid INTEGER not null,
	pregcode INTEGER not null, 
	constraint pkfdbprecpregrtdfgen primary key (rtdfgenid, pregcode)
);

create table fdb_precpreg_rtgen (
	rtgenid INTEGER not null,
	pregcode INTEGER not null, 
	constraint pkfdbprecpregrtgen primary key (rtgenid, pregcode)
);

create table fdb_rdfmid_rtdfgenid (
	rdfmid INTEGER not null,
	rtdfgenid INTEGER not null, 
	constraint pkfdbrdfmidrtdfgenid primary key (rdfmid, rtdfgenid)
);

create index ixfdbrdfmidrtdfgenid1 on fdb_rdfmid_rtdfgenid (rtdfgenid);

-- Table fdb_refitem will contain customer content.
create table fdb_refitem (
	refitemid INTEGER not null,
	description1 VARCHAR(100),
	description2 VARCHAR(255),
	description3 VARCHAR(255),
	description4 VARCHAR(255),
	description5 VARCHAR(255),
	description6 VARCHAR(255),
	refitemcomment1 VARCHAR(255),
	refitemcomment2 VARCHAR(255),
	refitemcomment3 VARCHAR(255),
	refitemcomment4 VARCHAR(255),
	refitemcomment5 VARCHAR(255),
	refitemcomment6 VARCHAR(255), 
	constraint pkfdbrefitem primary key (refitemid)
);

-- Table fdb_refitem_attributenames will contain customer content.
create table fdb_refitem_attributenames (
	attributeid VARCHAR(5) not null,
	description VARCHAR(50), 
	constraint pkfdbrefitemattributenames primary key (attributeid)
);

-- Table fdb_refitem_attributevalues will contain customer content.
create table fdb_refitem_attributevalues (
	refitemid INTEGER not null,
	attributeid VARCHAR(5) not null,
	attributevalue VARCHAR(50), 
	constraint pkfdbrefitemattributevalues primary key (refitemid, attributeid)
);

-- Table fdb_refitem_msg_catdef will contain customer content.
create table fdb_refitem_msg_catdef (
	categoryid VARCHAR(10) not null,
	description VARCHAR(50), 
	constraint pkfdbrefitemmsgcatdef primary key (categoryid)
);

-- Table fdb_refitem_msg_def will contain customer content.
create table fdb_refitem_msg_def (
	messageid INTEGER not null,
	categoryid VARCHAR(10), 
	constraint pkfdbrefitemmsgdef primary key (messageid)
);

-- Table fdb_refitem_msg_link will contain customer content.
create table fdb_refitem_msg_link (
	refitemid INTEGER not null,
	messageid INTEGER not null, 
	constraint pkfdbrefitemmsglink primary key (refitemid, messageid)
);

-- Table fdb_refitem_msg_text will contain customer content.
create table fdb_refitem_msg_text (
	languageid SMALLINT not null,
	messageid INTEGER not null,
	sequencenumber SMALLINT not null,
	linetext VARCHAR(255),
	beginparagraphind SMALLINT, 
	constraint pkfdbrefitemmsgtext primary key (languageid, messageid, sequencenumber)
);

-- Table fdb_refitem_search will contain customer content.
create table fdb_refitem_search (
	languageid SMALLINT not null,
	refitemid INTEGER not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbrefitemsearch primary key (languageid, refitemid)
);

create index ixfdbrefitemsearch1 on fdb_refitem_search (descsearch);

create index ixfdbrefitemsearch2 on fdb_refitem_search (descaltsearch);

create table fdb_regpackaged (
	ipdid VARCHAR(11) not null,
	description1 VARCHAR(255),
	description2 VARCHAR(90),
	description3 VARCHAR(90),
	description4 VARCHAR(90),
	description5 VARCHAR(90),
	description6 VARCHAR(90),
	addldescriptor1 VARCHAR(20),
	addldescriptor2 VARCHAR(60),
	addldescriptor3 VARCHAR(60),
	addldescriptor4 VARCHAR(60),
	addldescriptor5 VARCHAR(60),
	addldescriptor6 VARCHAR(60),
	labelerid VARCHAR(6),
	strength1 VARCHAR(10),
	strength2 VARCHAR(30),
	strength3 VARCHAR(30),
	strength4 VARCHAR(30),
	strength5 VARCHAR(30),
	strength6 VARCHAR(30),
	imdid VARCHAR(11),
	gcnseqno INTEGER,
	rtid INTEGER,
	gendfid INTEGER,
	rtdfgenid INTEGER,
	rtgenid INTEGER,
	hicl INTEGER,
	medicaldeviceind SMALLINT,
	singledoserouteind SMALLINT,
	singleingredientind SMALLINT,
	obsoletedate VARCHAR(8),
	packagedescription VARCHAR(50),
	packagesize NUMERIC(16, 6),
	packagesizeunitscode VARCHAR(1),
	currentimagefilename VARCHAR(8),
	adddate VARCHAR(8),
	previousipdid VARCHAR(11),
	hospitalind SMALLINT,
	statuscode VARCHAR(1),
	numstrength NUMERIC(16, 6),
	numstrengthuom VARCHAR(10),
	numvolume NUMERIC(16, 6),
	numvolumeuom VARCHAR(5),
	needlegauge NUMERIC(16, 6),
	needlelength NUMERIC(16, 6),
	patientpkginsertind SMALLINT,
	privlabeledprodind SMALLINT,
	repackagerind SMALLINT,
	syringecapacity NUMERIC(16, 6),
	updatedate VARCHAR(8),
	medid INTEGER,
	drcsingledoserouteind SMALLINT,
	neosingledoserouteind SMALLINT,
	inactiveingredientreviewind SMALLINT,
	safestrength1 VARCHAR(60),
	safestrength2 VARCHAR(180),
	safestrength3 VARCHAR(180),
	safestrength4 VARCHAR(180),
	safestrength5 VARCHAR(180),
	safestrength6 VARCHAR(180),
	safenumstrengthuom VARCHAR(50),
	safenumvolumeuom VARCHAR(50), 
	constraint pkfdbregpackaged primary key (ipdid)
);

create index ixfdbregpackaged1 on fdb_regpackaged (medid);

create table fdb_regpackagedextid (
	extidtype SMALLINT not null,
	hexid VARCHAR(40) not null,
	extid VARCHAR(20),
	ipdid VARCHAR(11), 
	constraint pkfdbregpackagedextid primary key (extidtype, hexid)
);

create table fdb_regpackagedpropname (
	propertyname VARCHAR(32) not null,
	description VARCHAR(50),
	regionaldescription VARCHAR(150), 
	constraint pkfdbregpackagedpropname primary key (propertyname)
);

create table fdb_regpackagedpropvalue (
	ipdid VARCHAR(11) not null,
	propertyname VARCHAR(32) not null,
	sequencenumber SMALLINT not null,
	propertyvalue VARCHAR(255), 
	constraint pkfdbregpackagedpropvalue primary key (ipdid, propertyname, sequencenumber)
);

create table fdb_regpackagedsearch (
	languageid SMALLINT not null,
	ipdid VARCHAR(11) not null,
	nametypecode VARCHAR(1) not null,
	sequencenumber SMALLINT not null,
	descsearch VARCHAR(255),
	descaltsearch VARCHAR(255),
	descdisplay VARCHAR(255),
	baselangid SMALLINT, 
	constraint pkfdbregpackagedsearch primary key (languageid, ipdid, nametypecode, sequencenumber)
);

create index ixfdbregpackagedsearch1 on fdb_regpackagedsearch (descsearch);

create index ixfdbregpackagedsearch2 on fdb_regpackagedsearch (descaltsearch);

create table fdb_replacementallergengroups (
	replacedid INTEGER not null,
	replacementid INTEGER not null, 
	constraint pkfdbreplacementallergengroups primary key (replacedid, replacementid)
);

create table fdb_replacementdrugs (
	concepttype SMALLINT not null,
	replacedid INTEGER not null,
	replacementid INTEGER not null, 
	constraint pkfdbreplacementdrugs primary key (concepttype, replacedid, replacementid)
);

create index ixfdbreplacementdrugs1 on fdb_replacementdrugs (replacedid);

create index ixfdbreplacementdrugs2 on fdb_replacementdrugs (replacementid);

create table fdb_replacementdxids (
	replaceddxid VARCHAR(8) not null,
	replacementdxid VARCHAR(8) not null, 
	constraint pkfdbreplacementdxids primary key (replaceddxid, replacementdxid)
);

create table fdb_replacementingredients (
	replacedid INTEGER not null,
	replacementid INTEGER not null, 
	constraint pkfdbreplacementingredients primary key (replacedid, replacementid)
);

create table fdb_rmid_rtgenid (
	rmid INTEGER not null,
	rtgenid INTEGER not null, 
	constraint pkfdbrmidrtgenid primary key (rmid, rtgenid)
);

create index ixfdbrmidrtgenid1 on fdb_rmid_rtgenid (rtgenid);

create table fdb_route (
	rtid INTEGER not null,
	description1 VARCHAR(30),
	description2 VARCHAR(90),
	description3 VARCHAR(90),
	description4 VARCHAR(90),
	description5 VARCHAR(90),
	description6 VARCHAR(90),
	abbrev VARCHAR(4), 
	constraint pkfdbroute primary key (rtid)
);

create table fdb_routeddfdrug (
	rdfmid INTEGER not null,
	descdisplay VARCHAR(95),
	descsearch VARCHAR(95),
	descaltsearch VARCHAR(95),
	mnid INTEGER,
	rtid INTEGER,
	dfid INTEGER,
	rmid INTEGER,
	medid_unique INTEGER,
	nametypecode VARCHAR(1),
	reffederallegendcode VARCHAR(1),
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	obsoletedate VARCHAR(8),
	replacedindicator SMALLINT,
	clinicallinkind SMALLINT,
	haspackageddrugsind SMALLINT,
	hasequivpackageddrugsind SMALLINT,
	genericmnid INTEGER,
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	statuscode VARCHAR(1),
	statusdrc SMALLINT,
	singledoserouteind SMALLINT,
	canbedosedind SMALLINT,
	tm_descdisplay VARCHAR(95),
	tm_confusiongroupid INTEGER,
	tm_groupdesc VARCHAR(250),
	tm_sourcecode VARCHAR(1),
	neocanbedosedind SMALLINT,
	neosingledoserouteind SMALLINT,
	hicl_unique INTEGER, 
	constraint pkfdbrouteddfdrug primary key (rdfmid)
);

create index ixfdbrouteddfdrug1 on fdb_routeddfdrug (mnid);

create index ixfdbrouteddfdrug2 on fdb_routeddfdrug (descaltsearch);

create index ixfdbrouteddfdrug3 on fdb_routeddfdrug (descsearch);

create table fdb_routeddrug (
	rmid INTEGER not null,
	descdisplay VARCHAR(65),
	descsearch VARCHAR(65),
	descaltsearch VARCHAR(65),
	mnid INTEGER,
	rtid INTEGER,
	rtgenid_unique INTEGER,
	rdfmid_unique INTEGER,
	medid_unique INTEGER,
	nametypecode VARCHAR(1),
	reffederallegendcode VARCHAR(1),
	singleingredientind SMALLINT,
	medicaldeviceind SMALLINT,
	obsoletedate VARCHAR(8),
	replacedindicator SMALLINT,
	clinicallinkind SMALLINT,
	haspackageddrugsind SMALLINT,
	hasequivpackageddrugsind SMALLINT,
	genericmnid INTEGER,
	statusddcm SMALLINT,
	statusddim SMALLINT,
	statusdfim SMALLINT,
	statusdt SMALLINT,
	statusprecgeri SMALLINT,
	statuspreclact SMALLINT,
	statusprecpedi SMALLINT,
	statusprecpreg SMALLINT,
	statusside SMALLINT,
	statuscode VARCHAR(1),
	statusdrc SMALLINT,
	singledoserouteind SMALLINT,
	canbedosedind SMALLINT,
	tm_descdisplay VARCHAR(70),
	tm_confusiongroupid INTEGER,
	tm_groupdesc VARCHAR(250),
	tm_sourcecode VARCHAR(1),
	neocanbedosedind SMALLINT,
	neosingledoserouteind SMALLINT,
	hicl_unique INTEGER, 
	constraint pkfdbrouteddrug primary key (rmid)
);

create index ixfdbrouteddrug1 on fdb_routeddrug (mnid);

create index ixfdbrouteddrug2 on fdb_routeddrug (descaltsearch);

create index ixfdbrouteddrug3 on fdb_routeddrug (descsearch);

create table fdb_rtgenid_genericrmid (
	rtgenid INTEGER not null,
	rmid INTEGER not null, 
	constraint pkfdbrtgenidgenericrmid primary key (rtgenid, rmid)
);

create index ixfdbtgenidgenericrmid on fdb_rtgenid_genericrmid (rmid);

create table fdb_settings (
	settingname VARCHAR(32) not null,
	settingvalue VARCHAR(255), 
	constraint pkfdbsettings primary key (settingname)
);

create table fdb_side (
	sideeffid INTEGER not null,
	sequencenumber SMALLINT not null,
	descdrug1 VARCHAR(100),
	descdrug2 VARCHAR(255),
	descdrug3 VARCHAR(255),
	descdrug4 VARCHAR(255),
	descdrug5 VARCHAR(255),
	descdrug6 VARCHAR(255),
	desceffect1 VARCHAR(100),
	desceffect2 VARCHAR(255),
	desceffect3 VARCHAR(255),
	desceffect4 VARCHAR(255),
	desceffect5 VARCHAR(255),
	desceffect6 VARCHAR(255),
	fdbdx VARCHAR(9),
	freqcode VARCHAR(1),
	sevcode VARCHAR(1),
	viscode VARCHAR(1),
	labtestcode VARCHAR(1),
	physcode VARCHAR(1),
	hypercode VARCHAR(1),
	dxid VARCHAR(8), 
	constraint pkfdbside primary key (sideeffid, sequencenumber)
);

create table fdb_sidedruglink (
	drugid VARCHAR(12) not null,
	sideeffid INTEGER not null,
	gcnseqno INTEGER, 
	constraint pkfdbsidedruglink primary key (drugid, sideeffid)
);

-- Table fdb_subset_drugvalues will contain customer content.
create table fdb_subset_drugvalues (
	siteid VARCHAR(10) not null,
	subsetid VARCHAR(10) not null,
	concepttype SMALLINT not null,
	conceptidstring VARCHAR(20) not null,
	sequencenumber SMALLINT not null,
	conceptidnumeric INTEGER,
	customvalue1 VARCHAR(50),
	customvalue2 VARCHAR(50),
	customvalue3 VARCHAR(50),
	customvalue4 VARCHAR(50), 
	constraint pkfdbsubsetdrugvalues primary key (siteid, subsetid, conceptidstring, concepttype, sequencenumber)
);

-- Table fdb_subset_set will contain customer content.
create table fdb_subset_set (
	siteid VARCHAR(10) not null,
	subsetid VARCHAR(10) not null,
	description VARCHAR(50),
	setcomment VARCHAR(255),
	owner VARCHAR(10), 
	constraint pkfdbsubsetset primary key (siteid, subsetid)
);

-- Table fdb_subset_site will contain customer content.
create table fdb_subset_site (
	siteid VARCHAR(10) not null,
	descdisplay VARCHAR(50),
	descsearch VARCHAR(50),
	descaltsearch VARCHAR(50),
	sitecomment VARCHAR(255), 
	constraint pkfdbsubsetsite primary key (siteid)
);

create index ixfdbsubsetsite1 on fdb_subset_site (descsearch);

create index ixfdbsubsetsite2 on fdb_subset_site (descaltsearch);

-- Table fdb_user_ddim will contain customer content.
create table fdb_user_ddim (
	rtgenid1 INTEGER not null,
	rtgenid2 INTEGER not null,
	interactionid INTEGER not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbuserddim primary key (rtgenid1, rtgenid2, interactionid)
);

-- Table fdb_user_ddimdruglink_category will contain customer content.
create table fdb_user_ddimdruglink_category (
	rtgenid1 INTEGER not null,
	rtgenid2 INTEGER not null,
	interactionid INTEGER not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbuserddimdruglinkcategory primary key (rtgenid1, rtgenid2, interactionid)
);

-- Table fdb_user_ddiminteraction will contain customer content.
create table fdb_user_ddiminteraction (
	interactionid INTEGER not null,
	description1 VARCHAR(60),
	description2 VARCHAR(180),
	description3 VARCHAR(180),
	description4 VARCHAR(180),
	description5 VARCHAR(180),
	description6 VARCHAR(180),
	severitylevelcode VARCHAR(1),
	monographid INTEGER,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150),
	clinicaleffectcode1 VARCHAR(3),
	clinicaleffectcode2 VARCHAR(3), 
	constraint pkfdbuserddiminteraction primary key (interactionid)
);

-- Table fdb_user_interaction_category will contain customer content.
create table fdb_user_interaction_category (
	interactiontypecode VARCHAR(1) not null,
	interactionid INTEGER not null,
	uicategory1 VARCHAR(50),
	uicategory2 VARCHAR(150),
	uicategory3 VARCHAR(150),
	uicategory4 VARCHAR(150),
	uicategory5 VARCHAR(150),
	uicategory6 VARCHAR(150), 
	constraint pkfdbuserinteractioncategory primary key (interactiontypecode, interactionid)
);

create table fdb_version (
	versionkey SMALLINT not null,
	dbversion VARCHAR(5),
	buildversion VARCHAR(5),
	frequency VARCHAR(1),
	issuedate VARCHAR(8),
	versioncomment VARCHAR(80),
	dbtype VARCHAR(10), 
	constraint pkfdbversion primary key (versionkey)
);
