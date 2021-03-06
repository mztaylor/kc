DELIMITER /
CREATE TABLE IACUC_PROTO_ATTACHMENT_STATUS ( 
    STATUS_CD VARCHAR(3) NOT NULL, 
    DESCRIPTION VARCHAR(300) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR(60) NOT NULL, 
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR(36) NOT NULL) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE IACUC_PROTO_ATTACHMENT_STATUS 
ADD CONSTRAINT PK_IACUC_PROTO_ATTACH_STATUS 
PRIMARY KEY (STATUS_CD)
/
DELIMITER ;
