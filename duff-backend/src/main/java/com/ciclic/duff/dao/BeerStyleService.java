package com.ciclic.duff.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.ciclic.duff.model.BeerStyle;
import com.ciclic.duff.util.ApplicationProperties;

public class BeerStyleService
{
    /**
     * select b.beer_id, b.style, b.min_temp, b.max_temp, bp.playlist_url
     * from ${schema}.beer b 
     * left join 
     * ${schema}.beer_playlist bp
     * on b.beer_id = bp.beer_id
     * wheer b.beer_id = ${id}
     */
    private final String BEER_STYLE_QUERY = "select b.beer_id, b.style, b.min_temp, b.max_temp, bp.playlist_url from ";

    private ApplicationProperties PROPERTIES;

    public BeerStyle searchBeerStyleById(long id)
    {
        BeerStyle beerStyle = null;   

        StringBuilder queryString = new StringBuilder(BEER_STYLE_QUERY);
        final String TABLE_SCHEMA = PROPERTIES.getApplicationPropertyByKey("duff_schema");

        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer b left join ");
        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer_playlist bp on b.beer_id = bp.beer_id where b.beer_id = ?");

        Connection connection = DatabaseConnection.getInstance().getConnection();

        try 
        {
            PreparedStatement queryStatement = connection.prepareStatement(queryString.toString());
            queryStatement.setLong(1, id);
            ResultSet queryResult = queryStatement.executeQuery();

            if(queryResult.next())
            {
                long styleId = queryResult.getLong("beer_id");
                String style = queryResult.getString("style");
                float minTemp = queryResult.getFloat("min_temp");
                float maxTemp = queryResult.getFloat("max_temp");
                String url = queryResult.getString("playlist_url");

                beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp, url);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
		}

        return beerStyle;
    }
}