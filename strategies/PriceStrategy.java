package strategies;

import actions.Actions;
import comparingtools.SortPriceThenQuantity;
import distributors.Distributor;
import production.Producer;

import java.util.List;

public final class PriceStrategy implements Strategy {
    @Override
    public void Strategy(List<Producer> producerList, Distributor distributor, int month) {
        long totalEnergy = 0;
        Actions actions = new Actions();
        producerList.sort(new SortPriceThenQuantity());
        actions.addDistributorsToProducers(producerList, distributor, month, totalEnergy);
    }
}
