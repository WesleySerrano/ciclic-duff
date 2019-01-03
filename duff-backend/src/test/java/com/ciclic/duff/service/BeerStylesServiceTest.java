package com.ciclic.duff.service;

import java.util.List;

import com.ciclic.duff.util.StringFunctions;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.Assert;

public class BeerStylesServiceTest
{
    @Test
    public void getAllBeerStylesNames()
    {
        List<String> beerStylesNames = BeerStylesService.getAllBeerStylesNames();

        Assert.assertEquals(beerStylesNames.size(), 9);
        Assert.assertTrue(StringFunctions.listContainsString(beerStylesNames, "Weizenbier"));
        Assert.assertTrue(StringFunctions.listContainsString(beerStylesNames, "Pilsens"));
        Assert.assertTrue(StringFunctions.listContainsString(beerStylesNames, "Dunkel"));
    }
}