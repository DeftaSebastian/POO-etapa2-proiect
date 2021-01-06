package strategies;

import actions.Actions;
import comparingtools.SortPriceThenQuantity;
import distributors.Distributor;
import production.Producer;

import java.util.ArrayList;
import java.util.List;

public class GreenStrategy implements Strategy {
    @Override
    public void Strategy(List<Producer> producerList, Distributor distributor, int month) {

        long totalEnergy = 0;
        Actions actions = new Actions();
        List<Producer> wantedProducers = new ArrayList<>();
        for (Producer producer : producerList) {
            if (producer.getEnergyType().isRenewable()) {
                wantedProducers.add(producer);
            }
        }
        wantedProducers.sort(new SortPriceThenQuantity());
        actions.addDistributorsToProducers(wantedProducers, distributor, month, totalEnergy);
    }
}
