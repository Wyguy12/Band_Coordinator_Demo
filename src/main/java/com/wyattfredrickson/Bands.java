package com.wyattfredrickson;


/**
 * The Bands class is used to store the band name and set time of each band
 * 
 */
public class Bands {
    // Properties of the Bands class
    String bandName;
    Float setTime;


    /**
     * Constructor for the Bands class
     * @param bandName the name of the band
     * @param setTime the set time of the band
     */
    public Bands(String bandName, Float setTime) {
        this.bandName = bandName;
        this.setTime = setTime;
    }


    /**
     * Get the band name
     * @return the name of the band
     */
    public String getBandName() {
        return bandName;
    }
    

    /**
     * Set the band name
     * @param bandName the name of the band
     */
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }


    /**
     * Get the set time of the band
     * @return the set time of the band
     */
    public Float getSetTime() {
        return setTime;
    }


    /**
     * Set the set time of the band
     * @param setTime the set time of the band
     */
    public void setSetTime(Float setTime) {
        this.setTime = setTime;
    }


    /**
     * Override the toString method to display the band name and set time
     * @return the band name and set time
     */
    @Override
    public String toString() {
        return "Band Name: " + bandName + " Set Time: " + setTime;
    }

}