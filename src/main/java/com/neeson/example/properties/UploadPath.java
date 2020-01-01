package com.neeson.example.properties;

import com.neeson.example.util.ParamsConfig;
import org.springframework.stereotype.Component;

@Component
public class UploadPath {

//    @Value("${upload.path}")
    private String path;

    public UploadPath() {
        path = ParamsConfig.getUploadDir();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


