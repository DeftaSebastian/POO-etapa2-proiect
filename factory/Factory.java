package factory;

import comparingtools.SortByID;
import inclasses.Consumer;
import inclasses.Distributor;
import outclass.ConsumerForDistributor;
import outclass.ConsumerOut;
import outclass.DistribuitorOut;
import outclass.HistoryOut;
import outclass.OutClass;
import outclass.ProducerOut;
import inclasses.History;
import inclasses.Producer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public final class Factory {
    private static Factory factory = null;

    private Factory() {
    }

    /**
     * functie care creeaza singleton-ul factory daca acesta nu a fost creat
     *
     * @return intoarce singleton-ul factory
     */
    public static Factory getFactory() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    /**
     * functie care creeaza clasa de tip OutClass pentru a fi folosita la output, ea itereaza prin
     * lista de consumatori si distribuitori creeand clasele ConsumerOut, DistribuitorOut si
     * ConsumerForDistribuitor, acestea fiind folosite pentru a fi adaugate in listele din OutClass
     *
     * @param consumerList    lista de consumatori
     * @param distributorList lista de distribuitori
     * @param mapClientBills  hashMap-ul cu ratele pe care clientii trebuie sa le plateasca
     * @return intoarce noua clasa de tip OutClass creata si populata
     */
    public OutClass createOutClass(final List<Consumer> consumerList,
                                   final List<Distributor> distributorList,
                                   final List<Producer> producerList,
                                   final HashMap<Long, Long> mapClientBills,
                                   final HashMap<Long, Long> contractPrices) {
        OutClass out = new OutClass();
        for (Consumer consumer : consumerList) {
            ConsumerOut consumerOut =
                    new ConsumerOut(consumer.getId(), consumer.isBankrupt(), consumer.getBudget());
            out.getConsumers().add(consumerOut);
        }
        for (Distributor distributor : distributorList) {
            DistribuitorOut distribuitorOut =
                    new DistribuitorOut(distributor.getId(), distributor.getEnergyNeeded(),
                            contractPrices.get(distributor.getId()),
                            distributor.getBudget(),
                            distributor.getEnergyChoiceStrategyType().toString(),
                            distributor.isBankrupt());
            for (Consumer consumer : distributor.getContracts()) {
                ConsumerForDistributor consumerForDistributor = new ConsumerForDistributor();
                consumerForDistributor.setConsumerId(consumer.getId());
                consumerForDistributor.setPrice(mapClientBills.get(consumer.getId()));
                consumerForDistributor
                        .setRemainedContractMonths(consumer.getRemainedContractMonths());
                distribuitorOut.getContracts().add(consumerForDistributor);
            }
            out.getDistributors().add(distribuitorOut);
        }
        Collections.sort(producerList, new SortByID());
        for (Producer producer : producerList) {
            ProducerOut producerOut =
                    new ProducerOut(producer.getId(), producer.getMaxDistributors(),
                            producer.getPriceKW(),
                            producer.getEnergyType().toString(),
                            producer.getEnergyPerDistributor());
            for (History history : producer.getMonthlyStats()) {
                if (history.getMonth() > 0) {
                    HistoryOut historyOut = new HistoryOut();
                    historyOut.setMonth(history.getMonth());
                    for (Distributor distributor : history.getDistributors()) {
                        historyOut.getDistributorsIds().add(distributor.getId());
                    }
                    producerOut.getMonthlyStats().add(historyOut);
                }
            }
            out.getProducers().add(producerOut);
        }
        return out;
    }
}
