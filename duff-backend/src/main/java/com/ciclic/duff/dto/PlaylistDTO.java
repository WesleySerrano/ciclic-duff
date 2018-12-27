package com.ciclic.duff.dto;

import java.util.List;

public class PlaylistDTO
{
    String name;
    List<TrackDTO> tracks;

    public PlaylistDTO(){}
    public PlaylistDTO(String name, List<TrackDTO> tracks)
    {
        this.name = name;
        this.tracks = tracks;
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public List<TrackDTO> getTracks() {return this.tracks;}
    public void setTracks(List<TrackDTO> tracks) {this.tracks = tracks;}
}