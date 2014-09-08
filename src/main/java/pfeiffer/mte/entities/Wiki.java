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
    private String populationDate;
    
    /**
     * Sets the description as a String and shortens it down, if the shorten
     * parameter is true.
     * @param description
     * @param shorten 
     */
    public void setDescription(String description, boolean shorten) {
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
}
