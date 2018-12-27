package com.ciclic.duff.controller.beer;

import com.ciclic.duff.dto.TemperatureDTO;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController
{
    /**
     * URL for testing the application
     */
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String helloBeer()
    {
        return "Hello! Let's have some beer?";
    }

    /**
     * Gets a temperature value and returns a beer style with a playlist matching it
     * 
     * @param temperature the temperature value to look for a matching beer style
     */
    @RequestMapping(value="/beerAndMusic",method=RequestMethod.POST)
    public String getBeerAndMatchingPlaylist(@RequestBody TemperatureDTO temperature)
    {
        return ""+temperature.getTemperature();
    }
}