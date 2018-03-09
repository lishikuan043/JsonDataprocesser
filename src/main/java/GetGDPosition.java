
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 通过java api从高德地图获取经纬度
 *  address 位置
 *  output 返回结果格式
 *  key  高德key值，需申请
 */

public class GetGDPosition {
    private static AsyncHttpClient client;

    static {
        AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
        builder.setCompressionEnabled(true).setAllowPoolingConnection(true);
        builder.setRequestTimeoutInMs((int) TimeUnit.MINUTES.toMillis(50));
        builder.setIdleConnectionTimeoutInMs((int) TimeUnit.MINUTES.toMillis(50));
        client = new AsyncHttpClient(builder.build());
    }

    public static void closeClient(){
        client.close();
    }

    public static String getJsonString(String position) throws Exception{


        //try {
        ListenableFuture<Response> future = client.prepareGet(position).execute();
        String result = future.get().getResponseBody();
        return result;
    }

    public static ArrayList<String[]> processItem(ArrayList<ItemUnit> itemUnits) throws Exception {
        /*String url = "http://restapi.amap.com/v3/geocode/geo?address=" +
                "辽宁沈阳市皇姑区" +
                "&output=JSON&key=cd959357ec0cb32606b9ad80e7462fd3";*/
        int i=0;
        ArrayList<String[]> data4GD = new ArrayList<>();
        for (ItemUnit item :
                itemUnits) {
            String[] tempData = new String[2];
            StringBuffer sb = new StringBuffer();
            sb.append("http://restapi.amap.com/v3/geocode/geo?address=");
            sb.append(item.getPosition());
            sb.append("&output=JSON&key=cd959357ec0cb32606b9ad80e7462fd3");
            JSONObject job = new JSONObject(getJsonString(sb.toString()));
            tempData[0] = getLngLat(job);
            tempData[1] = Integer.toString(item.getServiceNum());
            System.out.println(tempData[0]+"  "+tempData[1] + "  " +i);
            data4GD.add(tempData);
        }
        return data4GD;
    }

    private static String getLngLat(JSONObject jsonObject) {
        String staus = jsonObject.getString("status");
        if (staus.equals("1")) {
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");
            //System.out.println(jsonArray);
            JSONObject job = jsonArray.optJSONObject(0);
            return job.getString("location");

        } else {
            return "116.480724,39.989584";
        }

    }

    /*public static void main(String[] args) throws Exception{
        processItem();
        closeClient();
    }*/

}
