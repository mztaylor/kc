TRUNCATE TABLE AWARD_BUDGET_STATUS DROP STORAGE
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','In Progress','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Submitted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Rejected','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Posted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','To Be Posted','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Error in Posting','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Do Not Post','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Disapproved','admin',SYSDATE,SYS_GUID(),1)
/
INSERT INTO AWARD_BUDGET_STATUS (AWARD_BUDGET_STATUS_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Cancelled','admin',SYSDATE,SYS_GUID(),1)
/
