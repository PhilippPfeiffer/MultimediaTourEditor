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

package pfeiffer.mte.api;

import java.io.UnsupportedEncodingException;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pfeiffer.mte.entities.Place;
import pfeiffer.mte.entities.PlacesList;
import pfeiffer.mte.entities.VideoList;
import pfeiffer.mte.entities.Wiki;

/**
 *
 * @author Philipp Pfeiffer
 * 
 * SearchBean.java is the main class, used for by the browser to access data
 * on the server and to interact with the web services. SearchBean.java handles
 * all communication between the server and browser and is a session scoped bean.
 */
@ManagedBean(name = "searchBean", eager = true)
@SessionScoped
public class SearchBean {
    
    private String searchTerm;
    private PlacesList placesList = new PlacesList();
    private ArrayList<Place> onRouteList = new ArrayList<Place>();
    private ArrayList<Place> offRouteList = new ArrayList<Place>();
    
    private ArrayList<Place> editorOnRouteList = new ArrayList<Place>();
    private ArrayList<Place> editorOffRouteList = new ArrayList<Place>();
    
    private VideoList videoList;
    
    private Wiki wiki;
            
    private GooglePlacesClient googlePlacesClient;
    private YoutubeSearchClient youtubeSearchClient;
    private FreebaseSearchClient freebaseSearchClient;
    
    private int routeSize = 10;
    
    private String travelMode = "WALKING";
    
    private String locale = "EN";
    
    private Messages messages = new Messages();

    /**
     * Creates a new instance of searchBean
     */
    public SearchBean() {
    }
    
    /**
     * Returns the current locale as a String
     * @return the locale currently in use
     */
    public String getLocale() {
        return this.locale;
    }
    
    /**
     * Sets the locale to newLocale. Currently supported locales are English (EN)
     * and German (DE)
     * @param newLocale 
     */
    private void setLocale(String newLocale) {
        this.locale = newLocale;
        messages.setLocale(newLocale);
    }
    
    /**
     * sets the current locale to German
     */
    public void setGerman() {
        setLocale("DE");
    }
    
    /**
     * sets the current locale to English
     */
    public void setEnglish() {
        setLocale("EN");
    }
    
    /**
     * Replaces searchTerm with a new searchTerm
     * @param searchTerm 
     */
    public void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }
    
    /**
     * Returns the current searchTerm
     * @return the current searchTerm as String
     */
    public String getSearchTerm() {
        return searchTerm;
    }
    
    /**
     * Replaces placesList with a new placesList
     * @param placesList 
     */
    public void setPlacesList(PlacesList placesList) {
        this.placesList = placesList;
    }
    
    /**
     * Returns placesList
     * @return placesList
     */
    public PlacesList getPlacesList() {
        return placesList;
    }
    
    /**
     * Replaces onRouteList with a new onRouteList
     * @param onRouteList 
     */
    public void setOnRouteList(ArrayList<Place> onRouteList) {
        this.onRouteList = onRouteList;
    }
    
    /**
     * Returns onRouteList
     * @return onRouteList
     */
    public ArrayList<Place> getOnRouteList() {
        return onRouteList;
    }
    
    /**
     * Replaces offRouteList with a new offRouteList
     * @param offRouteList 
     */
    public void setOffRouteList(ArrayList<Place> offRouteList) {
        this.offRouteList = offRouteList;
    }
    
    /**
     * Returns the Address of the place at the given index on the route
     * @param index
     * @return String of the place's address
     */
    public String getPlaceAddress(int index) {
        if(onRouteList.size() <= index) {
            return "";
        }
        return onRouteList.get(index).getFormattedAddress();
    }
    
    /**
     * Returns the name of the place at the given index on the route
     * @param index
     * @return String of the place's name
     */
    public String getPlaceName(int index) {
        if(index >= onRouteList.size()) {
            return "";
        }
        return onRouteList.get(index).getName();
    }
    
    /**
     * Returns the name of a place that is off the route at the given index
     * @param index
     * @return String of the place's name
     */
    public String getOffRoutePlaceName(int index) {
        if(index >= offRouteList.size()) {
            return "";
        }
        return offRouteList.get(index).getName();
    }
    
    /**
     * Returns the latitude of the place at the given index on the route
     * @param index
     * @return Double value of the latitude
     */
    public Double getPlaceLat(int index) {
        if(index >= onRouteList.size()) {
            return 0.0;
        } else return onRouteList.get(index).getLat();
    }
    
    /**
     * Returns the longitude of the place at the given index on the route
     * @param index
     * @return Double value of the longitude
     */
    public Double getPlaceLng(int index) {
        if(index >= onRouteList.size()) {
            return 0.0;
        } else return onRouteList.get(index).getLng();
    }
    
    /**
     * Returns the size of the route
     * @return int value of route size
     */
    public int getRouteSize() {
        return onRouteList.size();
    }
    
    /**
     * Sets the route size to routeSize
     * @param routeSize 
     */
    public void setRouteSize(int routeSize) {
        this.routeSize = routeSize;
    }
    
    /**
     * Increments the route size by 1
     */
    public void incrementRouteSize() {
        this.routeSize++;
    }
    
    /**
     * decrements the route size by 1
     */
    public void decrementRouteSize() {
        this.routeSize--;
    }
    
    /**
     * returns a list of videos
     * @return videoList
     */
    public VideoList getvideoList() {
        return videoList;
    }
    
    /**
     * Replaces videoList with a new videoList
     * @param videoList 
     */
    public void setVideoList(VideoList videoList) {
        this.videoList = videoList;
    }
    
    /**
     * Returns the ID of the first video found
     * @return String of the first video ID
     */
    public String getMainVideoId() {
        return videoList.getVideo(0).getId();
    }
    
    /**
     * Returns the country
     * @return String of the country
     */
    public String getCountry() {
        return wiki.getCountry();
    }
    
    /**
     * Returns the area
     * @return String of the area
     */
    public String getArea() {
        return wiki.getArea();
    }
    
    /**
     * Returns the population
     * @return String of the population
     */
    public String getPopulation() {
        return wiki.getPopulation();
    }
    
    /**
     * Returns the description
     * @return String of the description
     */
    public String getDescription() {
        return wiki.getDescription();
    }
    
    /**
     * Returns the population date
     * @return String of the population date
     */
    public String getPopulationDate() {
        return wiki.getPopulationDate();
    }
    
    /**
     * Returns the currently set travel mode
     * @return String of the travel mode
     */
    public String getTravelMode() {
        return travelMode;
    }
    
    /**
     * Sets the current travel mode to WALKING
     */
    public void setWalking() {
        this.travelMode = "WALKING";
    }
    
    /**
     * Sets the current travel mode to BICYCLING
     */
    public void setBicycle() {
        this.travelMode = "BICYCLING";
    }
    
    /**
     * Sets the current travel mode to DRIVING
     */
    public void setDriving() {
        this.travelMode = "DRIVING";
    }
    
    /**
     * Returns the String for the walking button. If the travel mode is set to
     * WALKING, it returns the graphic for the active walking mode. Otherwise it
     * returns the graphic for the inactive walking mode.
     * @return String containing the graphic address
     */
    public String getWalkingButton() {
        if(travelMode.equals("WALKING")) {
            return "img:walkingActive.png";
        } else return "img:walkingInactive.png";
    }
    
    /**
     * Returns the String for the bycicling button. If the travel mode is set to
     * BICYCLING, it returns the graphic for the active bycicling mode. Otherwise it
     * returns the graphic for the inactive bycicling mode.
     * @return String containing the graphic address
     */
    public String getBicycleButton() {
        if(travelMode.equals("BICYCLING")) {
            return "img:bicycleActive.png";
        } else return "img:bicycleInactive.png";
    }
    
    /**
     * Returns the String for the driving button. If the travel mode is set to
     * DRIVING, it returns the graphic for the active driving mode. Otherwise it
     * returns the graphic for the inactive driving mode.
     * @return String containing the graphic address
     */
    public String getCarButton() {
        if(travelMode.equals("DRIVING")) {
            return "img:drivingActive.png";
        } else return "img:drivingInactive.png";
    }
    
    /**
     * Conducts the main search and returns the page that should be displayed
     * @return the name of the xhtml file to be displayed
     */
    public String search() {
        
        /* 
         * Before every new search, all entities that carry data from the last
         * search have to be reset and are newly initialized.
         */
        placesList = new PlacesList();
        onRouteList = new ArrayList<Place>();
        offRouteList = new ArrayList<Place>();
        editorOnRouteList = new ArrayList<Place>();
        editorOffRouteList = new ArrayList<Place>();
        wiki = new Wiki();
        videoList = new VideoList();
        googlePlacesClient = new GooglePlacesClient();
        youtubeSearchClient = new YoutubeSearchClient();
        freebaseSearchClient = new FreebaseSearchClient();
        
        
        //The search term is validated and checked for invalid characters
        cleanSearchTerm();
        
        //The actual search for relevant places
        placesList.setPlacesList(googlePlacesClient.textSearch(searchTerm + " Attraction", 10000));
        
        /*
         * If no places were found, the user is directed to the start page and
         * an a message is displayed.
        */
        if(placesList.getSize() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(messages.getString("NO_PLACES_FOUND")));
            return "index";
        }
        
        //Photos are added to the places
        addPhotos();
             
        //Separate lists for onRoute and offRoute purposes are built
        onRouteList = placesList.buildOnRouteList(10);
        offRouteList = placesList.buildOffRouteList(10);
        fillEditorLists();
        
        //Relevant videos are searched for
        videoList.setList(youtubeSearchClient.search(searchTerm));
       
        //Relevant wiki information is searched for
        wiki = freebaseSearchClient.search(searchTerm, wiki);
        
        //The user is directed to the editor view
        return "editor";
    }
    
    /**
     * Adds photos to all places by calling the getPhotoReferences(PLace currentplace)
     * method on googlePlacesClient for each place.
     */
    private void addPhotos() {
        for (int i = 0; i < placesList.getSize(); i++) {
            Place currentPlace = placesList.getPlace(i);
            currentPlace = googlePlacesClient.getPhotoReferences(currentPlace);
            placesList.setPlace(i, currentPlace);
        }
    }
    
    /**
     * Adds the place found at the index indicated by the given parameter to the
     * route. The place is only added, if there are less than 10 places already
     * on the route, since 10 is the maximum route size.
     * @param index 
     */
    public void addToRoute(int index) {
        if(routeSize >= 10) {
            Place place = offRouteList.get(index);
            offRouteList.remove(index);
            onRouteList.add(place);
            this.routeSize++;
        }
    }
    
    /**
     * Adds the place found at the index indicated by the given parameter to the
     * route. The place is only added, if there are less than 10 places already
     * on the route, since 10 is the maximum route size.
     * @param index 
     */
    public void addToEditorRoute(int index) {
        if(editorOnRouteList.size() < 10) {
            editorOnRouteList.add(editorOffRouteList.get(index));
            editorOffRouteList.remove(index);
        }
        refreshOnRouteList();
        refreshOffRouteList();
    }
    
    /**
     * Removes the place found at the index given through the parameter from
     * the route. It only removes the place from the route, if the route
     * currently consists of more than two places.
     * @param index 
     */
    public void removeFromEditorRoute(int index) {
        if(editorOnRouteList.size() > 2) {
            editorOffRouteList.add(editorOnRouteList.get(index));
            editorOnRouteList.remove(index);
        }
        refreshOnRouteList();
        refreshOffRouteList();
    }
    
    /**
     * Removes the place found at the index given through the parameter from
     * the route. It only removes the place from the route, if the route
     * currently consists of more than two places.
     * @param index 
     */
    public void removeFromRoute(int index) {
        if(onRouteList.size() > 2) {
            Place place = onRouteList.get(index);
            onRouteList.remove(index);
            offRouteList.add(place);
            this.routeSize--;
        }
    }
    
    /**
     * Returns a boolean indicating whether or not a place should be rendered
     * in the editor.
     * 
     * @param placeId
     * @return boolean
     */
    public boolean isPlaceVisible(String placeId) {
        if(placeId.contains("onRoute")) {
            int number = Integer.parseInt(placeId.replace("onRoute", ""));
            if(number < editorOnRouteList.size()) {
                return true;
            } else {
                return false;
            }
        }
        else if(placeId.contains("offRoute")) {
            int number = Integer.parseInt(placeId.replace("offRoute", ""));
            if(number < editorOffRouteList.size()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns the URL of the primary image of a place. The parameter i is the
     * index of the place within onRouteList.
     * 
     * @param i index of the place within onRouteList
     * @return URL of the image as a String
     */
    public String getImageURL(int i) {
        String URL = "";
        if(i >= onRouteList.size()) {
            return "";
        }
        if(onRouteList.get(i).photosAvailable()) {
            URL = onRouteList.get(i).getPhoto(0).getURL();
        }
        return URL;
    }
    
    /**
     * Returns the URL of the secondary image of a place. The parameter i is the
     * index of the place within onRouteList.
     * 
     * @param i index of the place within onRouteList
     * @return URL of the image as a String
     */
    public String getSecondImageURL(int i) {
        String URL = "";
        if(i >= onRouteList.size()) {
            return "";
        }
        if(onRouteList.get(i).photosAvailable()) {
            if(onRouteList.get(i).getPhotoAmount() > 1) {
                URL = onRouteList.get(i).getPhoto(1).getURL();
            }
        }
        return URL;
    }
    
    /**
     * Returns the URL of the tertiary image of a place. The parameter i is the
     * index of the place within onRouteList.
     * 
     * @param i index of the place within onRouteList
     * @return URL of the image as a String
     */
    public String getThirdImageURL(int i) {
        String URL = "";
        if(i >= onRouteList.size()) {
            return "";
        }
        if(onRouteList.get(i).photosAvailable()) {
            if(onRouteList.get(i).getPhotoAmount() > 2) {
                URL = onRouteList.get(i).getPhoto(2).getURL();
            }
        }
        return URL;
    }
    
    /**
     * Returns a boolean to indicate, if an image should be rendered or not. The
     * parameter i indicates the index of the place within onRouteList for which
     * the availability of an image should be checked. The method returns true,
     * if the specified place has an image and false if none was found. Only
     * checks if the primary image of the place is available
     * 
     * @param i index of the place within onRouteList
     * @return boolean indicating whether the image can be rendered or not
     */
    public boolean imageRendered(int i) {
        if(onRouteList == null) {
            return false;
        }
        
        if(i >= onRouteList.size()) {
            return false;
        }
        if(onRouteList != null) {
            if(onRouteList.get(i)!= null) {
                return onRouteList.get(i).photosAvailable(); 
            } else return false;
        } else return false;
        
    }
    
    /**
     * Returns a boolean to indicate, if an image should be rendered or not. The
     * parameter i indicates the index of the place within onRouteList for which
     * the availability of an image should be checked. The method returns true,
     * if the specified place has an image and false if none was found. Only 
     * checks if a secondary image of the place is available.
     * 
     * @param i index of the place within onRouteList
     * @return boolean indicating whether the image can be rendered or not
     */
    public boolean secondImageRendered(int i) {
         if(onRouteList == null) {
            return false;
        }
        
        if(i >= onRouteList.size()) {
            return false;
        }
        if(onRouteList != null) {
            if(onRouteList.get(i)!= null) {
                if(onRouteList.get(i).photosAvailable() && onRouteList.get(i).getPhotoAmount() > 1) {
                    return true;
                } else return false;
            } else return false;
        } else return false;
        
    }
    
    /**
     * Fills editorOnRouteList and editorOffRouteList with the same places found
     * in onRouteList and offRouteList.
     */
    public void fillEditorLists() {
        editorOnRouteList.addAll(onRouteList);
        editorOffRouteList.addAll(offRouteList);
    }
    
    /**
     * Updates both onRouteList and offRouteList when changes to the route
     * are made via the editor.
     */
    public void refreshLists() {
        refreshOnRouteList();
        refreshOffRouteList();
    }
    
    /**
     * Updates onRouteList with editorOnRouteList when changes to the route
     * are made via the editor.
     */
    public void refreshOnRouteList() {
        this.onRouteList = this.editorOnRouteList;
    }
    
    /**
     * Updates offRouteList with editorOffRouteList when changes to the route
     * are made via the editor.
     */
    public void refreshOffRouteList() {
        this.offRouteList = this.editorOffRouteList;
    }
    
    /**
     * returns a String[] array of the images available for all places
     * that are currently on the route. The array contains URL's of all the
     * images.
     * 
     * @return the array with the image URL's
     */
    public String[] getImageArray() {
        String[] returnArray = new String[10];
        for(int i = 0; i < 10; i++) {
            returnArray[i] = "";
        }
        for(int i = 0; i < onRouteList.size(); i++) {
            returnArray[i] = getImageURL(i);
        }
        return returnArray;
    }
    
    /**
     * Checks if a description for the place is available or not.
     * 
     * @return a boolean
     */
    public boolean descriptionAvailable() {
        if(wiki.getDescription() == null) {
            return false;
        }
        if(wiki.getDescription().length() > 0) {
            return true;
        } else return false;
    }
    
    /**
     * Returns the full, unshortened description from the wiki.
     * @return String of the full description
     */
    public String getFullDescription() {
        if(!descriptionAvailable()) {
            return "";
        }
        return wiki.getFullDescription();
    }
    
    /**
     * Returns HTML Attributions for a photo of a specified place. PlaceIndex
     * is the index of the place within onRouteList and photoIndex is the index
     * of the photo belonging to the place.
     * @param placeIndex
     * @param photoIndex
     * @return String of HTML attributions for a photo
     */
    public String getHtmlAttributions(int placeIndex, int photoIndex) {
        if(onRouteList.size() <= placeIndex) {
            return "";
        } else if(onRouteList.get(placeIndex).getPhotoAmount() <= photoIndex) {
            return "";
        } else if(onRouteList.get(placeIndex).getPhoto(photoIndex).getHtml_attributions() == null) {
            return "";
        } else return onRouteList.get(placeIndex).getPhoto(photoIndex).getHtml_attributions();
    }
    
    /**
     * Checks the input for validity by removing all special characters and
     * numbers. If the input field has been left empty, this sets the search
     * term as "New York City".
     */
    private void cleanSearchTerm() {
        if(this.searchTerm.equals("") || this.searchTerm.trim().length() == 0) {
            this.searchTerm = "New York City";
        } else {
            String newSearchTerm;
            try {
                newSearchTerm = new String(searchTerm.getBytes("ISO-8859-1"), "UTF-8");
                newSearchTerm = newSearchTerm
                    .replaceAll("ü", "ue")
                    .replaceAll("ö", "oe")
                    .replaceAll("ä", "ae")
                    .replaceAll("ß", "ss")
                    .replaceAll("Ü", "UE")
                    .replaceAll("Ö", "OE")
                    .replaceAll("Ä", "AE");
                newSearchTerm = newSearchTerm.replaceAll("[^a-zA-Z ]", "");
                this.searchTerm = newSearchTerm.trim();  
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SearchBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
