import java.util.Comparator;

public class CityComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}
