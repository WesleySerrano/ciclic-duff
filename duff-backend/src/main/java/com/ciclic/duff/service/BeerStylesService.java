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
        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();

        if(!StringFunctions.validateString(newBeerStyle.getStyle())) return "Style name not valid";

        return beerStyleDAO.addNewStyle(newBeerStyle);
    }
}