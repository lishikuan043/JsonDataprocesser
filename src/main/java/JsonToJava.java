import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class JsonToJava {

    public static String getNameinjason(JSONObject job,String name){
        String s = job.getJSONObject(name).getString("name");
        if (s.equals("null")) {
            return "";
        }else {
            return s;
        }

    }

    //返回String类型的完整地址信息
    public static String dealDistrict(JSONObject district){
        StringBuffer sb = new StringBuffer();
        sb.append(getNameinjason(district,"province"));
        sb.append(getNameinjason(district,"city"));
        sb.append(getNameinjason(district,"area"));
        sb.append(getNameinjason(district,"fouth"));
        return sb.toString();
    }

    //获取区域内所有服务并存在Set中（防止重复）
    public static Set<String> getAllServices(JSONArray jsonArray) {
        HashSet<String> serviceSet = new HashSet<>();
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject)iterator.next();
            while (iterator.hasNext()) {
                JSONObject jObject = (JSONObject)iterator.next();
                Map<String,Object> map = jObject.toMap();
                ArrayList<String> services= (ArrayList<String>)map.get("services");
                for (String s : services) {
                    serviceSet.add(s);
                }
            }
        }
        return serviceSet;
    }




    public static void main(String[] args) throws Exception{
        ArrayList<ItemUnit> itemUnits = new ArrayList<>();
        FileReader fr = new FileReader("E:\\data\\raw1\\raw.html");
        BufferedReader bfr = new BufferedReader(fr);
        String jasonString;
        while ((jasonString = bfr.readLine()) != null) {
            JSONObject jsonObject = new JSONObject(jasonString);
            //获取地址
            JSONObject district = jsonObject.getJSONObject("district");
            //获取服务数量
            JSONArray servicesArray = jsonObject.getJSONArray("allServices");
            getAllServices(servicesArray);
            //存放ItemUnit
            ItemUnit itemUnit = new ItemUnit(dealDistrict(district),getAllServices(servicesArray));
            itemUnits.add(itemUnit);
            if (SaveToMysql.SaveData(itemUnit) == 0) {
                System.out.println("可能存储失败了。。。");
            }
            System.out.println(itemUnit);
        }

        bfr.close();
        fr.close();
        System.out.println(itemUnits.size());

    }
}
