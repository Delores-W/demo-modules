package com.delores.medusa.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author William
 * @date 4/28/21 1:38 PM
 * @description TODO:
 */
//@Configuration
public class AJPConnectorConfig {
//    @Value("${ajp.port}")
//    int ajpPort;
//
//    @Value("${ajp.enabled}")
//    boolean ajpEnabled;
//
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//
//        if (ajpEnabled) {
//            Connector ajpConnector = new Connector("AJP/1.3");
//            ajpConnector.setPort(ajpPort);
//            ajpConnector.setSecure(false);
//            ajpConnector.setScheme("http");
//            ajpConnector.setAllowTrace(false);
//            tomcat.addAdditionalTomcatConnectors(ajpConnector);
//        }
//
//        return tomcat;
//    }
}
