DELIMITER /
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Award'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Institute Proposal'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Proposal'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'IRB Protocol'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'IACUC Protocol'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Update'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Annual'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'New'
/
UPDATE COI_DISCLOSURE_EVENT_TYPE SET SYSTEM_EVENT = 'Y' WHERE DESCRIPTION = 'Other'
/
DELIMITER ;
