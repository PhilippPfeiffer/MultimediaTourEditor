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
import pfeiffer.mte.entities.Video;

/**
 * This client is responsible for searching for youtube videos related to the
 * city or region. It sends an HTTP request to the youtube API and receives
 * search results in return.
 * 
 * @author Philipp Pfeiffer
 */
public class YoutubeSearchClient implements Serializable{
    
    private static final String YOUTUBE_DATA_API_BASE = "https://www.googleapis.com/youtube/v3";
    private static final String TYPE_SEARCH = "/search";
    private static final String API_KEY = "AIzaSyCT9W_P2am39nyEdnSX13ap4ujJbLtXLU0";
    
    /**
     * Sends an HTTP request to the Youtube web service and receives search
     * results as a response. The method extracts video information from the
     * results and returns a VideoList containing all information to all the
     * videos that were found in the response.
     * @param query
     * @return VideoList with all found videos
     */
    public ArrayList<Video> search(String query) {
        ArrayList<Video> videoList = null;
        HttpURLConnection connection = null;
        StringBuilder jsonResults = new StringBuilder();
        
        try {
            StringBuilder sb = new StringBuilder(YOUTUBE_DATA_API_BASE);
            sb.append(TYPE_SEARCH);
            sb.append("?part=snippet");
            sb.append("&q=" + URLEncoder.encode(query, "utf8"));
            sb.append("&order=rating");
            sb.append("&type=video");
            sb.append("videoEmbeddable=true");
            sb.append("&key=" + API_KEY);
            
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
            JSONObject restultJsonObj = new JSONObject(jsonResults.toString());
            JSONArray itemsArray = restultJsonObj.getJSONArray("items");
            
            videoList = new ArrayList<Video>(itemsArray.length());
            for (int i = 0; i < itemsArray.length(); i++) {
                Video video = new Video();
                JSONObject vidJsonObj = new JSONObject(itemsArray.get(i).toString());
                JSONObject idJsonObj = new JSONObject(vidJsonObj.getJSONObject("id").toString());
                video.setId(idJsonObj.getString("videoId"));
                videoList.add(video);
            }
        } catch (JSONException ex) {
            Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return videoList;  
    }
}