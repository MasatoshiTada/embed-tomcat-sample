package com.example;

import com.example.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector().setPort(8080);
        Context context = tomcat.addContext("", "tmp");
        Tomcat.addServlet(context, "helloServlet", new HelloServlet())
                .addMapping("/");
        tomcat.start();
        tomcat.getServer().await();
    }
}
