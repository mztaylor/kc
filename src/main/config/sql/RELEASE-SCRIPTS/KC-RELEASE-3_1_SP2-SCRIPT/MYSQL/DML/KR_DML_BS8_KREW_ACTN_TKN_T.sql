INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions'),'10000000001','S',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions'),'10000000001','C',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL); 
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1'),'10000000001','S',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1'),'10000000001','C',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0'),'10000000001','S',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0'),'10000000001','C',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2'),'10000000001','S',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2'),'10000000001','C',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);

COMMIT;