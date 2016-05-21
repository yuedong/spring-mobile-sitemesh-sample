package com.benkeit.sample.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SpringMobileSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.setCustomDecoratorSelector(
                new SpringMobileDecoratorSelector(builder.getDecoratorSelector()));
    }

}
