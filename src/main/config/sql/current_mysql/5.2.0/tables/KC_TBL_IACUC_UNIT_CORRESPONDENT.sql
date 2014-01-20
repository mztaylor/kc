DELIMITER /

CREATE TABLE IACUC_UNIT_CORRESPONDENT ( 
    CORRESPONDENT_ID DECIMAL(12,0) NOT NULL, 
    UNIT_NUMBER VARCHAR(8) NOT NULL, 
    CORRESPONDENT_TYPE_CODE DECIMAL(3,0) NOT NULL, 
    PERSON_ID VARCHAR(40), 
    NON_EMPLOYEE_FLAG VARCHAR(1) NOT NULL,
    DESCRIPTION VARCHAR(200), 
    UPDATE_TIMESTAMP DATETIME NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL)
/


ALTER TABLE IACUC_UNIT_CORRESPONDENT 
ADD CONSTRAINT IACUC_UNIT_CORRESPONDENT_PK 
PRIMARY KEY (CORRESPONDENT_ID)
/

DELIMITER ;