package com.ciclic.duff.dto;

public class BeerStyleDTO
{
    private String style;
    private double maximumTemperature;
    private double minimumTemperature;

    public BeerStyleDTO()
    {

    }

    public BeerStyleDTO(String style, double maximumTemperature, double minimumTemperature)
    {
        this.style = style;
        this.maximumTemperature = maximumTemperature;
        this.minimumTemperature = minimumTemperature;
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
}