package pfeiffer.mte.entities;

import java.util.ArrayList;

/**
 *  Represents a list of places and is used for building OffRouteList and 
 * OnRouteList. The ArrayList<Place> found in this class represents a list of
 * all places that were found, regardless of whether or not they are on the
 * current route.
 * @author Philipp Pfeiffer
 */
public class PlacesList {
    
    private ArrayList<Place> placesList;
    
    public PlacesList() {
    }
    
    /**
     * Replaces the list of places with a new ArrayList<Place>
     * @param placesList 
     */
    public void setPlacesList(ArrayList<Place> placesList) {
        this.placesList = placesList;
    }
    
    /**
     * Returns the ArrayList<Place> including all places
     * @return ArrayList<Place> of all places
     */
    public ArrayList<Place> getPlacesList() {
        return placesList;
    }
    
    /**
     * Returns the size of the list of places and hence the amount of places
     * present within the list.
     * @return int value of the amount of places
     */
    public int getSize() {
        return placesList.size();
    }
    
    /**
     * Adds a place to the list
     * @param place 
     */
    public void addPlace(Place place) {
        placesList.add(place);
    }
    
    /**
     * Removes the given place from the list
     * @param place 
     */
    public void removePlace(Place place) {
        placesList.remove(place);
    }
    
    /**
     * Removes the place found at the given index from the list
     * @param index 
     */
    public void removePlace(int index) {
        placesList.remove(index);
    }
    
    /**
     * Returs the place found at the given index of the list
     * @param index
     * @return Place found at the given index
     */
    public Place getPlace(int index) {
        return placesList.get(index);
    }
    
    /**
     * Replaces the place found at the given index of the list with a new place
     * @param index
     * @param place 
     */
    public void setPlace(int index, Place place) {
        placesList.set(index, place);
    }
    
    /**
     * Builds an ArrayList<Place> of all the places that are on the Route. The
     * size parameter indicates how many places the route should include.
     * @param size
     * @return ArrayList<Place> of all places on the route
     */
    public ArrayList<Place> buildOnRouteList(int size) {
        ArrayList<Place> onRouteList = new ArrayList<>();
         if(placesList.size() < size) {
             for(int i = 0; i < placesList.size(); i++) {
                 onRouteList.add(placesList.get(i));
             }
         } else {
             for(int i = 0; i < size; i++) {
                 onRouteList.add(placesList.get(i));
             }
         }
         return onRouteList;
    }
    
    /**
     * Builds and returns an ArrayList<Place> that includes all places that
     * are not part of the current route. The cutOffIndex parameter indicates
     * which places in the list should be off the route. All places below the
     * cutoff index are ignored.
     * @param cutOffIndex
     * @return ArrayList<Place> of all places that are off route
     */
    public ArrayList<Place> buildOffRouteList(int cutOffIndex) {
        ArrayList<Place> offRouteList = new ArrayList<>();
        if(placesList.size() < cutOffIndex) {
            return offRouteList;
        } else {
            for(int i = cutOffIndex; i < placesList.size(); i++) {
                offRouteList.add(placesList.get(i));
            }
        }
        return offRouteList;
    }
}
