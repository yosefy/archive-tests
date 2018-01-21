package org.bb.qa.archive.helpers;

import org.apache.commons.lang3.StringUtils;
import org.bb.qa.common.configuration.Configuration;
import org.bb.qa.common.utils.Language;

public class UrlBuilder {

    private String siteUrl;

    public UrlBuilder() {
        this(Configuration.getSiteUrl());
    }

    public UrlBuilder(String siteUrl) {
        this.siteUrl = siteUrl;
    }


    public String getUrlForPath(String path) {
        return getUrlForPath(path, Language.DEFAULT);
    }

    public String getUrlForPath(String path, Language lang) {
        String fPath = siteUrl;

        if (lang != Language.DEFAULT) {
            fPath += fPath.endsWith("/") ? "" : "/";
            fPath += lang.getCode();
        }

        if (StringUtils.isNotBlank(path)) {
            fPath += fPath.endsWith("/") ? "" : "/";
            fPath += path;
        }

        return fPath;
    }

}
