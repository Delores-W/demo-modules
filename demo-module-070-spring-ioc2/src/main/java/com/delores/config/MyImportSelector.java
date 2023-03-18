package com.delores.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author William
 * @date 4/6/21 12:08 PM
 * @description
 */
public class MyImportSelector implements ImportSelector {
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.delores.config.ThirdPartClass"};
    }
}
