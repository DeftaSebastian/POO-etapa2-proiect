package strategies;

import actions.Actions;
import comparingtools.SortByGreenPriceQuantity;
import comparingtools.SortPriceThenQuantity;
import distributors.Distributor;
import production.Producer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GreenStrategy implements Strategy {
    @Override
    public void Strategy(List<Producer> producerList, Distributor distributor, int month) {

        long totalEnergy = 0;
        Actions actions = new Actions();
        producerList.sort(new SortByGreenPriceQuantity());
        actions.addDistributorsToProducers(producerList, distributor, month, totalEnergy);

    }
}
