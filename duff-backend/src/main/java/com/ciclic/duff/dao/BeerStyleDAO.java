package com.ciclic.duff.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ciclic.duff.dto.BeerStyleDTO;
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
    private final String BEER_STYLE_QUERY = "select b.beer_id, b.style, b.min_temp, b.max_temp from ";

    private ApplicationProperties PROPERTIES;
    final String TABLE_SCHEMA;

    public BeerStyleDAO()
    {
        this.PROPERTIES = new ApplicationProperties();
        this.TABLE_SCHEMA = PROPERTIES.getApplicationPropertyByKey("duff_schema");
    }

    public List<BeerStyle> getAllBeerStyles()
    {
        List<BeerStyle> beerStyles = new ArrayList<BeerStyle>();

        StringBuilder query = new StringBuilder("select beer_id, style, max_temp, min_temp from ");
        query.append(TABLE_SCHEMA);
        query.append(".beer b where b.excluded = 0");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return null;

        try 
        {
            PreparedStatement queryStatement = connection.prepareStatement(query.toString());

            ResultSet queryResult = queryStatement.executeQuery();

            while(queryResult.next())
            {
                long styleId = queryResult.getLong("beer_id");
                String style = queryResult.getString("style");
                float minTemp = queryResult.getFloat("min_temp");
                float maxTemp = queryResult.getFloat("max_temp");

                BeerStyle beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp);

                beerStyles.add(beerStyle);
            }

            return beerStyles;
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();

            return null;
		}
    }

    public BeerStyle searchBeerStyleById(long id)
    {
        BeerStyle beerStyle = null;   

        StringBuilder queryString = new StringBuilder(BEER_STYLE_QUERY);

        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer b where b.excluded = 0 and b.beer_id = ?");

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

                beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp);
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
        queryString.append("beer b where b.excluded = 0");

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

                    beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp);
                }
                else if(TEMPERATURE_AVERAGE_DIFFERENCE_WITH_INPUT == currentTemperatureAverageDifference &&
                    StringFunctions.isStringLesserThanOther(style, beerStyle.getStyle())
                )
                {
                    long styleId = queryResult.getLong("beer_id");
    
                    beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp);
                }
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return null;
		}

        return beerStyle;
    }

    public BeerStyle getBeerStyleByName(String beerStyleName)
    {
        BeerStyle beerStyle = BeerStyle.getNone();

        StringBuilder queryString = new StringBuilder(BEER_STYLE_QUERY);
        queryString.append(TABLE_SCHEMA);
        queryString.append(".");
        queryString.append("beer b where b.excluded = 0 and b.style = ?");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return beerStyle;

        PreparedStatement queryStatement;

        try 
        {
            queryStatement = connection.prepareStatement(queryString.toString());
            queryStatement.setString(1, beerStyleName);

            ResultSet queryResult = queryStatement.executeQuery();

            if(queryResult.next())
            {
                long styleId = queryResult.getLong("beer_id");
                String style = queryResult.getString("style");
                float minTemp = queryResult.getFloat("min_temp");
                float maxTemp = queryResult.getFloat("max_temp");

                beerStyle = new BeerStyle(styleId, style, maxTemp, minTemp);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return beerStyle;
    }

    public String addNewStyle(BeerStyleDTO newStyle)
    {
        StringBuilder query = new StringBuilder("insert into ");
        query.append(TABLE_SCHEMA);
        query.append(".beer (beer_id, style, max_temp, min_temp) ");
        query.append("values");
        query.append("(");
        query.append(TABLE_SCHEMA);
        query.append(".BEER_SEQ.nextval, ?, ?, ?)");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return "Database Connection Error";

        PreparedStatement statement;
        try 
        {
            statement = connection.prepareStatement(query.toString());

            statement.setString(1, newStyle.getStyle());
            statement.setDouble(2, newStyle.getMaximumTemperature());
            statement.setDouble(3, newStyle.getMinimumTemperature());

            statement.execute();
            return "Success";
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "Fail, SQL Exception";
        }

    }

    public String deleteBeerStyle(String beerStyleToDeleteName)
    {
        StringBuilder deleteString = new StringBuilder("update ");
        deleteString.append(TABLE_SCHEMA);
        deleteString.append(".beer b set excluded = 1 where b.style = ?");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return "Database Connection Error";

        PreparedStatement statement;

        try 
        {
            statement = connection.prepareStatement(deleteString.toString());
            statement.setString(1, beerStyleToDeleteName);

            statement.execute();

            return "Success";
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "SQLException";
        }

    }

    public String updateBeerStyle(BeerStyleDTO beerStyleToUpdate)
    {
        StringBuilder updateString = new StringBuilder("update ");
        updateString.append(TABLE_SCHEMA);
        updateString.append(".beer b set max_temp = ?, min_temp = ? where style = ?");

        Connection connection = DatabaseConnection.getInstance().getConnection();
        if(connection == null) return "Database Connection Error";

        PreparedStatement updateStatement;

        try 
        {
            updateStatement = connection.prepareStatement(updateString.toString());

            updateStatement.setDouble(1, beerStyleToUpdate.getMaximumTemperature());
            updateStatement.setDouble(2, beerStyleToUpdate.getMinimumTemperature());
            updateStatement.setString(3, beerStyleToUpdate.getStyle());

            updateStatement.execute();

            return "Success";
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return "SQLException";
        }
    }
}