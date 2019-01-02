package com.ciclic.duff.util;

import com.ciclic.duff.model.BeerStyle;

public class BeerStyleFunctions
{
    public static boolean compareStyles(BeerStyle beerStyleA, BeerStyle beerStyleB)
    {
        return (beerStyleA.getId() == beerStyleB.getId() 
                && beerStyleA.getStyle().equals(beerStyleB.getStyle())
                && beerStyleA.getMaximumTemperature() == beerStyleB.getMaximumTemperature()
                && beerStyleA.getMinimumTemperature() == beerStyleB.getMinimumTemperature());
    }

    public static boolean isNone(BeerStyle beerStyle)
    {
        return compareStyles(beerStyle, BeerStyle.getNone());
    }
}