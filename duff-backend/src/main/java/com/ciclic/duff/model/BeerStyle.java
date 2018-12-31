package com.ciclic.duff.model;

public class BeerStyle
{
    private long id;
    private String style;
    private double maximumTemperature;
    private double minimumTemperature;
    private String playlistUrl;

    public BeerStyle()
    {

    }

    public BeerStyle(long id, String style, double maximumTemperature, double minimumTemperature, String playlistUrl)
    {
        this.id = id;
        this.style = style;
        this.maximumTemperature = maximumTemperature; this.minimumTemperature = minimumTemperature;
        this.playlistUrl = playlistUrl;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the maximumTemperature
     */
    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    /**
     * @param maximumTemperature the maximumTemperature to set
     */
    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    /**
     * @return the minimumTemperature
     */
    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    /**
     * @param minimumTemperature the minimumTemperature to set
     */
    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    /**
     * @return the playlistUrl
     */
    public String getPlaylistUrl() {
        return playlistUrl;
    }

    /**
     * @param playlistUrl the playlistUrl to set
     */
    public void setPlaylistUrl(String playlistUrl) {
        this.playlistUrl = playlistUrl;
    }

}