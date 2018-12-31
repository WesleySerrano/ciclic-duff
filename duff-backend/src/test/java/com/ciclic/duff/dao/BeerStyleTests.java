package com.ciclic.duff.dao;

import com.ciclic.duff.model.BeerStyle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;


@SpringBootTest
public class BeerStyleTests 
{
	BeerStyleService beerStyleService;

	@Test
	public void grabTheFirstBeer() 
	{		
		BeerStyle beerStyle = beerStyleService.searchBeerStyleById(1);
		final double TEMPERATURE_TOLERANCE = 0.01;

		Assert.assertEquals(beerStyle.getId(),1);
		Assert.assertEquals(beerStyle.getStyle(), "Weissbier");
		Assert.assertEquals(beerStyle.getMaximumTemperature(), 3, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle.getMinimumTemperature(), -1, TEMPERATURE_TOLERANCE);
	}
}

