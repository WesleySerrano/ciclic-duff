package com.ciclic.duff.dto;

public class TrackDTO
{
    String name;
    String artist;
    String link;

    TrackDTO()
    {

    }

    TrackDTO(String name, String artist, String link)
    {
        this.name = name;
        this.artist = artist;
        this.link = link;
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public String getArtist() {return this.artist;}
    public void setArtist(String artist) {this.artist = artist;}

    public String getLink() {return this.link;}
    public void setLink(String link) {this.link = link;}
}