DELIMITER /
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
VALUES('KC', 'KC-GEN', 'All', 'ACTION_LIST_DEFAULT_FROM_USER', UUID() , 1, 'CONFG', 'admin', 'Default user used to send action list notifications', 'A')
/
DELIMITER ;
