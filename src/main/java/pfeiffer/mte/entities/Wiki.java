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
    
    private String area;
    private String country;
    private String population;
    private String description;
    private String fullDescription;
    private String populationDate;
    
    /**
     * Sets the description as a String and shortens it down, if the shorten
     * parameter is true.
     * @param description
     * @param shorten 
     */
    public void setDescription(String description, boolean shorten) {
        this.fullDescription = description;
        if(shorten) {
            description = shortenWikiText(description,300);
        }
        this.description = description;
    }
    
    /**
     * Returns the description.
     * @return String of the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the population date.
     * @param populationDate 
     */
    public void setPopulationDate(String populationDate) {
        this.populationDate = populationDate;
    }
    
    /**
     * Returns the population date
     * @return String of the population date
     */
    public String getPopulationDate() {
        return populationDate;
    }
    
    /**
     * Sets the area.
     * @param area 
     */
    public void setArea(String area) {
        this.area = area;
    }
    
    /**
     * Returns the area.
     * @return String of the area
     */
    public String getArea() {
        return area;
    }
    
    /**
     * Sets the country.
     * @param country 
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Returns the country.
     * @return String of the country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Sets the population
     * @param population 
     */
    public void setPopulation(String population) {
        this.population = population;
    }
    
    /**
     * Returns the population
     * @return String of the population
     */
    public String getPopulation() {
        return population;
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
     * @return String of full Description
     */
    public String getFullDescription() {
        return fullDescription;
    }
}