package com.ciclic.duff.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ciclic.duff.util.ApplicationProperties;

public class DatabaseConnection
{
    Connection connection;

    private DatabaseConnection()
    {
        try 
        {
            ApplicationProperties properties = new ApplicationProperties();
            final String URL = properties.getApplicationPropertyByKey("database_url");
            final String USER = properties.getApplicationPropertyByKey("duff_user");
            final String PASSWD = properties.getApplicationPropertyByKey("duff_passwd");
            this.connection = DriverManager.getConnection(URL, USER, PASSWD);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            this.connection = null;
		} 
    }

    public static DatabaseConnection getInstance()
    {
        return new DatabaseConnection();
    }

    public Connection getConnection(){return this.connection;}
}