package strategies;

import actions.Actions;
import comparingtools.SortQuantityThenPrice;
import inclasses.Distributor;
import inclasses.Producer;

import java.util.List;

public final class QuantityStrategy implements Strategy {
    @Override
    public void method(List<Producer> producerList, Distributor distributor, int month) {
        long totalEnergy = 0;
        Actions actions = new Actions();
        producerList.sort(new SortQuantityThenPrice());
        actions.addDistributorsToProducers(producerList, distributor, month);
    }
}
