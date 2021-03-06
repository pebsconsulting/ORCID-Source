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
package org.orcid.core.exception;

import javax.ws.rs.core.Response;

public class OrcidForbiddenException extends OrcidApiException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OrcidForbiddenException(String message) {
        super(message, Response.Status.FORBIDDEN);
    }

    public OrcidForbiddenException(String message, Throwable t) {
        super(message, Response.Status.FORBIDDEN, t);
    }
}
