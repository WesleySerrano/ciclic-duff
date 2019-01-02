package com.ciclic.duff.dao;

import com.ciclic.duff.model.BeerStyle;
import com.ciclic.duff.util.BeerStyleFunctions;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Assert;


@SpringBootTest
public class BeerStyleTest 
{
	BeerStyleService beerStyleService = new BeerStyleService();
	final double TEMPERATURE_TOLERANCE = 0.01;

	@Test
	public void grabTheFirstBeer() 
	{		
		BeerStyle beerStyle = beerStyleService.searchBeerStyleById(1);

		Assert.assertEquals(beerStyle.getId(),1);
		Assert.assertEquals(beerStyle.getStyle(), "Weissbier");
		Assert.assertEquals(beerStyle.getMaximumTemperature(), 3, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle.getMinimumTemperature(), -1, TEMPERATURE_TOLERANCE);
	}

	@Test
	public void grabFittestBeer()
	{
		final double MAX_TEMP = 14;
		final double MIN_TEMP = -10;

		BeerStyle beerStyle0 = beerStyleService.getBeerThatFitsTemperature(-7);
		Assert.assertEquals(beerStyle0.getStyle(), "Dunkel");
		Assert.assertEquals(beerStyle0.getMaximumTemperature(), 2, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle0.getMinimumTemperature(), -8, TEMPERATURE_TOLERANCE);

		BeerStyle beerStyle1 = beerStyleService.getBeerThatFitsTemperature(1);
		Assert.assertEquals(beerStyle1.getStyle(), "Pilsens");
		Assert.assertEquals(beerStyle1.getMaximumTemperature(), 4, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle1.getMinimumTemperature(), -2, TEMPERATURE_TOLERANCE);

		BeerStyle maxNull = beerStyleService.getBeerThatFitsTemperature(MAX_TEMP + 1);
		Assert.assertTrue(BeerStyleFunctions.isNone(maxNull));

		BeerStyle minNull = beerStyleService.getBeerThatFitsTemperature(MIN_TEMP - 1);
		Assert.assertTrue(BeerStyleFunctions.isNone(minNull));
	}
}

