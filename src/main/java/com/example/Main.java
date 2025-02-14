package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        // ポート番号を設定
        tomcat.getConnector().setPort(8080);
        Context context = tomcat.addContext("", System.getProperty("java.io.tmpdir"));
        // サーブレットの追加
        Tomcat.addServlet(context, "helloServlet", new HelloServlet())
                .addMapping("/");
        // フィルターの追加
        FilterDef filterDef = new FilterDef();
        filterDef.setFilter(new HelloFilter());
        filterDef.setFilterName("helloFilter");
        context.addFilterDef(filterDef);
        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName("helloFilter");
        filterMap.addURLPattern("/*");
        context.addFilterMap(filterMap);
        // Tomcat起動
        tomcat.start();
        // シャットダウンされるまで待つ
        tomcat.getServer().await();
    }
}
