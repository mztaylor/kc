DELIMITER /
INSERT INTO IACUC_PROTOCOL_REFERENCE_TYPE ( PROTOCOL_REFERENCE_TYPE_CODE, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 1, 'Sponsor number', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROTOCOL_REFERENCE_TYPE ( PROTOCOL_REFERENCE_TYPE_CODE, DESCRIPTION, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 2, 'NCI ', 'Y', NOW(), 'admin', UUID(), 1 ) 
/



INSERT INTO IACUC_PROTOCOL_PROJECT_TYPE ( PROJECT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( '1', 'Agricultural', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROTOCOL_PROJECT_TYPE ( PROJECT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( '2', 'Behavioral', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROTOCOL_PROJECT_TYPE ( PROJECT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( '3', 'Biomedical', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROTOCOL_PROJECT_TYPE ( PROJECT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( '4', 'Field Studies', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROTOCOL_PROJECT_TYPE ( PROJECT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( '5', 'Other', NOW(), 'admin', UUID(), 1 ) 
/

DELIMITER ;
