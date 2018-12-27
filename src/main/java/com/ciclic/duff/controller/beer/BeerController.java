package com.ciclic.duff.controller.beer;

import com.ciclic.duff.dto.TemperatureDTO;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController
{
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String helloBeer()
    {
        return "Hello! Let's have some beer?";
    }

    @RequestMapping(value="/beerAndMusic",method=RequestMethod.POST)
    public String getBeerAndMatchingPlaylist(@RequestBody TemperatureDTO temperature)
    {
        return ""+temperature.getTemperature();
    }
}