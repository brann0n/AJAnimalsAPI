package com.brandon.animalapi;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AnimalsAPI extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses () {
        return new Class<?>[]{AnimalApiConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses () {
        return null;
    }

    @Override
    protected String[] getServletMappings () {
        return new String[]{"/"};
    }
}
