package com.ciclic.duff.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.ciclic.duff.model.BeerStyle;
import com.ciclic.duff.util.BeerStyleFunctions;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Assert;


@SpringBootTest
public class BeerStyleTest 
{
	BeerStyleDAO beerStyleDAO = new BeerStyleDAO();
	final double TEMPERATURE_TOLERANCE = 0.01;

	@Test
	public void grabTheFirstBeer() 
	{		
		BeerStyle beerStyle = beerStyleDAO.searchBeerStyleById(1);

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

		BeerStyle beerStyle0 = beerStyleDAO.getBeerThatFitsTemperature(-7);
		Assert.assertEquals(beerStyle0.getStyle(), "Dunkel");
		Assert.assertEquals(beerStyle0.getMaximumTemperature(), 2, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle0.getMinimumTemperature(), -8, TEMPERATURE_TOLERANCE);

		BeerStyle beerStyle1 = beerStyleDAO.getBeerThatFitsTemperature(1);
		Assert.assertEquals(beerStyle1.getStyle(), "Pilsens");
		Assert.assertEquals(beerStyle1.getMaximumTemperature(), 4, TEMPERATURE_TOLERANCE);
		Assert.assertEquals(beerStyle1.getMinimumTemperature(), -2, TEMPERATURE_TOLERANCE);

		BeerStyle maxNull = beerStyleDAO.getBeerThatFitsTemperature(MAX_TEMP + 1);
		Assert.assertTrue(BeerStyleFunctions.isNone(maxNull));

		BeerStyle minNull = beerStyleDAO.getBeerThatFitsTemperature(MIN_TEMP - 1);
		Assert.assertTrue(BeerStyleFunctions.isNone(minNull));
	}

	@Test
	public void grabAllBeers()
	{
		List<BeerStyle> beerStyles = beerStyleDAO.getAllBeerStyles();

		assertTrue(beerStyles.size() >= 9);
	}

	@Test
	public void getBeerStylesByName()
	{
		BeerStyleDAO beerStyleDAO = new BeerStyleDAO();

		final String EXISTENT_BEER_STYLE = "Pilsens";
		final String NON_EXISTENT_BEER_STYLE = "Beer Style that doesnt exists";

		BeerStyle existent = beerStyleDAO.getBeerStyleByName(EXISTENT_BEER_STYLE);
		BeerStyle nonExistent = beerStyleDAO.getBeerStyleByName(NON_EXISTENT_BEER_STYLE);

		Assert.assertFalse(BeerStyle.isNone(existent));
		Assert.assertTrue(BeerStyle.isNone(nonExistent));
	}
}

