package entity;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Item {
	private String id;
	private String name;
	private String phone;
	//private double score;
	private String address;
	private String url;
	private Set<String> categories ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//public double getScore() {
		//return score;
	//}
	//public void setScore(double score) {
		//this.score = score;
	//}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", id);
			obj.put("name", name);
			//obj.put("score", score);
			obj.put("address", address);
			obj.put("phone", phone);
			obj.put("categories", new JSONArray(categories));
			obj.put("url", url);

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}



}
