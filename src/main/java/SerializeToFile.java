import java.io.*;
import java.util.ArrayList;

public class SerializeToFile {

    //保存数据对象
    public static Boolean save(ArrayList<ItemUnit> itemUnits, String filepath) {
        File file = new File(filepath);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(itemUnits);
            oos.flush();
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean savedata4GD(ArrayList<String[]> data4GD, String filepath) throws Exception{
        File file = new File(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(data4GD);
        oos.flush();
        oos.close();
        return true;
    }

    public static ArrayList<ItemUnit> get(String filepath) {
        ArrayList<ItemUnit> itemUnits = new ArrayList<>();

        File file = new File(filepath);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            try {
                itemUnits = (ArrayList<ItemUnit>)ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemUnits;

    }
}
