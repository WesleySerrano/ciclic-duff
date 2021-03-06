package com.ciclic.duff.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ciclic.duff.dao.BeerStyleDAO;
import com.ciclic.duff.dto.BeerStyleDTO;
import com.ciclic.duff.model.BeerStyle;
import com.ciclic.duff.util.StringFunctions;

public class BeerStylesService
{
    public static BeerStyleDTO modelToDTO(BeerStyle beerStyle)
    {
        return new BeerStyleDTO(beerStyle.getStyle(), beerStyle.getMaximumTemperature(), beerStyle.getMinimumTemperature());
    }

    public static List<BeerStyleDTO> getAllBeerStyles()
    {
        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();
        List<BeerStyle> beerStyles = beerStyleDAO.getAllBeerStyles();

        return beerStyles.stream().map(beerStyle -> modelToDTO(beerStyle)).collect(Collectors.toList());
    }

    public static List<String> getAllBeerStylesNames()
    {
        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();
        List<BeerStyle> beerStyles = beerStyleDAO.getAllBeerStyles();

        return beerStyles.stream().map(beerStyle -> beerStyle.getStyle()).collect(Collectors.toList());
    }

    public static String addNewBeerStyle(BeerStyleDTO newBeerStyle)
    {
        if(newBeerStyle.getMaximumTemperature() < newBeerStyle.getMinimumTemperature()) return "Maximum temperature lesser than minimum temperature";

        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();

        if(!StringFunctions.validateString(newBeerStyle.getStyle())) return "Style name not valid";

        return beerStyleDAO.addNewStyle(newBeerStyle);
    }

    public static String deleteBeerStyle(BeerStyleDTO beerStyleToDelete)
    {
        if(StringFunctions.validateString(beerStyleToDelete.getStyle()))
        {        
            BeerStyleDAO beerStyleDAO = new BeerStyleDAO();
            return beerStyleDAO.deleteBeerStyle(beerStyleToDelete.getStyle());
        }
        else
        {
            return "Beer style name not valid";
        }
    }

    public static String updateBeerStyle(BeerStyleDTO beerStyleToUpdate)
    {
        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();

        BeerStyle beerStyle = beerStyleDAO.getBeerStyleByName(beerStyleToUpdate.getStyle());

        if(BeerStyle.isNone(beerStyle)) return "Beer style not exists on database";
        else if(beerStyleToUpdate.getMaximumTemperature() < beerStyleToUpdate.getMinimumTemperature()) return "Maximum temperature lesser than minimum temperature";

        return beerStyleDAO.updateBeerStyle(beerStyleToUpdate);
    }
}