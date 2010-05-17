/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;
/**
 * 
 * This class is represents S2S_REVISION_TYPE table
 */
public class S2sRevisionType extends KraPersistableBusinessObjectBase {
	private String s2sRevisionTypeCode;
	private String description;

	public String getS2sRevisionTypeCode() {
		return s2sRevisionTypeCode;
	}

	public void setS2sRevisionTypeCode(String s2sRevisionTypeCode) {
		this.s2sRevisionTypeCode = s2sRevisionTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("s2sRevisionTypeCode", getS2sRevisionTypeCode());
		hashMap.put("description", getDescription());
		return hashMap;
	}
}
