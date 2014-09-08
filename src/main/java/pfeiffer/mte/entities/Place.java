package pfeiffer.mte.entities;

import java.util.ArrayList;

/**
 *  This class represents the Place entity and encapsulates data for each place
 * @author Philipp Pfeiffer
 */
public class Place {
    private String reference;
    private String name;
    private String icon;
    private String formattedAddress;
    private String formattedPhoneNumber;
    private String country;
    private double lat;
    private double lng;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    
    /**
     * Sets the list of photos belonging to this place as an ArrayList<Photo>
     * @param photos 
     */
    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
        
    /**
     * Returns the list of photos of this place
     * @return ArrayList<Photo> of photos belonging to this place
     */
    public ArrayList<Photo> getPhotos() {
        return photos;
    }
    
    /**
     * Adds a photo to the list of photos
     * @param photo 
     */
    public void addPhoto(Photo photo) {
        photos.add(photo);
    }
    
    /**
     * Returns the photo found at the given index of the list of photos
     * @param index
     * @return Photo found at the given index
     */
    public Photo getPhoto(int index) {
        return photos.get(index);
    }
    
    /**
     * Replaces the photo found at the given index with a new photo
     * @param index
     * @param photo 
     */
    public void setPhoto(int index, Photo photo) {
        photos.set(index, photo);
    }
    
    /**
     * Returns the amount of photos currently present in the list
     * @return int of the photo amount
     */
    public int getPhotoAmount() {
        return photos.size();
    }
    
    /**
     * Returns the latitude of this place
     * @return double value of the latitude
     */
    public double getLat() {
        return lat;
    }
    
    /**
     * Returns the longitude of this place
     * @return double value of the longitude
     */
    public double getLng() {
        return lng;
    }
    
    /**
     * Sets the latitude of this place
     * @param lat 
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    
    /**
     * Sets the longitude of this place
     * @param lng 
     */
    public void setLng(double lng) {
        this.lng = lng;
    }
    
    /**
     * Returns the reference of the place as a String
     * @return String of the place reference
     */
    public String getReference() {
        return reference;
    }
    
    /**
     * Sets the place reference
     * @param reference 
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    /**
     * Returns the name of the place as a String
     * @return String of the place name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the place
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the icon of the place as a String
     * @return String of the icon
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * Sets the icon of the place as a String
     * @param icon 
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * Returns the formatted address of the place as a String
     * @return String of the formatted address
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }
    
    /**
     * Sets the the address and brings it into the correct format. At the same
     * time it also extracts which country or state the place is in and sets
     * the country.
     * @param formattedAddress 
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        String[] tempSplitString = formattedAddress.split(",");
        setCountry(tempSplitString[tempSplitString.length-1]);
    }
    
    /**
     * Returns the formatted phone number as a String
     * @return String of the formatted phone number
     */
    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }
    
    /**
     * Sets tje formatted phone number
     * @param formattedPhoneNumber 
     */
    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }
    
    /**
     * Sets the country the place lies in
     * @param country 
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Returns the country the place lies in as a String
     * @return String of the country 
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Checks if photos for this place are available and returns true, if there
     * is at least one photo. Returns false if no photos are there.
     * @return boolean
     */
    public boolean photosAvailable() {
        if(photos.size()>0) {
            return true;
        } else return false;
    }
    
}
