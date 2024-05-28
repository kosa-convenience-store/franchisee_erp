package main.java.com.ouibak.erp.dao;

import java.io.*;
import java.util.Properties;

public class Query {
    private static Properties properties;

    public Query() {
    }

    static {

        try {
            properties = new Properties();
            properties.loadFromXML(new FileInputStream("src/resource/query.xml"));
        } catch (FileNotFoundException e) {
            System.out.println("예외: 지정한 파일을 찾을수없습니다 :" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void getprop() {
        if (properties == null) {
            Query.getprop();
        }
    }

    public static String getQuery(String sqlKey) {
        getprop();
        return properties.getProperty(sqlKey);
    }
}
