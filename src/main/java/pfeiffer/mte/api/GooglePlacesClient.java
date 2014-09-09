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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pfeiffer.mte.entities.Place;

import pfeiffer.mte.entities.Photo;

/**
 *  This client is responsible for all communication with the Google Places API.
 * It sends HTTP requests to the Google Places web service and receives
 * information about places in response.
 * @author Philipp Pfeiffer
 */
public class GooglePlacesClient implements Serializable{
    
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_DETAILS = "/details";
    private static final String TYPE_SEARCH = "/search";
    private static final String TYPE_TEXTSEARCH = "/textsearch";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyCT9W_P2am39nyEdnSX13ap4ujJbLtXLU0";
    
    /**
     * Sends an HTTP request to the Google Places web service to find relevant
     * places in the city or region. The method extracts Place information from
     * the response and returns an ArrayList<Place> of all the relevant places 
     * found.
     * @param query is the name of the city or region that is searched for
     * @param radius is the radius around the city in which the search should
     * happen
     * @return ArrayList<Place> of all the relevant places within the specified
     * radios of the city or region
     */
    public static ArrayList<Place> textSearch(String query, int radius) {
        
        ArrayList<Place> placeList = null;
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_TEXTSEARCH);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&query=" + URLEncoder.encode(query, "utf8"));
            sb.append("&radius=" + String.valueOf(radius));
            
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the Place descriptions from the results
            placeList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Place place = new Place();
                place.setReference(predsJsonArray.getJSONObject(i).getString("reference"));
                place.setIcon(predsJsonArray.getJSONObject(i).getString("icon"));
                String name = new String(predsJsonArray.getJSONObject(i).getString("name").getBytes("ISO-8859-1"), "UTF-8");
                place.setName(name);
                String formattedAddress = new String(predsJsonArray.getJSONObject(i).getString("formatted_address").getBytes("ISO-8859-1"), "UTF-8");
                place.setFormattedAddress(formattedAddress);
                JSONObject geometryObject = (predsJsonArray.getJSONObject(i).getJSONObject("geometry"));
                JSONObject locationObject = (geometryObject.getJSONObject("location"));
                place.setLat(locationObject.getDouble("lat"));
                place.setLng(locationObject.getDouble("lng"));
                placeList.add(place);
            }
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return placeList;  
    }

    /**
     * Requests photo information for a place from the Google Places web
     * service. All photos relevant for a place that are found are
     * encapsulated within the place.
     * @param place without photos
     * @return Place with photos
     */
    public static Place getPhotoReferences(Place place) { 
                
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
             sb.append(TYPE_DETAILS);
             sb.append(OUT_JSON);
             sb.append("?sensor=false");
             sb.append("&key=" + API_KEY);
             sb.append("&reference=" + URLEncoder.encode(place.getReference(), "utf8"));
             
              URL url = new URL(sb.toString());
              connection = (HttpURLConnection) url.openConnection();
             InputStreamReader in = new InputStreamReader(connection.getInputStream());
             
             int read;
             char[] buff = new char[1024];
             while ((read = in.read(buff)) != -1) {
             jsonResults.append(buff, 0, read);
             }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");

            JSONArray photosArray = jsonObj.getJSONArray("photos");
            
            for(int i=0; i<photosArray.length(); i++) {
                String photo = photosArray.getString(i);
                JSONObject photoObject = new JSONObject(photo); 
                String photoReference = photoObject.getString("photo_reference");
                int height = photoObject.getInt("height");
                int width = photoObject.getInt("width");
                JSONArray attributionsArray = photoObject.getJSONArray("html_attributions");
                String html_attributions = "";
                if(attributionsArray.length() > 0) {
                    html_attributions = attributionsArray.getString(0);
                }
                
                
                
                Photo newPhoto = new Photo();
                newPhoto.setReference(photoReference);
                newPhoto.setHeight(height);
                newPhoto.setWidth(width);
                newPhoto.setURL("https://maps.googleapis.com/maps/api/place/photo?maxwidth=380&maxheight=380&photoreference=" + photoReference + "&sensor=false&key=" + API_KEY);
                newPhoto.setHtml_attributions(html_attributions);
                place.addPhoto(newPhoto);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return place;
    }
    
    /**
     * Sends an HTTP request to the Google Places web service to find relevant
     * places in the city or region. The method extracts Place information from
     * the response and returns an ArrayList<Place> of all the relevant places 
     * found. This method is an alternative to the textsearch.
     * @param keyword
     * @param lat
     * @param lng
     * @param radius
     * @return ArrayList<Place> of all the relevant places within the specified
     * radios of the city or region
     */
    public static ArrayList<Place> search(String keyword, double lat, double lng, int radius) {
        
        ArrayList<Place> placeList = null;
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&keyword=" + URLEncoder.encode(keyword, "utf8"));
            sb.append("&location=" + String.valueOf(lat) + "," + String.valueOf(lng));
            sb.append("&radius=" + String.valueOf(radius));
            
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("results");

            // Extract the Place descriptions from the results
            placeList = new ArrayList<Place>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                Place place = new Place();
                place.setReference(predsJsonArray.getJSONObject(i).getString("reference"));
                place.setName(predsJsonArray.getJSONObject(i).getString("name"));
                placeList.add(place);
            }
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return placeList;    
    }
    
    /**
     * Sends an HTTP request to the Google Places web service to receive
     * detailed information about a specific place in response. The information
     * is extracted from the response and returned as a Place object
     * @param reference
     * @return Place object with detailed information
     */
    public static Place details(String reference) {
        Place place = null;
    
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");

            place = new Place();
            place.setReference(reference);
            place.setIcon(jsonObj.getString("icon"));
            place.setName(jsonObj.getString("name"));
            place.setFormattedAddress(jsonObj.getString("formatted_address"));
            place.setLat(jsonObj.getDouble("lat"));
            place.setLng(jsonObj.getDouble("lng"));
            if (jsonObj.has("formatted_phone_number")) {
                place.setFormattedPhoneNumber(jsonObj.getString("formatted_phone_number"));
            }
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return place;
    }
    
    /**
     * Sends an HTTP request to the Google Places web service to receive
     * detailed information about a specific place in response. The information
     * is extracted from the response and returned as a Place object
     * @param place object without detailed information
     * @return Place object with detailed information
     */
    public Place details(Place place) {
        String reference = place.getReference();
    
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
            sb.append(TYPE_DETAILS);
            sb.append(OUT_JSON);
            sb.append("?sensor=false");
            sb.append("&key=" + API_KEY);
            sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connection.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");

            place = new Place();
            place.setReference(reference);
            place.setIcon(jsonObj.getString("icon"));
            place.setName(jsonObj.getString("name"));
            place.setFormattedAddress(jsonObj.getString("formatted_address"));
            if (jsonObj.has("formatted_phone_number")) {
                place.setFormattedPhoneNumber(jsonObj.getString("formatted_phone_number"));
            }
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return place;
    }
    
    
}
