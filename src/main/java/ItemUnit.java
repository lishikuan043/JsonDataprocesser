import java.util.HashSet;
import java.util.Set;

public class ItemUnit {

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

    @Override
    public String toString() {
        return  "position=" + position + " ---serviceSet=" + serviceSet;
    }
}
