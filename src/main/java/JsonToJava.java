import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
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

    public static void processData( ArrayList<ItemUnit> itemUnits) throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("E:\\data\\raw1\\raw1.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr);
        String jasonString;
        while ((jasonString = bfr.readLine()) != null) {
            JSONObject jsonObject = new JSONObject(jasonString);
            //获取地址
            JSONObject district = jsonObject.getJSONObject("district");
            //获取服务数量
            JSONArray servicesArray = jsonObject.getJSONArray("allServices");
            //生成ItemUnit
            ItemUnit itemUnit = new ItemUnit(dealDistrict(district),getAllServices(servicesArray));
            //储存ItemUnit到一个ArrayList
            itemUnits.add(itemUnit);
            //储存到Mysql中
            /*if (SaveToMysql.SaveData(itemUnit) == 0) {
                System.out.println("可能存储失败了。。。");
            }*/
            System.out.println(itemUnit);
        }
        bfr.close();
        fr.close();
    }




    public static void main(String[] args) throws Exception{
        ArrayList<ItemUnit> itemUnits = new ArrayList<>();
        ArrayList<String[]> data4GD = new ArrayList<>();
        //预处理Jason数据
        //processData(itemUnits);
        String filepath = "E:\\data\\itemUnits.dat";
        //保存数据
        /*if (SerializeToFile.save(itemUnits,filepath)) {
            System.out.println("数据保存成功！");
        } else {
            System.out.println("数据保存失败5555555");
        }*/
        //获取保存好的数据
        itemUnits = SerializeToFile.get(filepath);

        String data4GDpath = "E:\\data\\data4GD.csv";
        /*GetGDPosition.processItem(itemUnits);
        data4GD = GetGDPosition.processItem(itemUnits);
        if(SerializeToFile.savedata4GD(data4GD,data4GDpath)) {
            System.out.println("地图数据保存成功！");
        } else {
            System.out.println("地图数据保存失败5555555");
        }

        GetGDPosition.closeClient();
        System.out.println(data4GD.size());*/

        CreatData4GD.creatDataFile(itemUnits,data4GDpath);

    }
}
