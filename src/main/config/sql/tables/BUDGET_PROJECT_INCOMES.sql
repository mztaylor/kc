CREATE TABLE BUDGET_PROJECT_INCOMES (
 	"PROPOSAL_NUMBER" VARCHAR2(12) constraint BUDGET_PROJECT_INCOME_PN NOT NULL ENABLE, 
	"BUDGET_VERSION_NUMBER" NUMBER(3,0) constraint BUDGET_PROJECT_INCOME_BV NOT NULL ENABLE,
	 
	"BUDGET_PERIOD_NUMBER" NUMBER(3,0) constraint BUDGET_PROJECT_INCOME_BP NOT NULL ENABLE, 
 	"PROJECT_INCOME"  NUMBER(19,2) constraint BUDGET_PROJECT_INCOME_PI NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(200) constraint BUDGET_PROJECT_INCOME_DE NOT NULL ENABLE, 
	
	"UPDATE_TIMESTAMP" DATE constraint BUDGET_PROJECT_INCOME_UT NOT NULL ENABLE, 
	"UPDATE_USER" VARCHAR2(8) constraint BUDGET_PROJECT_INCOME_UU NOT NULL ENABLE, 
	"VER_NBR" NUMBER(8,0) DEFAULT 1  constraint  BUDGET_PROJECT_INCOME_VN  NOT NULL ENABLE,
	"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID()  constraint  BUDGET_PROJECT_INCOME_OI  NOT NULL ENABLE,
	
	CONSTRAINT "PK_BUDGET_PROJECT_INCOME_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "BUDGET_VERSION_NUMBER", "BUDGET_PERIOD_NUMBER", "PROJECT_INCOME", "DESCRIPTION") ENABLE
);

ALTER TABLE BUDGET_PROJECT_INCOMES ADD CONSTRAINT "FK_BUDGET_PROJ_INC_BUDGET_KRA" FOREIGN KEY ("PROPOSAL_NUMBER", "BUDGET_VERSION_NUMBER")
	  REFERENCES "BUDGET" ("PROPOSAL_NUMBER", "VERSION_NUMBER");

ALTER TABLE BUDGET_PROJECT_INCOMES ADD CONSTRAINT "FK_BUDGET_PROJ_INC_BP_KRA" FOREIGN KEY ("PROPOSAL_NUMBER", "BUDGET_VERSION_NUMBER", "BUDGET_PERIOD_NUMBER")
	  REFERENCES "BUDGET_PERIODS" ("PROPOSAL_NUMBER", "VERSION_NUMBER", "BUDGET_PERIOD");