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

    public void setTemperature(double temperature)
    {
        this.temperature = temperature;
    }
}