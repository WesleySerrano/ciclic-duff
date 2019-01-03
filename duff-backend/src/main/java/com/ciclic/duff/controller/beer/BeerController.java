package com.ciclic.duff.controller.beer;

import java.util.List;

import com.ciclic.duff.dto.BeerStyleDTO;
import com.ciclic.duff.dto.BeerWithMusicDTO;
import com.ciclic.duff.dto.TemperatureDTO;
import com.ciclic.duff.service.BeerStylesService;
import com.ciclic.duff.service.TemperatureBeerService;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public BeerWithMusicDTO getBeerAndMatchingPlaylist(@RequestBody TemperatureDTO temperature)
    {
        BeerWithMusicDTO beerWithMusicDTO = TemperatureBeerService.getBeerStyleMatchingTemperature(temperature);
        return beerWithMusicDTO;
    }

    /**
     * Gets all the beer styles with its playlists
     * @return a list containing all beer styles, its temperatures and its playlists
     */
    @RequestMapping(value="/beer",method=RequestMethod.GET)
    @ResponseBody
    public List<BeerStyleDTO> getAllBeerStylesAndItsPlaylists()
    {
        return BeerStylesService.getAllBeerStyles();
    }

    @RequestMapping(value="/beer",method=RequestMethod.PUT)
    public String addNewBeerStyle(@RequestBody BeerStyleDTO newBeerStyle)
    {
        return BeerStylesService.addNewBeerStyle(newBeerStyle);
    }
}