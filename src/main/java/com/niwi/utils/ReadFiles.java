package com.niwi.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadFiles {
    static String filepath=System.getProperty("user.dir")+"/src/test/resources/datasets/";
    static Properties properties;
    static FileInputStream fileInputStream;
    public static Properties readPropertyFile(String filename)
    {
        try {
            fileInputStream = new FileInputStream(filepath + filename);
            properties = new Properties();
            properties.load(fileInputStream);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getDataFromPropertyFile(String filename,String propertykey)
    {
        String value=null;
        try{
            properties=readPropertyFile(filename);
            value=properties.getProperty(propertykey);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }






}
