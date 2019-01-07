package com.ciclic.duff.model;

public class BeerStyle
{
    private long id;
    private String style;
    private double maximumTemperature;
    private double minimumTemperature;

    public BeerStyle()
    {

    }

    public BeerStyle(long id, String style, double maximumTemperature, double minimumTemperature)
    {
        this.id = id;
        this.style = style;
        this.maximumTemperature = maximumTemperature; this.minimumTemperature = minimumTemperature;
    }

    public static BeerStyle getNone()
    {
        return new BeerStyle(0,"",0,0);
    }

    public static boolean isNone(BeerStyle beerStyle)
    {
        BeerStyle none = getNone();
        return (beerStyle.getId() == none.getId() && beerStyle.getStyle().equals(none.getStyle()) && beerStyle.getMaximumTemperature() == none.getMaximumTemperature() && beerStyle.getMinimumTemperature() == none.getMinimumTemperature());
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
}
