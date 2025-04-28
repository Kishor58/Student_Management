package com.wcs.demo.Exception;

public class CollegeNotFoundException extends RuntimeException{
    public CollegeNotFoundException(String massage){
        super(massage);
    }
}
