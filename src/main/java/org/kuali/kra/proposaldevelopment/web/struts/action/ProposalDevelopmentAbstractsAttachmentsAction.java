/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.INSTITUTIONAL_ATTACHMENT_TYPE_NAME;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.MAPPING_CLOSE_PAGE;
import static org.kuali.kra.infrastructure.Constants.MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE;
import static org.kuali.kra.infrastructure.Constants.MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE;
import static org.kuali.kra.infrastructure.Constants.PERSONNEL_ATTACHMENT_TYPE_NAME;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_ATTACHMENT_TYPE_NAME;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_DELETE_ABSTRACT_CONFIRMATION;
import static org.kuali.kra.infrastructure.KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.kns.util.KNSConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddAbstractEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddInstituteAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddPersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.rule.event.NewNarrativeUserRightsEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveInstituteAttachmentsEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SavePersonnelAttachmentEvent;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.bo.Note;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * <code>Struts Action</code> class process requests from Proposal Abstract Attachments page.
 * It handles Proposal attachments, Institutional attachments, Personnel attachments and Abstracts.
 * Attachment(Narrative) Module Maintenance, Narrative user Rights, Upload Attachment, View Attachment, 
 * Abstract Maintenance are the main features processed by this class 
 * 
 * @author KRADEV team
 * @version 1.0
 */
public class ProposalDevelopmentAbstractsAttachmentsAction extends ProposalDevelopmentAction {
    private static final String PROPOSAL_NARRATIVE_TYPE_GROUP2 = "proposalNarrativeTypeGroup";
    private static final String EMPTY_STRING = "";
    private static final String MODULE_NUMBER = "moduleNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final String PROPOSAL_PERSON_NUMBER = "proposalPersonNumber";
    private static final String BIOGRAPHY_NUMBER = "biographyNumber";
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAbstractsAttachmentsAction.class);
    private static final String LINE_NUMBER = "line";
    private static final String CONFIRM_DELETE_ABSTRACT_KEY = "confirmDeleteAbstract";
    private static final String CONFIRM_DELETE_INSTITUTIONAL_ATTACHMENT_KEY = "confirmDeleteInstitutionalAttachment";
    private static final String CONFIRM_DELETE_PERSONNEL_ATTACHMENT_KEY = "confirmDeletePersonnelAttachment";
    private static final String CONFIRM_DELETE_PROPOSAL_ATTACHMENT_KEY = "confirmDeleteProposalAttachment";
    
    /**
     * Overridden method from ProposalDevelopmentAction. It populates Narrative module user rights
     * before the save.
     * Proposal Attachments and Institutional Attachments are being saved into <i>NARRATIVE</i> table
     * 
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        Narrative newNarrative = proposalDevelopmentForm.getNewNarrative();
        
        boolean rulePassed = true;
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveNarrativesEvent(EMPTY_STRING,proposalDevelopmentDocument,newNarrative, proposalDevelopmentForm.getNarratives()));
        rulePassed &= getKualiRuleService().applyRules(new SavePersonnelAttachmentEvent(EMPTY_STRING, proposalDevelopmentDocument, proposalDevelopmentForm.getNewPropPersonBio()));
        rulePassed &= getKualiRuleService().applyRules(new SaveInstituteAttachmentsEvent(EMPTY_STRING,proposalDevelopmentDocument));

        if (!rulePassed){
            mapping.findForward(Constants.MAPPING_BASIC);
        }
        // refresh, so the status can be displayed properly on tab title
        List<Narrative> narativeListToBeSaved = proposalDevelopmentDocument.getDevelopmentProposal().getNarratives();
        for (Narrative narrativeToBeSaved : narativeListToBeSaved) {
            narrativeToBeSaved.refreshNonUpdateableReferences();
        }
        
        return super.save(mapping, form, request, response);
    }
    /**
     * Populates module level rights for each narrative for logged in user.
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((ProposalDevelopmentForm)form).getProposalDevelopmentParameters().put(PROPOSAL_NARRATIVE_TYPE_GROUP2,getParameterService().retrieveParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, PROPOSAL_NARRATIVE_TYPE_GROUP2));
        ActionForward actionForward = super.execute(mapping, form, request, response); 
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument)((ProposalDevelopmentForm)form).getDocument();
        KraServiceLocator.getService(ProposalPersonBiographyService.class).setPersonnelBioTimeStampUser(doc.getDevelopmentProposal().getPropPersonBios()); 
        List<Narrative> narratives = new ArrayList<Narrative> ();
        narratives.addAll(doc.getDevelopmentProposal().getNarratives());
        narratives.addAll(doc.getDevelopmentProposal().getInstituteAttachments());
        KraServiceLocator.getService(NarrativeService.class).setNarrativeTimeStampUser(narratives);
        return actionForward;
    }    

    /**
     * 
     * This method adds new proposal attachment(narrative) to the narrative list.
     * User can not add more than one narrative with the same narrative type which 
     * has allowMultipleFlag as false. This rule is being validated by using AddNarrativeRule
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward of ProposalAbstractsAttachments page
     * @throws Exception
     */
    public ActionForward addProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        Narrative narrative = proposalDevelopmentForm.getNewNarrative();
        if(getKualiRuleService().applyRules(new AddNarrativeEvent(EMPTY_STRING, proposalDevelopmentDocument, narrative))){
            proposalDevelopmentDocument.getDevelopmentProposal().addNarrative(narrative);
            proposalDevelopmentForm.setNewNarrative(new Narrative());
            populateTabState(proposalDevelopmentForm, "Proposal Attachments " + proposalDevelopmentDocument.getDevelopmentProposal().getNarratives().size());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method used to stream the attachment byte array onto the browser.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadInstituteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        Narrative narrative = pd.getDevelopmentProposal().getInstituteAttachments().get(lineNumber);
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrativeAttachment,response); 
        return null;
    }
    
    /**
     * 
     * This method used to stream the attachment byte array onto the browser.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        Narrative narrative = pd.getDevelopmentProposal().getNarratives().get(lineNumber);
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrativeAttachment,response);
        return null;
    }
    /**
     * 
     * This method used to find the narrative attachment for a narrative
     * @param narrative
     * @return NarrativeAttachment
     */
    private NarrativeAttachment findNarrativeAttachment(Narrative narrative){
        Map<String,String> narrativeAttachemntMap = new HashMap<String,String>();
        narrativeAttachemntMap.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        narrativeAttachemntMap.put(MODULE_NUMBER, narrative.getModuleNumber()+"");
        return (NarrativeAttachment)getBusinessObjectService().findByPrimaryKey(NarrativeAttachment.class, narrativeAttachemntMap);
    }
    /**
     * 
     * Handy method to stream the byte array to response object
     * @param attachmentDataSource
     * @param response
     * @throws Exception
     */
    private void streamToResponse(AttachmentDataSource attachmentDataSource,HttpServletResponse response) throws Exception{
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }

    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return confirm(buildDeleteAttachmentConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_PROPOSAL_ATTACHMENT_KEY), CONFIRM_DELETE_PROPOSAL_ATTACHMENT_KEY, EMPTY_STRING);
    }

    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward confirmDeleteProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        populateTabState((ProposalDevelopmentForm) form, "Proposal Attachments " + pdDoc.getDevelopmentProposal().getNarratives().size());
        return deleteAttachment(mapping, form, request, response, CONFIRM_DELETE_PROPOSAL_ATTACHMENT_KEY, "deleteProposalAttachment");
    }
    
    /**
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @param key Name of the key for this confirmation
     * @param deleteMethodName String name of the delete method to use.
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String key, String deleteMethodName) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        
        if (key.equals(question)) {
            ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) form).getDocument();
            
            LOG.info("Running delete '" + deleteMethodName + "' on " + document + " for " + getLineToDelete(request));
            document.getDevelopmentProposal().getClass().getMethod(deleteMethodName, int.class).invoke(document.getDevelopmentProposal(), getLineToDelete(request));
        }
        
        return mapping.findForward(MAPPING_BASIC);
       
    }

    /**
     * 
     * This method used to get the proposal user rights
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
     public ActionForward getInstituteAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
             HttpServletResponse response) throws Exception {
         ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
         proposalDevelopmentForm.setShowMaintenanceLinks(false);
         String line = request.getParameter(LINE_NUMBER);
         int lineNumber = line == null ? getLineToDelete(request) : Integer.parseInt(line);
         ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
         pd.getDevelopmentProposal().populatePersonNameForInstituteAttachmentUserRights(lineNumber);
         
         Narrative narrative = pd.getDevelopmentProposal().getInstituteAttachment(lineNumber);
         List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
         List<NarrativeUserRights> editUserRights = (List<NarrativeUserRights>) ObjectUtils.deepCopy((Serializable) userRights);
         proposalDevelopmentForm.setNewNarrativeUserRights(editUserRights);
         
         request.setAttribute(LINE_NUMBER, ""+lineNumber);
         return mapping.findForward(MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE);
     }
     
   /**
    * 
    * This method used to get the proposal user rights
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return 
    * @throws Exception
    */
    public ActionForward getProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setShowMaintenanceLinks(false);
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? getLineToDelete(request) : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        pd.getDevelopmentProposal().populatePersonNameForNarrativeUserRights(lineNumber);
        
        Narrative narrative = pd.getDevelopmentProposal().getNarratives().get(lineNumber);
        List<NarrativeUserRights> userRights = narrative.getNarrativeUserRights();
        List<NarrativeUserRights> editUserRights = (List<NarrativeUserRights>) ObjectUtils.deepCopy((Serializable) userRights);
        proposalDevelopmentForm.setNewNarrativeUserRights(editUserRights);
        
        request.setAttribute(LINE_NUMBER, ""+lineNumber);
        return mapping.findForward(MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE);
    }

    /**
     * 
     * This method to send the request back to a page which closes by itself. Since Attachment right page 
     * is opened in a new window, after saving, it should close by itself.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward of close page
     * @throws Exception
     */
    public ActionForward addProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        List<NarrativeUserRights> newNarrativeUserRights = proposalDevelopmentForm.getNewNarrativeUserRights();
        int lineNumber = getLineNumber(request);
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new NewNarrativeUserRightsEvent(proposalDevelopmentDocument, newNarrativeUserRights, lineNumber));
        
        if (!rulePassed) {
            request.setAttribute(LINE_NUMBER, Integer.toString(lineNumber));
            forward = mapping.findForward(MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE);
        }
        else {
            proposalDevelopmentDocument.getDevelopmentProposal().getNarrative(lineNumber).setNarrativeUserRights(newNarrativeUserRights);
            forward = mapping.findForward(MAPPING_CLOSE_PAGE);
        }
        
        return forward;
    }

    /**
     * 
     * This method to send the request back to a page which closes by itself. Since Attachment right page 
     * is opened in a new window, after saving, it should close by itself.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward of close page
     * @throws Exception
     */
    public ActionForward addInstituteAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward;

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        List<NarrativeUserRights> newNarrativeUserRights = proposalDevelopmentForm.getNewNarrativeUserRights();
        int lineNumber = getLineNumber(request);
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new NewNarrativeUserRightsEvent(proposalDevelopmentDocument, newNarrativeUserRights, lineNumber));
        
        if (!rulePassed) {
            request.setAttribute(LINE_NUMBER, Integer.toString(lineNumber));
            forward = mapping.findForward(MAPPING_INSTITUTE_ATTACHMENT_RIGHTS_PAGE);
        }
        else {
            proposalDevelopmentDocument.getDevelopmentProposal().getInstituteAttachment(lineNumber).setNarrativeUserRights(newNarrativeUserRights);
            forward = mapping.findForward(MAPPING_CLOSE_PAGE);
        }
        
        return forward;
    }

    /**
     * 
     * This method used to replace the attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward replaceProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        pd.getDevelopmentProposal().replaceAttachment(getSelectedLine(request));
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * 
     * This method used to replace the attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward replaceInstituteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        pd.getDevelopmentProposal().replaceInstituteAttachment(getSelectedLine(request));
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Update the User and Timestamp for the business object.
     * @param doc the business object
     */
    private void updateUserTimestamp(KraPersistableBusinessObjectBase bo) {
        String updateUser = GlobalVariables.getUserSession().getPrincipalName();
    
        // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters
        if (updateUser.length() > 60) {
            updateUser = updateUser.substring(0, 60);
        }
        bo.setUpdateTimestamp((getService(DateTimeService.class)).getCurrentTimestamp());
        bo.setUpdateUser(updateUser);
        if (bo instanceof ProposalAbstract) {
            ProposalAbstract abstractBo = (ProposalAbstract)bo;
            abstractBo.setTimestampDisplay((getService(DateTimeService.class)).getCurrentTimestamp());
            abstractBo.setUserDisplay(updateUser);
        }
    }
    
    /**
     * Adds an Abstract to the Proposal Development Document.
     * 
     * Assuming we have a valid new abstract, it is taken from the form 
     * and moved into the document's list of abstracts.  The form's abstract
     * is then cleared for the next abstract to be added.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward addAbstract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalAbstract proposalAbstract = proposalDevelopmentForm.getNewProposalAbstract();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new AddAbstractEvent(proposalDevelopmentForm.getDocument(), proposalAbstract));
                    
        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            updateUserTimestamp(proposalAbstract);
            proposalAbstract.setTimestampDisplay((getService(DateTimeService.class)).getCurrentTimestamp());
            proposalAbstract.setUserDisplay(GlobalVariables.getUserSession().getPrincipalName());
            proposalDevelopmentForm.getDocument().getDevelopmentProposal().getProposalAbstracts().add(proposalAbstract);
            proposalDevelopmentForm.setNewProposalAbstract(new ProposalAbstract());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes an Abstract from the Proposal Development Document.
     * 
     * If the user confirms the deletion, the abstract is removed from
     * the document's list of abstracts.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    // START SNIPPET: deleteAbstract
    public ActionForward deleteAbstract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return confirm(buildDeleteAbstractConfirmationQuestion(mapping, form, request, response), CONFIRM_DELETE_ABSTRACT_KEY, EMPTY_STRING);
    }
    // END SNIPPET: deleteAbstract

    /**
     * Method dispatched from <code>{@link KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)}</code> for when a "yes" condition is met.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     * @see KraTransactionalDocumentActionBase#confirm(StrutsQuestion, String, String)
     */
    // START SNIPPET: confirmDeleteAbstract
    public ActionForward confirmDeleteAbstract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        
        int lineNum = getLineToDelete(request);

        if (CONFIRM_DELETE_ABSTRACT_KEY.equals(question)) { 
            ((ProposalDevelopmentForm) form).getDocument().getDevelopmentProposal().getProposalAbstracts().remove(lineNum);
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }        
    // END SNIPPET: confirmDeleteAbstract

    /**
     * Builds the Delete Abstract Confirmation Question as a <code>{@link StrutsConfirmation}</code> instance.<br/>  
     * <br/>
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the confirmation question
     * @throws Exception
     * @see buildParameterizedConfirmationQuestion
     */
    // START SNIPPET: buildDeleteAbstractConfirmationQuestion
    private StrutsConfirmation buildDeleteAbstractConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Abstracts are stored in a document. We need to document to retrieve the abstract.
        ProposalDevelopmentDocument doc = ((ProposalDevelopmentForm) form).getDocument();

        // Get the description. This will be used as a parameter to build the message for requesting confirmation feedback from the user.
        String description = doc.getDevelopmentProposal().getProposalAbstracts().get(getLineToDelete(request)).getAbstractType().getDescription();
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_DELETE_ABSTRACT_KEY, QUESTION_DELETE_ABSTRACT_CONFIRMATION, description);
    }
    // END SNIPPET: buildDeleteAbstractConfirmationQuestion

    /**
     * Builds the Delete Abstract Confirmation Question as a <code>{@link StrutsConfirmation}</code> instance.<br/>  
     * <br/>
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @param questionId String questionId. This needs to be unique for each type of attachment because there are different attachments to delete.
     * @return the confirmation question
     * @throws Exception
     * @see buildParameterizedConfirmationQuestion
     */
    private StrutsConfirmation buildDeleteAttachmentConfirmationQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String questionId) throws Exception {
        ProposalDevelopmentDocument doc = ((ProposalDevelopmentForm) form).getDocument();
        String description = null;
        String fileName = null;
        if (CONFIRM_DELETE_INSTITUTIONAL_ATTACHMENT_KEY.equals(questionId)) {
            description = INSTITUTIONAL_ATTACHMENT_TYPE_NAME;
            fileName = doc.getDevelopmentProposal().getInstituteAttachment(getLineToDelete(request)).getFileName();
        }
        else if (CONFIRM_DELETE_PERSONNEL_ATTACHMENT_KEY.equals(questionId)) {
            description = PERSONNEL_ATTACHMENT_TYPE_NAME;
            fileName = doc.getDevelopmentProposal().getPropPersonBio(getLineToDelete(request)).getFileName();
        }
        else if (CONFIRM_DELETE_PROPOSAL_ATTACHMENT_KEY.equals(questionId)) {
            description = PROPOSAL_ATTACHMENT_TYPE_NAME;
            fileName = doc.getDevelopmentProposal().getNarrative(getLineToDelete(request)).getFileName();
        }
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, questionId, QUESTION_DELETE_ATTACHMENT_CONFIRMATION, description, fileName);
    }

    @Override
    protected BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }
    
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    /**
     * Adds a personnel attachment.
     * 
     * Move the new attachment from the form 
     * into the document's list of personnelbiographyattachment.  The form's newpersonbio
     * is then cleared for the next personnel attachment to be added.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward addPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        if(getKualiRuleService().applyRules(new AddPersonnelAttachmentEvent(EMPTY_STRING, proposalDevelopmentDocument, proposalDevelopmentForm.getNewPropPersonBio()))){
            proposalDevelopmentDocument.getDevelopmentProposal().addProposalPersonBiography(proposalDevelopmentForm.getNewPropPersonBio());
            proposalDevelopmentForm.setNewPropPersonBio(new ProposalPersonBiography());
            populateTabState(proposalDevelopmentForm, "Personnel Attachments " + proposalDevelopmentDocument.getDevelopmentProposal().getPropPersonBios().size());
        } 

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Deletes a personnel attachment from the Proposal Development Document.
     * 
     * Removed the personnel attachment from the document's list of personnel attachments.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward deletePersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String questionId = CONFIRM_DELETE_PERSONNEL_ATTACHMENT_KEY;
        return confirm(buildDeleteAttachmentConfirmationQuestion(mapping, form, request, response, questionId), questionId, EMPTY_STRING);
    }

    /**
     * Deletes a personnel attachment from the Proposal Development Document.
     * 
     * Removed the personnel attachment from the document's list of personnel attachments.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward confirmDeletePersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        populateTabState((ProposalDevelopmentForm) form, "Personnel Attachments " + pdDoc.getDevelopmentProposal().getPropPersonBios().size());

        return deleteAttachment(mapping, form, request, response, CONFIRM_DELETE_PERSONNEL_ATTACHMENT_KEY, "deleteProposalPersonBiography");
    }

    /**
     * View a personnel attachment file.
     *      
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward viewPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalPersonBiography propPersonBio = pd.getDevelopmentProposal().getPropPersonBios().get(lineNumber);
        Map<String,String> propPersonBioAttVal = new HashMap<String,String>();
        propPersonBioAttVal.put(PROPOSAL_NUMBER, propPersonBio.getProposalNumber());
        propPersonBioAttVal.put(BIOGRAPHY_NUMBER, propPersonBio.getBiographyNumber()+"");
        propPersonBioAttVal.put(PROPOSAL_PERSON_NUMBER, propPersonBio.getProposalPersonNumber()+"");
        ProposalPersonBiographyAttachment propPersonBioAttachment = (ProposalPersonBiographyAttachment)getBusinessObjectService().findByPrimaryKey(ProposalPersonBiographyAttachment.class, propPersonBioAttVal);
        if(propPersonBioAttachment==null && !propPersonBio.getPersonnelAttachmentList().isEmpty()){//get it from the memory
            propPersonBioAttachment = propPersonBio.getPersonnelAttachmentList().get(0);
        }
        streamToResponse(propPersonBioAttachment,response);
        return  null;
    }


    /**
     * 
     * It add and institutional attachment to proposal document.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        Narrative narrative = proposalDevelopmentForm.getNewInstituteAttachment();
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        if(getKualiRuleService().applyRules(new AddInstituteAttachmentEvent(EMPTY_STRING, proposalDevelopmentDocument, narrative))){
            proposalDevelopmentDocument.getDevelopmentProposal().addInstituteAttachment(narrative);
            proposalDevelopmentForm.setNewInstituteAttachment(new Narrative());
            populateTabState(proposalDevelopmentForm, "Internal Attachments " + proposalDevelopmentDocument.getDevelopmentProposal().getInstituteAttachments().size());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);

    }

    /**
     * 
     * Delete an institutional attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String questionId = CONFIRM_DELETE_INSTITUTIONAL_ATTACHMENT_KEY;
        return confirm(buildDeleteAttachmentConfirmationQuestion(mapping, form, request, response, questionId), questionId, EMPTY_STRING);
    }

    /**
     * 
     * Delete an institutional attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        populateTabState((ProposalDevelopmentForm) form, "Internal Attachments " + pdDoc.getDevelopmentProposal().getInstituteAttachments().size());
        return deleteAttachment(mapping, form, request, response, CONFIRM_DELETE_INSTITUTIONAL_ATTACHMENT_KEY, "deleteInstitutionalAttachment");
    }

    /**
     * 
     * View an institutional attachment file.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
          //return downloadProposalAttachment(mapping, form, request, response);
          ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
          ProposalDevelopmentDocument pd = proposalDevelopmentForm.getDocument();
          String line = request.getParameter(LINE_NUMBER);
          int lineNumber = line == null ? 0 : Integer.parseInt(line);
          Narrative narrative = pd.getDevelopmentProposal().getInstituteAttachments().get(lineNumber);
          NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
          if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
              narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
          }
          streamToResponse(narrativeAttachment,response);
          return null;

    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#processAuthorizationViolation(java.lang.String, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward processAuthorizationViolation(String taskName, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = null;
        if (!StringUtils.equals(taskName, "addProposalAttachmentRights")) {
            forward = super.processAuthorizationViolation(taskName, mapping, form, request, response);
        }
        else {
            ErrorMap errorMap = GlobalVariables.getErrorMap();
            errorMap.putError(Constants.NEW_NARRATIVE_USER_RIGHTS_PROPERTY_KEY, KeyConstants.AUTHORIZATION_VIOLATION);
            ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
            int lineNumber = getLineNumber(request);
            request.setAttribute(LINE_NUMBER, Integer.toString(lineNumber));
            forward = mapping.findForward(MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE);
        }
        return forward;
    }
    
    private int getLineNumber(HttpServletRequest request) {
        int lineNumber = 0;
        String lineStr = request.getParameter(LINE_NUMBER);
        if (lineStr == null) {
            lineNumber = getLineToDelete(request);
        } else {
            try {
                lineNumber = Integer.parseInt(lineStr);
            } catch (Exception ex) {
                // do nothing; 0 will be returned
            }
        }
        return lineNumber;
    }
    
    /**
     * Hopefully a temporary fix to resolve the timestamp being the last post not the current post.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#insertBONote(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward insertBONote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        Note newNote = kualiDocumentFormBase.getNewNote();
        newNote.setNotePostedTimestamp(KNSServiceLocator.getDateTimeService().getCurrentTimestamp());
        return super.insertBONote(mapping, form, request, response);
    }
}
