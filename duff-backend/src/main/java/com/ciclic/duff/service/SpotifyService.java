package com.ciclic.duff.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ciclic.duff.dto.PlaylistDTO;
import com.ciclic.duff.dto.TrackDTO;
import com.ciclic.duff.util.ApplicationProperties;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

public class SpotifyService
{
    private String accessToken;
    private String clientId;
    private String refreshToken;

    SpotifyApi spotifyApi;

    public SpotifyService()
    {
        final String ACCESS_TOKEN_KEY = "spotify_access_token";
        final String REFRESH_TOKEN_KEY = "spotify_access_token";
        final String CLIENT_ID_KEY = "spotify_client_id";
        ApplicationProperties applicationProperties = new ApplicationProperties();

        this.accessToken = applicationProperties.getApplicationPropertyByKey(ACCESS_TOKEN_KEY);
        this.clientId = applicationProperties.getApplicationPropertyByKey(CLIENT_ID_KEY);
        this.refreshToken = applicationProperties.getApplicationPropertyByKey(REFRESH_TOKEN_KEY);

        spotifyApi = new SpotifyApi.Builder()
        .setAccessToken(accessToken)
        .setRefreshToken(refreshToken)
        .build();
    }

    public PlaylistDTO getPlaylist(String playlistId)
    {
        GetPlaylistRequest playlistRequest = spotifyApi.getPlaylist(playlistId).build();

        try 
        {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            List<TrackDTO> tracksDTOs = new ArrayList<TrackDTO>();

            final Playlist playlist = playlistRequest.execute();
            playlistDTO.setName(playlist.getName());            
            Paging<PlaylistTrack> playlistTracks = playlist.getTracks();
            
            for(PlaylistTrack playlistTrack : playlistTracks.getItems())
            {
                Track track = playlistTrack.getTrack();
                TrackDTO trackDTO = new TrackDTO(track.getName(), track.getArtists()[0].getName(), track.getHref());
                tracksDTOs.add(trackDTO);
            }

            playlistDTO.setTracks(tracksDTOs);

            return playlistDTO;
        } 
        catch (IOException | SpotifyWebApiException e) 
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public PlaylistDTO getPlaylistByName(String playlistKeyword)
    {
        SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(playlistKeyword)
        .market(CountryCode.SE)
        .limit(10)
        .offset(0).build();

        try 
        {
            final Paging<PlaylistSimplified> playlistSimplifiedPaging = searchPlaylistsRequest.execute();
            if(playlistSimplifiedPaging.getTotal() == 0) return null;
            String playlistId = playlistSimplifiedPaging.getItems()[0].getId();
            return this.getPlaylist(playlistId);
        } 
        catch (IOException | SpotifyWebApiException e) 
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}