DELIMITER /
INSERT INTO KRIM_ROLE_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
VALUES ('Y','COI Reporter',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'),NOW(),'KC-COIDISCLOSURE',UUID(),(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_BS_S),'COI Reporter',1)
/
INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND
NM = 'Edit Document Section'),'KC-COIDISCLOSURE','Report Coi Disclosure','Report Coi Disclosure','Y',UUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S),(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_BS_S),(SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S),'Y',UUID(),1)
/
DELIMITER ;
