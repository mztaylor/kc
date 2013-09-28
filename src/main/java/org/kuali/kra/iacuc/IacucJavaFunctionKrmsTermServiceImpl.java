/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc;

import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.amendrenew.IacucProtocolModule;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolJavaFunctionKrmsTermServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IacucJavaFunctionKrmsTermServiceImpl extends ProtocolJavaFunctionKrmsTermServiceBase implements IacucJavaFunctionKrmsTermService {
    private BusinessObjectService businessObjectService;

    @Override
    protected ProtocolBase getActiveProtocol(String protocolNumber) {
        Map<String,String> keyMap = new HashMap<String,String>();
        keyMap.put("protocolNumber", protocolNumber);
        List<IacucProtocol> protocols = (List <IacucProtocol>)getBusinessObjectService().findMatchingOrderBy(
                            IacucProtocol.class, keyMap, "sequenceNumber", false);
        return protocols.get(0);
    }

    @Override
    public String getRenewalActionTypeCode() {
        return IacucProtocolActionType.RENEWAL_CREATED;
    }

    @Override
    public String getProtocolPersonnelModuleTypeCode() {
        return IacucProtocolModule.PROTOCOL_PERSONNEL;
    }

    @Override
    public String getProtocolOrganizationModuleTypeCode() {
        return IacucProtocolModule.PROTOCOL_ORGANIZATIONS;
    }

    @Override
    public String getNotifySubmissionTypeCode() {
        return IacucProtocolSubmissionType.FYI;
    }


    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }


    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
