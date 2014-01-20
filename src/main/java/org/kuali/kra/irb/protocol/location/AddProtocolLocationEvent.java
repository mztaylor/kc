/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol.location;

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.krad.document.Document;

/**
 * This class represents the AddProtocolLocationEvent
 */
public class AddProtocolLocationEvent extends org.kuali.kra.protocol.protocol.location.AddProtocolLocationEvent {

    public AddProtocolLocationEvent(String errorPathPrefix, ProtocolDocument document, ProtocolLocation protocolLocation) {
        super(errorPathPrefix, document, protocolLocation);
    }
    
    public AddProtocolLocationEvent(String errorPathPrefix, Document document, ProtocolLocation protocolLocation) {
        this(errorPathPrefix, (ProtocolDocument) document, protocolLocation);
    }

}
