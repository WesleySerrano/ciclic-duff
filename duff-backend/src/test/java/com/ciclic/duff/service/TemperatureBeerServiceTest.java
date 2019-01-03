package com.ciclic.duff.service;

import java.util.List;

import com.ciclic.duff.dto.BeerWithMusicDTO;
import com.ciclic.duff.dto.TemperatureDTO;
import com.ciclic.duff.dto.TrackDTO;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.Assert;

@SpringBootTest
public class TemperatureBeerServiceTest
{
    private boolean playlistTracksContainsTrackName(List<TrackDTO> tracks, String name)
    {
        return tracks.stream().filter(track -> track.getName().equals(name)).findFirst().isPresent();
    }

    @Test
    public void getBeerAndPlaylistFromTemperature()
    {
        TemperatureDTO negativeSevenDegrees = new TemperatureDTO((double) -7);

        BeerWithMusicDTO beerAndPlaylist = TemperatureBeerService.getBeerStyleMatchingTemperature(negativeSevenDegrees);

        Assert.assertEquals(beerAndPlaylist.getBeerStyle(), "Dunkel");
        Assert.assertEquals(beerAndPlaylist.getPlaylist().getName(), "Ultimate pub playlist");
        Assert.assertTrue(playlistTracksContainsTrackName(beerAndPlaylist.getPlaylist().getTracks(), "Lego House"));
        Assert.assertTrue(playlistTracksContainsTrackName(beerAndPlaylist.getPlaylist().getTracks(), "The Payback"));
    }
}