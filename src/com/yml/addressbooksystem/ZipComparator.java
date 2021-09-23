import java.util.Comparator;

public class ZipComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact o1, Contact o2) {
        return Integer.compare(o1.getZip(), o2.getZip());
    }
}
