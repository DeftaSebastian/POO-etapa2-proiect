package comparingtools;

import production.Producer;

import java.util.Comparator;

public class SortByID implements Comparator<Producer> {
    @Override
    public int compare(Producer o1, Producer o2) {
        return (int) o1.getId() - (int) o2.getId();
    }
}
