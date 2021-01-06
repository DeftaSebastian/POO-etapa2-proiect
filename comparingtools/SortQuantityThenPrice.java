package comparingtools;

import production.Producer;

import java.util.Comparator;

public class SortQuantityThenPrice implements Comparator<Producer> {
    @Override
    public int compare(Producer o1, Producer o2) {
        if (o1.getEnergyPerDistributor() - o2.getEnergyPerDistributor() == 0) {
            if (o1.getPriceKW() - o2.getPriceKW() == 0) {
                return (int) o1.getId() - (int) o2.getId();
            } else {
                return (int) o1.getPriceKW() - (int) o2.getPriceKW();
            }
        } else {
            return (int) o1.getEnergyPerDistributor() - (int) o2.getEnergyPerDistributor();
        }
    }
}