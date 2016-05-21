package com.benkeit.sample.filter;

import org.sitemesh.DecoratorSelector;
import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.Content;
import org.sitemesh.webapp.WebAppContext;
import org.springframework.mobile.device.site.SitePreference;

import java.io.IOException;

public class SpringMobileDecoratorSelector implements DecoratorSelector {
    private final DecoratorSelector fallbackSelector;

    public SpringMobileDecoratorSelector(DecoratorSelector fallbackSelector) {
        this.fallbackSelector = fallbackSelector;
    }

    public String[] selectDecoratorPaths(Content content, SiteMeshContext context) throws IOException {
        SitePreference sitePreference = (SitePreference) ((WebAppContext) context).getRequest().getAttribute("currentSitePreference");
        final String[] decorators = fallbackSelector.selectDecoratorPaths(content, context);

        if (sitePreference.isMobile()) {
            if (decorators != null && decorators.length > 0) {
                String[] tempDecorators = new String[decorators.length];
                for (int i = 0; i < decorators.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder(decorators[i]);
                    tempDecorators[i] = stringBuilder.insert(20, sitePreference.name().toLowerCase() + "/").toString();
                }
                return tempDecorators;
            }
        }
        return decorators;
    }

}
