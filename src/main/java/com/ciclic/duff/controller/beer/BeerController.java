package com.ciclic.duff.controller.beer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeerController
{
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String helloBeer()
    {
        return "Hello! Let's have some beer?";
    }
}