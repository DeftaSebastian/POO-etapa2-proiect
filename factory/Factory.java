package factory;

import consumers.Consumer;
import distributors.Distributor;
import outclass.ConsumerForDistributor;
import outclass.ConsumerOut;
import outclass.DistribuitorOut;
import outclass.OutClass;

import java.util.HashMap;
import java.util.List;

public final class Factory {
    private static Factory factory = null;

    private Factory() {
    }

    /**
     * functie care creeaza singleton-ul factory daca acesta nu a fost creat
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
     * @param consumerList lista de consumatori
     * @param distributorList lista de distribuitori
     * @param mapClientBills hashMap-ul cu ratele pe care clientii trebuie sa le plateasca
     * @return intoarce noua clasa de tip OutClass creata si populata
     */
    public OutClass createOutClass(final List<Consumer> consumerList,
                                   final List<Distributor> distributorList,
                                   final HashMap<Long, Long> mapClientBills) {
        OutClass out = new OutClass();
        for (Consumer consumer : consumerList) {
            ConsumerOut consumerOut =
                    new ConsumerOut(consumer.getId(), consumer.isBankrupt(), consumer.getBudget());
            out.getConsumers().add(consumerOut);
        }
        for (Distributor distributor : distributorList) {
            DistribuitorOut distribuitorOut =
                    new DistribuitorOut(distributor.getId(), distributor.getBudget(),
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
        return out;
    }
}
