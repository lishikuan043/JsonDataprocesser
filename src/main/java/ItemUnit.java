import java.io.Serializable;
import java.util.Set;

public class ItemUnit implements Serializable{

    private String position;
    private Set<String> serviceSet;

    public ItemUnit(String position, Set<String> serviceSet) {
        this.position = position;
        this.serviceSet = serviceSet;
    }

    public String getPosition() {
        return position;
    }

    public Set<String> getServiceSet() {
        return serviceSet;
    }

    public int getServiceNum() {
        return serviceSet.size();
    }

    public String getServicesInString() {
        StringBuffer sb = new StringBuffer();
        for (String s :
                serviceSet) {
            sb.append(s);
            sb.append(",");
        }
        if (sb.length() == 0){
            return "无服务";
        }else {
            return sb.deleteCharAt(sb.length()-1).toString();
        }
    }

    @Override
    public String toString() {
        return  "position=" + position + " ---serviceSet=" + serviceSet;
    }
}
