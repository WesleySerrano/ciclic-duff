package com.ciclic.duff.dto;

public class BeerWithMusicDTO
{
    String beerStyle;
    PlaylistDTO playlist;


    public BeerWithMusicDTO(){}

    public BeerWithMusicDTO(String beerStyle, PlaylistDTO playlist)
    {
        this.beerStyle = beerStyle;
        this.playlist = playlist;
    }

    public String getBeerStyle() {return this.beerStyle;}
    public void setBeerStyle(String beerStyle) {this.beerStyle = beerStyle;}

    public PlaylistDTO getPlaylist() {return this.playlist;}
    public void setPlaylist(PlaylistDTO playlist) {this.playlist = playlist;}
}