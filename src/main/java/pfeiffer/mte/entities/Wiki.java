/*
 * © Copyright 2014, Philipp Pfeiffer
 * 
 * This file is part of MultimediaTourEditor.

    MultimediaTourEditor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MultimediaTourEditor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with MultimediaTourEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

package pfeiffer.mte.entities;

/**
 *  Represents a Wiki entity and encapsulates all Wiki related data.
 * @author Philipp Pfeiffer
 */
public class Wiki {
    
    int shortenLength = 300;
    
    private String area_EN;
    private String country_EN;
    private String population_EN;
    private String description_EN;
    private String fullDescription_EN;
    private String populationDate_EN;
    private String area_DE;
    private String country_DE;
    private String population_DE;
    private String description_DE;
    private String fullDescription_DE;
    private String populationDate_DE;
    
    /**
     * Sets the description as a String and shortens it down, if the shorten
     * parameter is true.
     * @param description
     * @param shorten 
     * @param locale
     */
    public void setDescription(String description, boolean shorten, String locale) {
        if(locale.equals("EN")) {
            this.fullDescription_EN = description;
            if(shorten) {
                description = shortenWikiText(description, shortenLength);
            }
            this.description_EN = description;
        } else if(locale.equals("DE")) {
            this.fullDescription_DE = description;
            if(shorten) {
                description = shortenWikiText(description, shortenLength);
            }
            this.description_DE = description;
        }
        
    }
    
    /**
     * Returns the description.
     * @param locale
     * @return String of the description
     */
    public String getDescription(String locale) {
        if(locale.equals("EN")) {
            return description_EN;
        } else if(locale.equals("DE")) {
            return description_DE;
        } else return "No Data Found.";
        
    }
    
    /**
     * Sets the population date.
     * @param populationDate 
     * @param locale
     */
    public void setPopulationDate(String populationDate, String locale) {
        if(locale.equals("EN")) {
            this.populationDate_EN = populationDate;
        } else if(locale.equals("DE")) {
            this.populationDate_DE = populationDate;
        }
        
    }
    
    /**
     * Returns the population date
     * @param locale
     * @return String of the population date
     */
    public String getPopulationDate(String locale) {
        if(locale.equals("EN")) {
            return populationDate_EN;
        } else if(locale.equals("DE")) {
            return populationDate_DE;
        } else return "No Data Found.";
    }
    
    /**
     * Sets the area.
     * @param area 
     * @param locale
     */
    public void setArea(String area, String locale) {
        if(locale.equals("EN")) {
            this.area_EN = area;
        } else if(locale.equals("DE")) {
            this.area_DE = area;
        }
    }
    
    /**
     * Returns the area.
     * @param locale
     * @return String of the area
     */
    public String getArea(String locale) {
        if(locale.equals("EN")) {
            return area_EN;
        } else if(locale.equals("DE")) {
            return area_DE;
        } else return "No Data Found.";
    }
    
    /**
     * Sets the country.
     * @param country
     * @param locale
     */
    public void setCountry(String country, String locale) {
        if(locale.equals("EN")) {
            this.country_EN = country;
        } else if(locale.equals("DE")) {
            this.country_DE = country;
        }
    }
    
    /**
     * Returns the country.
     * @param locale
     * @return String of the country
     */
    public String getCountry(String locale) {
        if(locale.equals("EN")) {
            return country_EN;
        } else if(locale.equals("DE")) {
            return country_DE;
        } else return "No Data Found";
    }
    
    /**
     * Sets the population
     * @param locale
     * @param population 
     */
    public void setPopulation(String population, String locale) {
        if(locale.equals("EN")) {
            this.populationDate_EN = population;
        } else if(locale.equals("DE")) {
            this.populationDate_DE = population;
        }
    }
    
    /**
     * Returns the population
     * @param locale
     * @return String of the population
     */
    public String getPopulation(String locale) {
        if(locale.equals("EN")) {
            return population_EN;
        } else if(locale.equals("DE")) {
            return population_DE;
        } else return "No Data Found";
    }
    
    /**
     * Shortens text to the length of the parameter length.
     * @param longString
     * @param length
     * @return String of the shortened text
     */
    public String shortenWikiText(String longString, int length) {
        String shortString = longString.substring(0, length);
        shortString += "...";
        return shortString;
    }
    
    /**
     * Returns the full, unshortened Description
     * @param locale
     * @return String of full Description
     */
    public String getFullDescription(String locale) {
        if(locale.equals("EN")) {
            return fullDescription_EN;
        } else if(locale.equals("DE")) {
            return fullDescription_DE;
        } else return "No Data Found";
    }
}