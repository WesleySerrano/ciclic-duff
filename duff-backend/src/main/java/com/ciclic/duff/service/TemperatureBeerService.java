package com.ciclic.duff.service;

import java.util.List;
import java.util.ArrayList;

import com.ciclic.duff.dao.BeerStyleDAO;
import com.ciclic.duff.dto.BeerWithMusicDTO;
import com.ciclic.duff.dto.PlaylistDTO;
import com.ciclic.duff.dto.TemperatureDTO;
import com.ciclic.duff.dto.TrackDTO;
import com.ciclic.duff.model.BeerStyle;
import com.wrapper.spotify.SpotifyApi;

public class TemperatureBeerService
{
    /**
     * Gets the beer style and a playlist matching it base on a temperature value
     * 
     * @param temperature a DTO containing a temperature value to find a beer style matching it
     * @return a beer style and a matching playlist according to the given temperature value
     */
    public static BeerWithMusicDTO getBeerStyleMatchingTemperature(TemperatureDTO temperature)
    {
        BeerStyleDAO beerStyleDAO = new BeerStyleDAO();
        
        BeerStyle beerStyle = beerStyleDAO.getBeerThatFitsTemperature(temperature.getTemperature());

        SpotifyService spotifyService = new SpotifyService();        

        PlaylistDTO playlist = spotifyService.getPlaylistByName(beerStyle.getStyle());

        BeerWithMusicDTO beerWithMusic = playlist == null? null : new BeerWithMusicDTO(beerStyle.getStyle(),playlist);

        return beerWithMusic;
    }
}