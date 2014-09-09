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
