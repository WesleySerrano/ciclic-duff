package com.ciclic.duff.dto;

public class TemperatureDTO
{
    private Double temperature;

    public TemperatureDTO(){}

    public TemperatureDTO(Double temperature)
    {
        this.temperature = temperature;
    }

    public Double getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(Double temperature)
    {
        this.temperature = temperature;
    }

    public void setTemperature(long temperature)
    {
        this.temperature = (double)temperature;
    }
}