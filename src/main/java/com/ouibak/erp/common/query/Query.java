package main.java.com.ouibak.erp.common.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Query {
    private static Properties properties = new Properties();

    private Query(){}

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

    public static String getQuery(String sqlkey) {
        if (properties == null) { new Query();}
        return properties.getProperty(sqlkey);
    }
}
