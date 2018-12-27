package com.ciclic.duff.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;


@SpringBootTest
public class TemperatureDTOTests 
{

	@Test
	public void createTemperature() 
	{
		TemperatureDTO temperature0 = new TemperatureDTO(2.0);
		TemperatureDTO temperature1 = new TemperatureDTO(-8.0);

		temperature0.setTemperature(4);

		Assert.assertEquals(temperature0.getTemperature(), 4.0);
		Assert.assertEquals(temperature1.getTemperature(), -8.0);
	}

}

