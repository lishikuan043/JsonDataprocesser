import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

public class JsonToJava {

    public static String getNameinjason(JSONObject district){
        return null;
    }

    public static String dealDistrict(JSONObject district){
        StringBuffer sb = new StringBuffer();
        sb.append(district.getJSONObject("province").getString("name"));
        sb.append(district.getJSONObject("city").getString("name"));
        return sb.toString();
    }




    public static void main(String[] args) throws Exception{
        FileReader fr = new FileReader("E:\\data\\raw1\\raw.html");
        BufferedReader bfr = new BufferedReader(fr);
        String jasonString = bfr.readLine();
        System.out.println(jasonString);
        JSONObject jsonObject = new JSONObject(jasonString);
        System.out.println(jsonObject);
        JSONObject district = jsonObject.getJSONObject("district");
        System.out.println(district);
        System.out.println(dealDistrict(district));
        JSONArray servicesArray = jsonObject.getJSONArray("allServices");
        System.out.println(servicesArray);



    }
}
