package booking;

import org.json.JSONObject;

import java.util.ArrayList;

public class Area {
    private String city;
    private String road;
    private String number;
    private String zipCode;

    public Area(){

    }
    public Area(String area) {
    }
    public Area(JSONObject obj){
        ArrayList<String> arrayOfKeys = new ArrayList<>() ;
        if (obj != null && obj.length() > 0){
            for (String key : obj.keySet()){
                arrayOfKeys.add(key);
            }
        }
        this.city = arrayOfKeys.get(0);
        this.road = arrayOfKeys.get(1);
        this.number = arrayOfKeys.get(2);
        this.zipCode = arrayOfKeys.get(3);
//        this.city = obj.optString("city", "");
//        this.road = obj.optString("road", "");
//        this.number = obj.optString("number", "");
//        this.zipCode = obj.optString("zipCode", "");
    }

    public Area(String city, String road, String number, String zipCode) {
        this.city = city;
        this.road = road;
        this.number = number;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getRoad() {
        return road;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Area{" +
                "city='" + city + '\'' +
                ", road='" + road + '\'' +
                ", number='" + number + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
