package pfeiffer.mte.api;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pfeiffer.mte.entities.Wiki;

/**
 * This class handles all communication with the Freebase web service. It
 * sends an HTTP request for information about a city and receives several
 * pieces of data in response. All data is encapsulated by a Wiki object.
 * @author Philipp Pfeiffer
 */
public class FreebaseSearchClient {
    public static String API_KEY = "AIzaSyCT9W_P2am39nyEdnSX13ap4ujJbLtXLU0";
    
    /**
     * Sends an HTTP Request to the Freebase web service and extracts the
     * information that is received in the response. The data is encapsuled by
     * a Wiki object, which is then returned
     * @param searchTerm
     * @param wiki
     * @return Wiki Object with found data
     */
    public Wiki search(String searchTerm, Wiki wiki) {
    try {
      HttpTransport httpTransport = new NetHttpTransport();
      HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
      JSONParser parser = new JSONParser();
      GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/search");
      
      url.put("query", URLEncoder.encode(searchTerm, "utf8"));
      url.put("type", "City/Town/Village");
      url.put("limit", "1");
      url.put("indent", "true");
      url.put("filter", "(all type:/location/citytown type:/location/location type:/common/topic)");
      url.put("output", "(/location/location/area /common/topic/description /location/statistical_region/population /location/location/containedby)");
      url.put("lang", "d/en");
      url.put("key", FreebaseSearchClient.API_KEY);
      
      HttpRequest request = requestFactory.buildGetRequest(url);
      HttpResponse httpResponse = request.execute();
      
      JSONObject response = (JSONObject)parser.parse(httpResponse.parseAsString());
      JSONArray results = (JSONArray)response.get("result");
      org.json.JSONObject result = new org.json.JSONObject(results.get(0).toString());
      org.json.JSONObject output = new org.json.JSONObject(result.get("output").toString());
      
      
      org.json.JSONObject populationObj = new org.json.JSONObject(output.get("/location/statistical_region/population").toString());
      org.json.JSONObject areaObj = new org.json.JSONObject(output.get("/location/location/area").toString());
      org.json.JSONObject containedByObj = new org.json.JSONObject(output.get("/location/location/containedby").toString());
      org.json.JSONObject descriptionObj = new org.json.JSONObject(output.get("/common/topic/description").toString());
      
      try{
          org.json.JSONArray populationArray = populationObj.getJSONArray("/location/statistical_region/population");
          String population = populationArray.getJSONObject(0).getString("value");
          String populationDate = populationArray.getJSONObject(0).getString("date");
          wiki.setPopulation(population);
          wiki.setPopulationDate(populationDate);
      } catch (Exception ex) {
        wiki.setPopulation("No data found");
        wiki.setPopulationDate("");
        Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.INFO, null, ex);
      }
      
      try{
          org.json.JSONArray areaArray = areaObj.getJSONArray("/location/location/area");
          String area = areaArray.getString(0);
          wiki.setArea(area);
      } catch (Exception ex) {
        wiki.setArea("No data found");
        Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.INFO, null, ex);
      }
      
      try{
          org.json.JSONArray containedByArray = containedByObj.getJSONArray("/location/location/containedby");
          String containedBy = containedByArray.getJSONObject(0).getString("name");
          wiki.setCountry(containedBy);
      } catch (Exception ex) {
        wiki.setCountry("No data found");
        Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.INFO, null, ex);
      }
      
      try{
          org.json.JSONArray descriptionArray = descriptionObj.getJSONArray("/common/topic/description");
          String description = descriptionArray.getString(0);
          wiki.setDescription(description, true);
      } catch (Exception ex) {
        wiki.setDescription("No data found", false);
        Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.INFO, null, ex);
      }
     
    } catch (Exception ex) {
      Logger.getLogger(GooglePlacesClient.class.getName()).log(Level.INFO, null, ex);
    }
    
    return wiki;
  }
   
}
