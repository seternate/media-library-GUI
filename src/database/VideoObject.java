package database;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import config.Configuration;
import database.JSON.JSONArray;
import database.JSON.JSONObject;

public abstract class VideoObject implements Serializable{

	
	
	
	//Static variables
	private static final long serialVersionUID = 1615842231667441259L;
	private static final String ID = "id",
								OVERVIEW = "overview",
								TMDBDATEFORMAT = "yyyy-MM-dd";
	
	
	
	
	//Instance variables
	private JSONObject jsonObj;
	
	
	
	
	//Constructors
	public VideoObject(){
		this.jsonObj = new JSONObject();
	}
	
	public VideoObject(JSONObject jsonObj){
		this.jsonObj = jsonObj;
	}
	
	
	
	
	//Abstract instance methods
	public abstract String getReleaseDate();
		
	public abstract String getTitle();
		
	public abstract String getPosterPath();
	
	
	
	
	//Instance methods
	public final int getId(){
		return this.getInt(VideoObject.ID);
	}
	
	public final String getOverview(){
		return this.getString(VideoObject.OVERVIEW);
	}
	
	
	
	
	private final String parseText(String oldString){
		try {
			return new String(oldString.getBytes(), Configuration.getTextFormat());
		} catch (UnsupportedEncodingException e) {
			return oldString;
		}
	}
	
	private final String parseDate(String oldDate){
		try {
			Date date = new SimpleDateFormat(VideoObject.TMDBDATEFORMAT).parse(oldDate);
			return new SimpleDateFormat(Configuration.getDateFormat()).format(date);
		} catch (ParseException e) {
			return oldDate;
		}
	}
	
	private final String parseCurrency(Number oldCurrency){
		switch(Configuration.getRegion()){
		case "de":
			return NumberFormat.getNumberInstance(Locale.GERMAN).format(oldCurrency) + "€";
		default:
			return NumberFormat.getNumberInstance(Locale.US).format(oldCurrency) + "$";
		}
		
	}	
	
	
	
	
	final Object get(String key){
		return this.jsonObj.get(key);
	}
	
	final Number getNumber(String key){
		return (Number)this.get(key);
	}
	
	final String getString(String key){
		return this.get(key).equals(null) ? null:this.parseText((String)this.get(key));
	}
	
	final String getDate(String key){
		return this.parseDate(this.getString(key));
	}
	
	final String getCurrency(String key){
		return this.parseCurrency(this.getNumber(key));
	}
	
	final int getInt(String key){
		return (int)this.get(key);
	}
	
	final boolean getBoolean(String key){
		return (boolean)this.get(key);
	}
	
	final double getDouble(String key){
		return (double)this.get(key);
	}
	
	
	
	
	final Map<String, Integer> getNamesIds(String arrayKey, String firstKey, String secondKey){
		JSONArray list = (JSONArray)this.get(arrayKey);
		Map<String, Integer> map = new HashMap<String, Integer>();
		list.forEach(element -> {map.put(this.parseText(((JSONObject)element).getString(firstKey)), ((JSONObject)element).getInt(secondKey));});
		return map;
	}
	
	final Map<String, String> getNamesIsos(String arrayKey, String firstKey, String secondKey){
		JSONArray list = (JSONArray)this.get(arrayKey);
		Map<String, String> map = new HashMap<String, String>();
		list.forEach(element -> {map.put(this.parseText(((JSONObject)element).getString(firstKey)), this.parseText(((JSONObject)element).getString(secondKey)));});
		return map;
	}
	
	final List<String> getNames(Map<String, ?> map){
		List<String> list = new ArrayList<String>();
		Object[] nameArray = map.keySet().toArray();
		for(Object element : nameArray){
			list.add((String)element);
		}
		return list;
	}
	
	final List<Integer> getIds(Map<String, Integer> map){
		List<Integer> list = new ArrayList<Integer>();
		Object[] idArray = map.values().toArray();
		for(Object element : idArray){
			list.add((int)element);
		}
		return list;
	}
	
	final List<String> getIsos(Map<String, String> map){
		List<String> list = new ArrayList<String>();
		Object[] idArray = map.values().toArray();
		for(Object element : idArray){
			list.add((String)element);
		}
		return list;
	}
	
	final List<String> getNames(String key){
		JSONArray list = (JSONArray)this.get(key);
		List<String> originCountryList = new ArrayList<String>();
		list.forEach(element -> {originCountryList.add((String)element);});
		return originCountryList;
	}
	
	final List<Integer> getListInteger(String key){
		JSONArray list = (JSONArray)this.get(key);
		List<Integer> runtimeList = new ArrayList<Integer>();
		list.forEach(element -> {runtimeList.add((int)element);});
		return runtimeList;
	}
	
	final String getAllNames(List<String> names){
		String allNames = new String();
		for(int i = 0; i < names.size() - 1; i++){
			allNames += names.get(i) + ", ";
		}
		return allNames + names.get(names.size()- 1);
	}
	
	
	
		
	public final void put(String key, String value){
		this.jsonObj.put(key, value);
	}
	
	final void put(String key, int value){
		this.jsonObj.put(key, value);
	}
	
	final void put(String key, double value){
		this.jsonObj.put(key, value);
	}
	
	final void put(String key, boolean value){
		this.jsonObj.put(key, value);
	}
	
}
