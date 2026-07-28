/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.tomcatserver;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author palma
 */
public class TomcatApp {

    public TomcatApp(int port, String packageName, String appName) {
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(port);
        connector.setProperty("address", "0.0.0.0");
        tomcat.getService().addConnector(connector);
        String tempDir = System.getProperty("user.dir");
        tomcat.setBaseDir(tempDir);
        Context ctx = tomcat.addContext("", tempDir);
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(packageName);
        resourceConfig.register(OpenApiResource.class);
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        Tomcat.addServlet(ctx, "jersey-servlet", servletContainer);
        ctx.addServletMappingDecoded("/" + appName + "/api/*", "jersey-servlet");      
        try {
            tomcat.start();
        } catch (LifecycleException ex) {
            throw new RuntimeException(ex);
        }
        tomcat.getServer().await();
    }

}
