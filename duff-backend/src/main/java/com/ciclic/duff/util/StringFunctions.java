package com.ciclic.duff.util;

import java.util.List;

public class StringFunctions
{
    public static boolean isStringLesserThanOther(String lesser, String greater)
    {
        return lesser.compareTo(greater) < 0;
    }

    public static boolean listContainsString(List<String> listOfStrings, String target)
    {
        for(String string : listOfStrings)
        {
            if(string.trim().contains(target)) return true;
        }

        return false;
    }
}