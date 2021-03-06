/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.attachment;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;

import java.util.List;


/**
 * Base implementation for events triggered when a Key Person state is modified on a
 * <code>{@link ProposalDevelopmentDocument}</code>
 * 
 */
public abstract class NarrativeEventBase extends KcDocumentEventBase implements NarrativeEvent {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(NarrativeEventBase.class);

    private Narrative narrative;
    private List<Narrative> narratives;

    /**
     * 
     * Constructs a NarrativeEventBase
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     * @param narrative
     */
    protected NarrativeEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document,
            Narrative narrative) {
        super(description, errorPathPrefix, document);
        if (narrative != null) {
            this.narrative = narrative;
        }
        narratives = document.getDevelopmentProposal().getNarratives();
        logEvent();
    }

    /**
     * 
     * Constructs a NarrativeEventBase.
     * 
     * @param description
     * @param errorPathPrefix
     * @param document
     */
    protected NarrativeEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument document) {
        super(description, errorPathPrefix, document);
        narratives = document.getDevelopmentProposal().getNarratives();
    }


    /**
     * @return <code>{@link Narrative}</code> that triggered this event.
     */
    public Narrative getNarrative() {
        return narrative;
    }

    /**
     * @return <code>{@link Narrative}</code> that triggered this event.
     */
    public List<Narrative> getNarratives() {
        return narratives;
    }

    /**
     * Logs the event type and some information about the associated accountingLine
     */
    protected void logEvent() {
        LOG.debug(getDescription());
    }
}
