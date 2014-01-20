DELIMITER /
CREATE TABLE PERSON_MASS_CHANGE (
  PERSON_MASS_CHANGE_ID DECIMAL(12,0) NOT NULL,
  DOCUMENT_NUMBER       VARCHAR(40) NOT NULL,
  REPLACEE_PERSON_ID    VARCHAR(40),
  REPLACEE_ROLODEX_ID   DECIMAL(12,0),
  REPLACER_PERSON_ID    VARCHAR(40),
  REPLACER_ROLODEX_ID   DECIMAL(12,0),
  CHANGE_ALL_SEQUENCES  CHAR(1) DEFAULT 'N' NOT NULL,
  UPDATE_USER           VARCHAR(60) NOT NULL, 
  UPDATE_TIMESTAMP      DATE NOT NULL, 
  OBJ_ID                VARCHAR(36) NOT NULL,
  VER_NBR               DECIMAL(8,0) DEFAULT 1 NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE PERSON_MASS_CHANGE ADD CONSTRAINT PK_PMC
  PRIMARY KEY (PERSON_MASS_CHANGE_ID)
/
DELIMITER ;
