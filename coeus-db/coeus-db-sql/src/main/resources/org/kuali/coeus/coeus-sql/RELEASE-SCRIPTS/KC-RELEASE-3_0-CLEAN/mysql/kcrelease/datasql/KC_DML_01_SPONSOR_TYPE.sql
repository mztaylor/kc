delimiter /
TRUNCATE TABLE SPONSOR_TYPE
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('0','Federal','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','State','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Local Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Private Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Private Non-Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Foundation','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Institution of Higher Education','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Foreign Federal Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Foreign State Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Foreign Local Government','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Foreign Private Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Foreign Private Non-Profit','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('15','Foreign Foundation','admin',NOW(),UUID(),1)
/
INSERT INTO SPONSOR_TYPE (SPONSOR_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('16','Foreign Institution of Higher Education','admin',NOW(),UUID(),1)
/
delimiter ;
