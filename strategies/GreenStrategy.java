package strategies;

import actions.Actions;
import comparingtools.SortByGreenPriceQuantity;
import distributors.Distributor;
import production.Producer;

import java.util.List;

public final class GreenStrategy implements Strategy {
    @Override
    public void method(List<Producer> producerList, Distributor distributor, int month) {

        Actions actions = new Actions();
        producerList.sort(new SortByGreenPriceQuantity());
        actions.addDistributorsToProducers(producerList, distributor, month);

    }
}
