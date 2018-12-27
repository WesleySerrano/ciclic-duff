package com.ciclic.duff.service;

import java.util.List;
import java.util.ArrayList;

import com.ciclic.duff.dto.BeerWithMusicDTO;
import com.ciclic.duff.dto.PlaylistDTO;
import com.ciclic.duff.dto.TemperatureDTO;
import com.ciclic.duff.dto.TrackDTO;

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
        TrackDTO track0 = new TrackDTO("Lua de Cristal", "Xuxa", "https: //open.spotify.com/artist/21451j1KhjAiaYKflxBjr1");
        TrackDTO track1 = new TrackDTO("Vogue", "Madonna", "https: //open.spotify.com/artist/21451j1Khj123YKflxBjr1");

        List<TrackDTO> tracksList = new ArrayList<TrackDTO>();
        tracksList.add(track0);
        tracksList.add(track1);

        PlaylistDTO playlist = new PlaylistDTO("IPARTY",tracksList);

        BeerWithMusicDTO beerWithMusic = new BeerWithMusicDTO("IPA",playlist);

        return beerWithMusic;
    }
}