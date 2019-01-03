package com.ciclic.duff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ciclic.duff.model.BeerStyle;
import com.ciclic.duff.util.ApplicationProperties;
import com.ciclic.duff.util.StringFunctions;

public class BeerStyleDAO
{
    /**
     * select b.beer_id, b.style, b.min_temp, b.max_temp, bp.playlist_url
     * from ${schema}.beer b 
     * left join 
     * ${schema}.beer_playlist bp
     * on b.beer_id = bp.beer_id
     * where b.beer_id = ${id}
     */
    private final String BEER_STYLE_QUERY = "select b.beer_id, b.style, b.min_temp, b.max_temp, bp.playlist_id from ";

    private ApplicationProperties PROPERTIES;
    final String TABLE_SCHEMA;

    public BeerStyleDAO()
    {
        this.PROPERTIES = new ApplicationProperties();
        this.TABLE_SCHEMA = PROPERTIES.getApplicationPropertyByKey("duff_schema");
    }

    public BeerStyle searchBeerStyleById(long id)
    {
        BeerStyle beerStyle = null;   

        StringBuilder queryString = new StringBuilder(BEER_STYLE_QUERY);

        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer b left join ");
        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer_playlist bp on b.beer_id = bp.beer_id where b.beer_id = ?");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return null;
        
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
                String playlistId = queryResult.getString("playlist_id");

                beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp, playlistId);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
		}

        return beerStyle;
    }

    public BeerStyle getBeerThatFitsTemperature(double temperature)
    {
        BeerStyle beerStyle = BeerStyle.getNone();

        StringBuilder queryString = new StringBuilder(BEER_STYLE_QUERY);
        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer b left join ");
        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer_playlist bp on b.beer_id = bp.beer_id");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return null;
        
        PreparedStatement queryStatement;
        try 
        {
			queryStatement = connection.prepareStatement(queryString.toString());
            ResultSet queryResult = queryStatement.executeQuery();

            double currentTemperatureAverageDifference = Double.MAX_VALUE;
            
            while(queryResult.next())
            {
                float minTemp = queryResult.getFloat("min_temp");
                float maxTemp = queryResult.getFloat("max_temp");
                
                if(temperature > maxTemp || temperature < minTemp)
                {
                    continue;
                }

                final double TEMPERATURE_AVERAGE = (maxTemp + minTemp)/2.0;
                final double TEMPERATURE_AVERAGE_DIFFERENCE_WITH_INPUT = Math.abs(TEMPERATURE_AVERAGE - temperature);
                String style = queryResult.getString("style");

                if(TEMPERATURE_AVERAGE_DIFFERENCE_WITH_INPUT < currentTemperatureAverageDifference || beerStyle == null)
                {
                    currentTemperatureAverageDifference = TEMPERATURE_AVERAGE_DIFFERENCE_WITH_INPUT;
                    long styleId = queryResult.getLong("beer_id");
                    String playlistId = queryResult.getString("playlist_id");

                    beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp, playlistId);
                }
                else if(TEMPERATURE_AVERAGE_DIFFERENCE_WITH_INPUT == currentTemperatureAverageDifference &&
                    StringFunctions.isStringLesserThanOther(style, beerStyle.getStyle())
                )
                {
                    long styleId = queryResult.getLong("beer_id");
                    String playlistId = queryResult.getString("playlist_id");
    
                    beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp, playlistId);
                }
            }

        } catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
		}

        return beerStyle;
    }
}