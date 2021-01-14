package comparingtools;

import production.Producer;

import java.util.Comparator;

public final class SortByGreenPriceQuantity implements Comparator<Producer> {
    @Override
    public int compare(Producer o1, Producer o2) {
        if (o1.getEnergyType().isRenewable() && o2.getEnergyType().isRenewable()) {
            if (o1.getPriceKW() == o2.getPriceKW()) {
                return (int) o2.getEnergyPerDistributor() - (int) o1.getEnergyPerDistributor();
            } else {
                if (o1.getPriceKW() - o2.getPriceKW() < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        } else if (o1.getEnergyType().isRenewable() && !o2.getEnergyType().isRenewable()) {
            return -1;
        } else if (!o1.getEnergyType().isRenewable() && o2.getEnergyType().isRenewable()) {
            return 1;
        } else {
            if (o1.getPriceKW() == o2.getPriceKW()) {
                return (int) o2.getEnergyPerDistributor() - (int) o1.getEnergyPerDistributor();
            } else if (o1.getPriceKW() - o2.getPriceKW() < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
