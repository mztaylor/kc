DELETE FROM PROTOCOL_CORRESPONDENCE;

DELETE FROM PROTOCOL_ACTIONS;

DELETE FROM PROTOCOL_ATTACHMENT_PERSONNEL;

DELETE FROM PROTOCOL_ATTACHMENT_PROTOCOL;

DELETE FROM PROTOCOL_PERSONS;

--DELETE FROM PROTOCOL_PERSON_ROLE_MAPPING;
--DELETE FROM PROTOCOL_PERSON_ROLES;
DELETE FROM PROTOCOL_VULNERABLE_SUB;

DELETE FROM PROTOCOL_LOCATION;

DELETE FROM PROTOCOL_NOTEPAD;

DELETE FROM PROTOCOL_EXEMPT_CHKLST;

DELETE FROM PROTOCOL_EXEMPT_NUMBER;

DELETE FROM PROTOCOL_EXPIDITED_CHKLST;

DELETE FROM PROTOCOL_FUNDING_SOURCE;

DELETE FROM PROTOCOL_SPECIAL_REVIEW;

DELETE FROM PROTOCOL_REFERENCES;

DELETE FROM PROTOCOL_RESEARCH_AREAS;

DELETE FROM PROTOCOL_REVIEWERS;

DELETE FROM PROTOCOL_RISK_LEVELS;

DELETE FROM PROTOCOL_SUBMISSION_DOC;

DELETE FROM PROTOCOL_SUBMISSION;

DELETE FROM PROTOCOL_VOTE_ABSTAINEES;

DELETE FROM PROTOCOL;

DELETE FROM PROTOCOL_STATUS;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(100, 'Pending/In Progress', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','42770C33F7724A50A052860D2D9FA4BB') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(101, 'Submitted to IRB', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','2427C65D566A4BD9BCCAFABD1ADA4916') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(102, 'Specific Minor Revisions Required', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','AFE0F24F2A58464EA78E5E31D93F2ABC') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(103, 'Deferred', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','5CC52D4F35B34ABDB920360766FD7A9F') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(104, 'Substantive Revisions Required', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','EBE3017CDB6D457D8A8607A510D5D0FA') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(105, 'Amendment in Progress', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','261230CC03D6411289C0830C867B8256') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(106, 'Renewal in Progress', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','8A7C0A22169843E982220EFD4B84BB9A') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(200, 'Active - Open to Enrollment', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','35637A0B11B94A02BBD3D86263510182') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(201, 'Active - Closed to Enrollment', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','162E713F6B1340A4BCCDEB27497237C6') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(202, 'Active - Data Analysis Only', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','DBBFAA4123484B5DBC14356C1CFD3B3D') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(203, 'Exempt', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','56FD757AF81841AB84611E92CE541188') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(300, 'Closed Administratively for lack of response', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','661C6274D3BF4D8FBF3E1838EE73CDAA') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(301, 'Closed by Investigator', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','6B3B9E9DA9FA45CE913599999B623D5F') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(302, 'Suspended by Investigator', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','A29DD248A04A4A40AA4C0EAA06A70E9D') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(303, 'Do Not Use - delete later - Terminated by Investigator', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','F48F3335D76D44F1BA17D8A2D3AB06BB') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(304, 'Withdrawn', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','4B3A16BDB62C426FA706A9E8BE9C7EC9') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(305, 'Expired', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','E971A9BBD4334A598EA75590BB96C4B9') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(306, 'Disapproved', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','C36CFDF2829743DB85562D67A7C6BA8F') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(307, 'Terminated by IRB', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','84652879D2DD4411B2AD07346778F7B1') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(308, 'Suspended by IRB', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','7B7646575BB046B091BD0694ACABC34A') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(309, 'Not Human Subjects Research', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','E93A194152AF459EAE855780093109B1') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(310, 'IRB review not required', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','FE30BD4ECD954B9E921464FA40CEAD68') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(311, 'Suspended by DSMB', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','33CCA8C759D04D0EB4B166E223894F07') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(400, 'Amendment Incorportated into Protocol', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','D6F4F1E36AEF4D93BE51597018C7561A') ;

INSERT INTO PROTOCOL_STATUS (PROTOCOL_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
VALUES(401, 'Renewal Incorporated into Protocol', TO_DATE ('2007-12-20 13:25:59', 'YYYY-MM-DD HH24:MI:SS') , 'COEUS','F7EFCE7029B742C586BB06804C9960B6') ;


