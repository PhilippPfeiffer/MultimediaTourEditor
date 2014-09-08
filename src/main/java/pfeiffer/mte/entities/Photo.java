package pfeiffer.mte.entities;

/**
 *  This class represents an entity to save Photo informations for places. The
 * class encapsulates a photo's URL, reference, height, width and 
 * html_attributions
 * @author Philipp Pfeiffer
 */
public class Photo {
    
    private String URL = "";
    private String reference;
    private int height;
    private int width;
    private String html_attributions;
    
    /**
     * Sets the URL
     * @param URL 
     */
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    /**
     * Returns the URL
     * @return String of URL
     */
    public String getURL() {
        return URL;
    }
    
    /**
     * Sets the height of the image
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Returns the height of the image
     * @return int value of height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets the width of the image
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Returns the width of the image
     * @return int value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the reference to the image
     * @return String of the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the reference to the image
     * @param reference 
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /*
     * Returns the Html_attributions for the image
     * @return String of html_attributions
     */
    public String getHtml_attributions() {
        return html_attributions;
    }

    /**
     * Sets the Html_attributions for the image
     * @param html_attributions 
     */
    public void setHtml_attributions(String html_attributions) {
        this.html_attributions = html_attributions;
    }
    
    
    
}
