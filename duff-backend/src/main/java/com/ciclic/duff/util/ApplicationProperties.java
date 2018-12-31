package com.ciclic.duff.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties
{
    private InputStream loadApplicationProperties()
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
    
        return classLoader.getResourceAsStream("application.properties");
    }

    public String getApplicationPropertyByKey(String propertyKey)
    {
        final Properties PROPERTIES = new Properties();

        try 
        {
            PROPERTIES.load(loadApplicationProperties());
        } catch (IOException e) 
        {
            e.printStackTrace();
            return null;
        }

        return PROPERTIES.getProperty(propertyKey);
    }
}