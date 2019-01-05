package com.ciclic.duff.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ciclic.duff.dto.PlaylistDTO;
import com.ciclic.duff.dto.TrackDTO;
import com.ciclic.duff.util.ApplicationProperties;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

public class SpotifyService
{
    private String clientId;
    private String clientSecret;
    private Date accessCodeCreationTime;
    private Integer accessTokenDurationTime;

    SpotifyApi spotifyApi;

    public SpotifyService()
    {
        final String CLIENT_ID_KEY = "spotify_client_id";
        final String CLIENT_SECRET_KEY = "spotify_client_secret";
        ApplicationProperties applicationProperties = new ApplicationProperties();

        this.clientId = applicationProperties.getApplicationPropertyByKey(CLIENT_ID_KEY);
        this.clientSecret = applicationProperties.getApplicationPropertyByKey(CLIENT_SECRET_KEY);

        spotifyApi = new SpotifyApi.Builder()
        .setClientId(this.clientId)
        .setClientSecret(this.clientSecret)
        .build();

        getAuthorizationTokens();
    }

    public PlaylistDTO getPlaylist(String playlistId)
    {
        if(this.checkWhetherANewAccessCodeIsNeeded()) this.getAuthorizationTokens();

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
        if(this.checkWhetherANewAccessCodeIsNeeded()) this.getAuthorizationTokens();

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

    public void getAuthorizationTokens()
    {
        ClientCredentialsRequest clientCredentialsRequest = this.spotifyApi.clientCredentials().build();
        try 
        {
            ClientCredentials clientCredentials =  clientCredentialsRequest.execute();
            this.spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            this.accessCodeCreationTime = new Date();
            this.accessTokenDurationTime = clientCredentials.getExpiresIn();
        } 
        catch (SpotifyWebApiException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private boolean checkWhetherANewAccessCodeIsNeeded()
    {
        return (new Date().getTime() - this.accessCodeCreationTime.getTime()) >= this.accessTokenDurationTime;
    }
}