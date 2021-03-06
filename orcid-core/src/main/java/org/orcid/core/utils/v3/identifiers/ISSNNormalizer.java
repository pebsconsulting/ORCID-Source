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
package org.orcid.core.utils.v3.identifiers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class ISSNNormalizer implements Normalizer{

    private static final List<String> canHandle = Lists.newArrayList("issn");
    private static final Pattern pattern = Pattern.compile("(?:^|[^\\d])(\\d{4}\\ {0,1}[-–]{0,1}\\ {0,1}\\d{3}[\\dXx])(?:$|[^-\\d])");
    
    @Override
    public List<String> canHandle() {
        return canHandle;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public String normalise(String apiTypeName, String value) {
        if (!canHandle.contains(apiTypeName))
            return value;
        Matcher m = pattern.matcher(value);
        if (m.find()){
            String n = m.group(1);
            if (n != null){
                n = n.replace(" ", "");
                n = n.replace("-", "");
                n = n.replace("–", "");
                n = n.replace("x", "X");
                n= n.substring(0,4) +"-"+n.substring(4,8);//0000-000X
                return n;
            }
        }
        return "";
    }

}
