package com.ciclic.duff.service;

import java.util.List;

import com.ciclic.duff.dto.BeerWithMusicDTO;
import com.ciclic.duff.dto.TemperatureDTO;
import com.ciclic.duff.dto.TrackDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@SpringBootTest
public class TemperatureBeerServiceTests
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

        Assert.assertEquals(beerAndPlaylist.getBeerStyle(), "IPA");
        Assert.assertEquals(beerAndPlaylist.getPlaylist().getName(), "IPARTY");
        Assert.assertTrue(playlistTracksContainsTrackName(beerAndPlaylist.getPlaylist().getTracks(), "Lua de Cristal"));
        Assert.assertTrue(playlistTracksContainsTrackName(beerAndPlaylist.getPlaylist().getTracks(), "Vogue"));
    }
}