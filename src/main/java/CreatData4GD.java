import java.io.*;
import java.util.ArrayList;

public class CreatData4GD {

    public static void creatDataFile(ArrayList<ItemUnit> itemUnits, String filepath) throws IOException{
        File file = new File(filepath);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
        BufferedWriter  bw = new BufferedWriter(osw);
        for (ItemUnit item :itemUnits) {
            StringBuffer sb = new StringBuffer();
            sb.append(item.getPosition());
            sb.append(",");
            sb.append("\"");
            sb.append(item.getServiceNum());
            sb.append("\"");
            bw.write(sb.toString()+"\t\n");
        }
        bw.close();
        osw.close();
        fos.close();
    }
}
