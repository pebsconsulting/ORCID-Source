/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
package org.orcid.core.manager.v3.read_only.impl;

import java.util.List;

import org.orcid.core.manager.v3.read_only.ActivitiesSummaryManagerReadOnly;
import org.orcid.core.manager.v3.read_only.AffiliationsManagerReadOnly;
import org.orcid.core.manager.v3.read_only.PeerReviewManagerReadOnly;
import org.orcid.core.manager.v3.read_only.ProfileFundingManagerReadOnly;
import org.orcid.core.manager.v3.read_only.WorkManagerReadOnly;
import org.orcid.jaxb.model.v3.dev1.common.Visibility;
import org.orcid.jaxb.model.v3.dev1.record.summary.ActivitiesSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.DistinctionSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Distinctions;
import org.orcid.jaxb.model.v3.dev1.record.summary.EducationSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Educations;
import org.orcid.jaxb.model.v3.dev1.record.summary.EmploymentSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Employments;
import org.orcid.jaxb.model.v3.dev1.record.summary.FundingSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Fundings;
import org.orcid.jaxb.model.v3.dev1.record.summary.InvitedPositionSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.InvitedPositions;
import org.orcid.jaxb.model.v3.dev1.record.summary.MembershipSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Memberships;
import org.orcid.jaxb.model.v3.dev1.record.summary.PeerReviewSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.PeerReviews;
import org.orcid.jaxb.model.v3.dev1.record.summary.QualificationSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Qualifications;
import org.orcid.jaxb.model.v3.dev1.record.summary.ServiceSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Services;
import org.orcid.jaxb.model.v3.dev1.record.summary.WorkSummary;
import org.orcid.jaxb.model.v3.dev1.record.summary.Works;

public class ActivitiesSummaryManagerReadOnlyImpl extends ManagerReadOnlyBaseImpl implements ActivitiesSummaryManagerReadOnly {    
    private AffiliationsManagerReadOnly affiliationsManager;
    
    private ProfileFundingManagerReadOnly fundingManager;
    
    private PeerReviewManagerReadOnly peerReviewManager;
    
    private WorkManagerReadOnly workManager;

    public void setAffiliationsManager(AffiliationsManagerReadOnly affiliationsManager) {
        this.affiliationsManager = affiliationsManager;
    }

    public void setFundingManager(ProfileFundingManagerReadOnly fundingManager) {
        this.fundingManager = fundingManager;
    }

    public void setPeerReviewManager(PeerReviewManagerReadOnly peerReviewManager) {
        this.peerReviewManager = peerReviewManager;
    }

    public void setWorkManager(WorkManagerReadOnly workManager) {
        this.workManager = workManager;
    }    

    @Override
    public ActivitiesSummary getActivitiesSummary(String orcid) {
        return getActivitiesSummary(orcid, false);
    }

    @Override
    public ActivitiesSummary getPublicActivitiesSummary(String orcid) {
        return getActivitiesSummary(orcid, true);
    }

    public ActivitiesSummary getActivitiesSummary(String orcid, boolean justPublic) {
        ActivitiesSummary activities = new ActivitiesSummary();

        // Set distinctions
        List<DistinctionSummary> distinctionsList = affiliationsManager.getDistinctionSummaryList(orcid);
        Distinctions distinctions = new Distinctions();
        for (DistinctionSummary summary : distinctionsList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    distinctions.getSummaries().add(summary);
                }
            } else {
                distinctions.getSummaries().add(summary);
            }
        }
        activities.setDistinctions(distinctions);
        
        // Set educations
        List<EducationSummary> educationsList = affiliationsManager.getEducationSummaryList(orcid);
        Educations educations = new Educations();
        for (EducationSummary summary : educationsList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    educations.getSummaries().add(summary);
                }
            } else {
                educations.getSummaries().add(summary);
            }
        }
        activities.setEducations(educations);

        // Set employments
        List<EmploymentSummary> employmentList = affiliationsManager.getEmploymentSummaryList(orcid);

        Employments employments = new Employments();
        for (EmploymentSummary summary : employmentList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    employments.getSummaries().add(summary);
                }
            } else {
                employments.getSummaries().add(summary);
            }
        }
        activities.setEmployments(employments);

        // Set invited positions
        List<InvitedPositionSummary> invitedPositionsList = affiliationsManager.getInvitedPositionSummaryList(orcid);
        InvitedPositions invitedPositions = new InvitedPositions();
        for (InvitedPositionSummary summary : invitedPositionsList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    invitedPositions.getSummaries().add(summary);
                }
            } else {
                invitedPositions.getSummaries().add(summary);
            }
        }
        activities.setInvitedPositions(invitedPositions);
        
        // Set memberships
        List<MembershipSummary> membershipsList = affiliationsManager.getMembershipSummaryList(orcid);
        Memberships memberships = new Memberships();
        for (MembershipSummary summary : membershipsList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    memberships.getSummaries().add(summary);
                }
            } else {
                memberships.getSummaries().add(summary);
            }
        }
        activities.setMemberships(memberships);
        
        // Set qualifications
        List<QualificationSummary> qualificationsList = affiliationsManager.getQualificationSummaryList(orcid);
        Qualifications qualifications = new Qualifications();
        for (QualificationSummary summary : qualificationsList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    qualifications.getSummaries().add(summary);
                }
            } else {
                qualifications.getSummaries().add(summary);
            }
        }
        activities.setQualifications(qualifications);
        
        // Set services
        List<ServiceSummary> servicesList = affiliationsManager.getServiceSummaryList(orcid);
        Services services = new Services();
        for (ServiceSummary summary : servicesList) {
            if (justPublic) {
                if (Visibility.PUBLIC.equals(summary.getVisibility())) {
                    services.getSummaries().add(summary);
                }
            } else {
                services.getSummaries().add(summary);
            }
        }
        activities.setServices(services);
        
        // Set fundings
        List<FundingSummary> fundingSummaries = fundingManager.getFundingSummaryList(orcid);
        Fundings fundings = fundingManager.groupFundings(fundingSummaries, justPublic);        
        activities.setFundings(fundings);

        // Set peer reviews
        List<PeerReviewSummary> peerReviewSummaries = peerReviewManager.getPeerReviewSummaryList(orcid);
        PeerReviews peerReviews = peerReviewManager.groupPeerReviews(peerReviewSummaries, justPublic);        
        activities.setPeerReviews(peerReviews);

        // Set works
        List<WorkSummary> workSummaries = workManager.getWorksSummaryList(orcid);
        Works works = workManager.groupWorks(workSummaries, justPublic);        
        activities.setWorks(works);

        return activities;
    }
}
