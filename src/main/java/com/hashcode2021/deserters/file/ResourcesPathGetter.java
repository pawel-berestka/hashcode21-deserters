package com.hashcode2021.deserters.file;

import org.springframework.stereotype.Component;

@Component
public class ResourcesPathGetter {
    public String getResourcesFilePath(String fileName){
        if(null == fileName){
            throw new RuntimeException("Cannot get path to file in resources for null fileName.");
        }

        return "resources\\" + fileName;
    }
}
