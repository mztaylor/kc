/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.specialreview;

import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.AbstractSpecialReviewExemption;

/**
 * This class represents AwardSpecialReviewExemption
 */
public class AwardSpecialReviewExemption extends AbstractSpecialReviewExemption implements SequenceAssociate<Award> {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2155591303526248765L;
    private Long awardSpecialReviewExemptionId;
    private AwardSpecialReview awardSpecialReview;
    
    private Award award;
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = super.toStringMapper();
        hashMap.put("awardSpecialReviewExemptionId", getAwardSpecialReviewExemptionId());
        return hashMap;
    }
    /**
     * Gets the awardSpecialReviewExemptionId attribute. 
     * @return Returns the awardSpecialReviewExemptionId.
     */
    public Long getAwardSpecialReviewExemptionId() {
        return awardSpecialReviewExemptionId;
    }
    /**
     * Sets the awardSpecialReviewExemptionId attribute value.
     * @param awardSpecialReviewExemptionId The awardSpecialReviewExemptionId to set.
     */
    public void setAwardSpecialReviewExemptionId(Long awardSpecialReviewExemptionId) {
        this.awardSpecialReviewExemptionId = awardSpecialReviewExemptionId;
    }
    /**
     * Gets the awardSpecialReview attribute. 
     * @return Returns the awardSpecialReview.
     */
    public AwardSpecialReview getAwardSpecialReview() {
        return awardSpecialReview;
    }
    /**
     * Sets the awardSpecialReview attribute value.
     * @param awardSpecialReview The awardSpecialReview to set.
     */
    public void setAwardSpecialReview(AwardSpecialReview awardSpecialReview) {
        this.awardSpecialReview = awardSpecialReview;
    }
    
    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardSpecialReviewExemptionId = null;
    }
    public Award getSequenceOwner() {
        return award;
    }
    public void setSequenceOwner(Award newlyVersionedOwner) {
        setAward(newlyVersionedOwner);
        
    }
    /**
     * @see org.kuali.kra.Sequenceable#getSequenceNumber()
     */
    public Integer getSequenceNumber() {
        return award != null ? award.getSequenceNumber() : null;
    }
    public Award getAward() {
        return award;
    }
    public void setAward(Award award) {
        this.award = award;
    }
}
