package external;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.Item;
public class TomTomAPI {
	private static final String API_HOST = "api.tomtom.com";
	private static final String SEARCH_PATH = "/search/2/search/";
	private static final String DEFAULT_TERM = "";  // no restriction
	private static final String API_KEY = "ySp0JDvvLy8bE9FaIDyQeJdJWcB5gNNM";

	/**
	 * Creates and sends a request to the TicketMaster API by term and location.
	 */
	private List<Item> getItemList(JSONArray items) throws JSONException {
		List<Item> itemList = new ArrayList<>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject oldItem = items.getJSONObject(i);
			//System.out.println(oldItem);
			String id = (String) oldItem.get("id");
			double score = (double)oldItem.get("score");
			JSONObject poi = (JSONObject)oldItem.get("poi");
			String name = poi.getString("name");
			String phone = poi.getString("phone");
			//System.out.println(poi);
			String url = "";
			if (poi.has("url")){
				url =  poi.getString("url");
			}
			
			JSONObject addressObj = (JSONObject)oldItem.get("address");
			String address = addressObj.getString("freeformAddress");
			//System.out.println(name);
			//if (poi != null){
			Set<String> categories = getCategories(poi);
			//}
			//System.out.println(categories);
			Item item = new Item();
			if (id != null){
				item.setId(id);
			}
			
			//item.setScore(score);
			if (name != null){
				item.setName(name);
			}
			if (phone != ""){
				item.setPhone(phone);
			}
			if ( url != null){
				item.setUrl(url);
			}
			if (address != null){
				item.setAddress(address);
			}
		    if (categories != null){
				item.setCategories(categories);
			}
			
			itemList.add(item);
		}

		return itemList;
	}
	public List<Item> search(double lat, double lon, String term) {
		

		String url = "https://" + API_HOST + SEARCH_PATH;
		String latlong = lat + "," + lon;
		if (term == null) {
			term = DEFAULT_TERM;
		}
		//System.out.println(term);
		//term = urlEncodeHelper(term);
		String jsonQuery = String.format("%s.json", term);
		String query = String.format("key=%s&lat=%s&lon=%s", API_KEY, lat, lon);
	
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url +jsonQuery+ "?" + query).openConnection();
			connection.setRequestMethod("GET");
 
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url + "?" + query);
			System.out.println("Response Code : " + responseCode);
 
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// Extract events array only.
			JSONObject responseJson = new JSONObject(response.toString());
			//JSONObject embedded = (JSONObject) responseJson.get("_embedded");
			JSONArray itemsJson = (JSONArray) responseJson.get("results");
			//System.out.println(itemsJson);
			 List<Item> newItems = getItemList(itemsJson);
			
			return newItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
 
	private String urlEncodeHelper(String term) {
		try {
			term = java.net.URLEncoder.encode(term, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return term;
	}
 
	private void queryAPI(double lat, double lon, String term) {
	
		List<Item> itemList = search(lat, lon, term);
		try {
			for (Item item : itemList) {
				JSONObject jsonObject = item.toJSONObject();
				System.out.println(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Set<String> getCategories(JSONObject event) throws JSONException {
		Set<String> categories = new HashSet<>();
		JSONArray classifications = null;
		if (event.has("categories")){
			classifications = (JSONArray) event.get("categories");
		}
		int length = 0;
		if (classifications != null){
			length = classifications.length();
		}
		for (int j = 0; j < classifications.length(); j++) {
			String classification = classifications.getString(j);
			
			categories.add(classification);
		}
		//System.out.println("-----");
		//System.out.println(event);
		return categories;
	}

	/**
	 * Main entry for sample TicketMaster API requests.
	 */
	//public static void main(String[] args) {
		//TomTomAPI tmApi = new TomTomAPI();
		//tmApi.queryAPI(37.8085, -122.4239, "hotdog");
	//}

}
