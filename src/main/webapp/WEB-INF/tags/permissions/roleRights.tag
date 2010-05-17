<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
<%@ attribute name="roleName" required="true" %>
<%@ attribute name="permissions" required="true" type="java.util.List" %>

<div class="tab-container" align="center" style="margin:0px; padding:0px; border-width:0px">
	<h3>
    	<span class="subhead-left">${roleName}</span>
 	</h3>
 
 	<table cellpadding="0" cellspacing="0" summary="">
    	<c:forEach var="permission" items="${permissions}" varStatus="status">
    		<tr>
				<th align="right" valign="middle" width="40%">${permission.name}:</th>
    			<td align="left" valign="middle">${permission.description}</td>
    		</tr>
	    </c:forEach>
    </table>
</div>
