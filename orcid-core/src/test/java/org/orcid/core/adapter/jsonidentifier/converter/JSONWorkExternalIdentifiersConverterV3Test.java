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
package org.orcid.core.adapter.jsonidentifier.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.orcid.core.utils.v3.identifiers.NormalizationService;
import org.orcid.jaxb.model.v3.dev1.record.ExternalID;
import org.orcid.jaxb.model.v3.dev1.record.ExternalIDs;
import org.orcid.jaxb.model.v3.dev1.record.Work;
import org.orcid.persistence.jpa.entities.PublicationDateEntity;
import org.orcid.persistence.jpa.entities.WorkEntity;
import org.orcid.utils.DateUtils;

public class JSONWorkExternalIdentifiersConverterV3Test {

    NormalizationService norm = new NormalizationService();//dummy;
    
    private JSONWorkExternalIdentifiersConverterV3 converter = new JSONWorkExternalIdentifiersConverterV3(norm);

    @Test
    public void testConvertTo() throws JAXBException {
        Work work = getWork();
        assertEquals(
                "{\"workExternalIdentifier\":[{\"relationship\":\"SELF\",\"url\":{\"value\":\"http://orcid.org\"},\"workExternalIdentifierType\":\"AGR\",\"workExternalIdentifierId\":{\"content\":\"work:external-identifier-id\"}}]}",
                converter.convertTo(work.getExternalIdentifiers(), null));
    }

    @Test
    public void testConvertFrom() {
        WorkEntity workEntity = getWorkEntity();
        ExternalIDs entityIDs = converter.convertFrom(workEntity.getExternalIdentifiersJson(), null);
        assertEquals(1, entityIDs.getExternalIdentifier().size());

        ExternalID externalID = entityIDs.getExternalIdentifier().get(0);
        assertEquals("123", externalID.getValue());
        assertNotNull(externalID.getType());
        assertEquals(org.orcid.jaxb.model.message.WorkExternalIdentifierType.AGR.value(), externalID.getType());
    }

    private Work getWork() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(new Class[] { Work.class });
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String name = "/record_2.0/samples/read_samples/work-full-2.0.xml";
        InputStream inputStream = getClass().getResourceAsStream(name);
        return (Work) unmarshaller.unmarshal(inputStream);
    }

    private WorkEntity getWorkEntity() {
        Date date = DateUtils.convertToDate("2015-06-05T10:15:20");
        WorkEntity work = new WorkEntity();
        work.setDateCreated(date);
        work.setLastModified(date);
        work.setOrcid("0000-0000-0000-0001");
        work.setVisibility(org.orcid.jaxb.model.common_v2.Visibility.LIMITED);
        work.setDisplayIndex(1234567890L);
        work.setClientSourceId("APP-5555555555555555");
        work.setCitation("work:citation");
        work.setCitationType(org.orcid.jaxb.model.record_v2.CitationType.BIBTEX);
        work.setDateCreated(date);
        work.setDescription("work:description");
        work.setId(12345L);
        work.setIso2Country(org.orcid.jaxb.model.common_v2.Iso3166Country.CR);
        work.setJournalTitle("work:journalTitle");
        work.setLanguageCode("EN");
        work.setLastModified(date);
        work.setPublicationDate(new PublicationDateEntity(2000, 1, 1));
        work.setSubtitle("work:subtitle");
        work.setTitle("work:title");
        work.setTranslatedTitle("work:translatedTitle");
        work.setTranslatedTitleLanguageCode("ES");
        work.setWorkType(org.orcid.jaxb.model.record_v2.WorkType.ARTISTIC_PERFORMANCE);
        work.setWorkUrl("work:url");
        work.setContributorsJson("{\"contributor\":[]}");
        work.setExternalIdentifiersJson("{\"workExternalIdentifier\":[{\"workExternalIdentifierType\":\"AGR\",\"workExternalIdentifierId\":{\"content\":\"123\"}}]}");
        return work;
    }

}
