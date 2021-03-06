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
package org.orcid.core.adapter.v3.impl;

import java.util.Collection;
import java.util.List;

import ma.glasnost.orika.MapperFacade;

import org.orcid.core.adapter.v3.JpaJaxbMembershipAdapter;
import org.orcid.jaxb.model.v3.dev1.record.summary.MembershipSummary;
import org.orcid.jaxb.model.v3.dev1.record.Membership;
import org.orcid.persistence.jpa.entities.OrgAffiliationRelationEntity;

/**
 * 
 * @author Angel Montenegro
 * 
 */
public class JpaJaxbMembershipAdapterImpl implements JpaJaxbMembershipAdapter {

    private MapperFacade mapperFacade;

    public void setMapperFacade(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    @Override
    public OrgAffiliationRelationEntity toOrgAffiliationRelationEntity(Membership membership) {
        if(membership == null)
            return null;
        return mapperFacade.map(membership, OrgAffiliationRelationEntity.class);
    }

    @Override
    public Membership toMembership(OrgAffiliationRelationEntity entity) {
        if(entity == null)
            return null;
        return mapperFacade.map(entity, Membership.class);
    }

    public MembershipSummary toMembershipSummary(OrgAffiliationRelationEntity entity) {
        if(entity == null)
            return null;
        return mapperFacade.map(entity, MembershipSummary.class);
    }
    
    @Override
    public List<Membership> toMembership(Collection<OrgAffiliationRelationEntity> entities) {
        if(entities == null)
            return null;
        return mapperFacade.mapAsList(entities, Membership.class);
    }

    @Override
    public List<MembershipSummary> toMembershipSummary(Collection<OrgAffiliationRelationEntity> entities) {
        if(entities == null)
            return null;
        return mapperFacade.mapAsList(entities, MembershipSummary.class);
    }
    
    @Override
    public OrgAffiliationRelationEntity toOrgAffiliationRelationEntity(Membership membership, OrgAffiliationRelationEntity existing) {
        if (membership == null) {
            return null;
        }
        mapperFacade.map(membership, existing);
        return existing;
    }
}
