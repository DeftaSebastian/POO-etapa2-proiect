package actions;

import consumers.Consumer;
import distributors.Distributor;
import formulas.Formulas;
import months.DistributorChanges;
import months.ProducerChanges;
import production.Producer;

import java.util.HashMap;
import java.util.List;

public final class Actions {
    /**
     * functie care aduna venitul lunar al unui consumator cu bugetul acestuia
     * @param consumerList lista consumatorilor
     */
    public void giveSalary(final List<Consumer> consumerList) {
        for (Consumer consumer : consumerList) {
            if (!consumer.isBankrupt()) {
                consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());
            }
        }
    }

    /**
     * functie care pune un consumator in lista de contracte a celui mai bun distribuitor, pune
     * id-ul distribuitorului cu care consumatorul are contract in variabila contractTo si lunile
     * de durata a contractului in remainedContractMonths. Functia populeaza hashmap-ul
     * mapClientBills astfel in cat sa stim cat trebuie sa plateasca fiecare consumator pe luna
     * @param consumerList lista de consumatori
     * @param bestDistributor cel mai bun distribuitor
     * @param mapContractPrices hashmap-ul cu preturile contractelor a tuturor distribuitorilor
     * @param mapClientBills hashmap-ul cu ratele pe care trebuie sa le plateasca consumatorii
     */
    public void makeContracts(final List<Consumer> consumerList,
                              final Distributor bestDistributor,
                              final HashMap<Long, Long> mapContractPrices,
                              final HashMap<Long, Long> mapClientBills) {
        for (Consumer consumer : consumerList) {
            if (consumer.getRemainedContractMonths() == 0 && !consumer.isBankrupt()) {
                consumer.setRemainedContractMonths(bestDistributor.getContractLength());
                bestDistributor.getContracts().add(consumer);
                consumer.setContractTo(bestDistributor.getId());
                if (mapClientBills.containsKey(consumer.getId())) {
                    mapClientBills.replace(consumer.getId(),
                            mapContractPrices.get(bestDistributor.getId()));
                } else {
                    mapClientBills
                            .put(consumer.getId(), mapContractPrices.get(bestDistributor.getId()));
                }
            }
        }
    }

    /**
     * functie care calculeaza preturile stabilite de fiecare distribuitor si cauta distribuitorul
     * cu pretul cel mai mic
     * @param distributorList lista de distribuitori
     * @return intoarce cel mai bun distribuitor
     */
    public Distributor getBestDistributor(final List<Distributor> distributorList) {
        Formulas formulas = new Formulas();
        long bestPrice;
        long id;
        bestPrice = Long.MAX_VALUE;
        id = Long.MAX_VALUE;
        for (Distributor distributor : distributorList) {
            if (!distributor.isBankrupt()) {
                if (distributor.getContracts().size() == 0) {
                    if (formulas.getFinalContractNoClients(distributor) < bestPrice) {
                        bestPrice = formulas.getFinalContractNoClients(distributor);
                        id = distributor.getId();
                    }
                } else {
                    if (formulas.getFinalContract(distributor) < bestPrice) {
                        bestPrice = formulas.getFinalContract(distributor);
                        id = distributor.getId();
                    }
                }
            }
        }
        for (Distributor distributor : distributorList) {
            if (id == distributor.getId()) {
                return distributor;
            }
        }
        return distributorList.get(0);
    }

    /**
     * functie care calculeaza ce suma de bani trebuie ca fiecare distribuitor sa primeasca de la
     * clientii acestora si ii strange pentru a fi primiti cu o alta functie. Functia aceasta mai
     * calculeaza si cazurile in care un distribuitor nu poate sa plateasca si ii se atribuie o
     * dobanda sau chiar il falimenteaza, functia mai calculeaza si cazul in care un consumator
     * nu se mai afla in lista de consumatori aflata in contractul unui distribuitor, dar acesta
     * are o dobanda la distribuitorul vechi.
     * @param consumerList lista de consumatori
     * @param distributorList lista de distribuitori
     * @param mapClientBills hashmap-ul ce contine sumele de bani pe care toti consumatorii trebuie
     *                       sa le plateasca
     * @return intoarce hashmap-ul cu sumele de bani pe care distribuitorii trebuie sa le primeasca
     */
    public HashMap<Long, Long> getMoneyFromConsumers(final List<Consumer> consumerList,
                                                     final List<Distributor> distributorList,
                                                     final HashMap<Long, Long> mapClientBills) {
        HashMap<Long, Long> moneyForDistributors = new HashMap<>();
        long moneyBag;
        Formulas formulas = new Formulas();
        for (Distributor distributor : distributorList) {
            if (!distributor.isBankrupt()) {
                moneyBag = 0;
                for (Consumer consumer : distributor.getContracts()) {
                    if (!consumer.isBankrupt()) {
                        if (consumer.getBudget() >= mapClientBills.get(consumer.getId())
                                && consumer.getDebt() == 0) {
                            moneyBag = moneyBag + mapClientBills.get(consumer.getId());
                            consumer.setBudget(
                                    consumer.getBudget()
                                            - mapClientBills.get(consumer.getId()));
                        } else if (consumer.getDebt() == 0
                                && consumer.getBudget() < mapClientBills.get(consumer.getId())) {
                            consumer.setDebt(formulas.getDebtAmount(
                                    mapClientBills.get(consumer.getId())));
                            consumer.setDebtTo(distributor.getId());
                        } else {
                            consumer.setBankrupt(true);
                            consumer.setRemainedContractMonths(0);
                        }
                    }
                }
                moneyForDistributors.put(distributor.getId(), moneyBag);
            }
        }

        long moneyBagDebtTo, moneyBagContractTo;
        moneyBagDebtTo = 0;
        moneyBagContractTo = 0;
        Distributor distributorDebtTo = distributorList.get(0);
        Distributor distributorContractTo = distributorList.get(0);
        for (Consumer consumer : consumerList) {
            if (consumer.getDebtTo() != 0 && consumer.getContractTo() != 0
                    && !consumer.isBankrupt()) {
                if (consumer.getDebtTo() != consumer.getContractTo()) {
                    for (Distributor distributor : distributorList) {
                        if (consumer.getContractTo() == distributor.getId()
                                && !distributor.isBankrupt()) {
                            distributorContractTo = distributor;
                            moneyBagContractTo = moneyForDistributors.get(distributor.getId());
                        }
                        if (consumer.getDebtTo() == distributor.getId()
                                && !distributor.isBankrupt()) {
                            distributorDebtTo = distributor;
                            moneyBagDebtTo = moneyForDistributors.get(distributor.getId());
                        }
                    }
                    if (consumer.getBudget()
                            >= consumer.getDebt()
                            + mapClientBills.get(consumer.getId())) {
                        moneyBagDebtTo = moneyBagDebtTo + consumer.getDebt();
                        moneyBagContractTo = moneyBagContractTo
                                + mapClientBills.get(consumer.getId());
                        moneyForDistributors
                                .replace(distributorContractTo.getId(), moneyBagContractTo);
                        moneyForDistributors.replace(distributorDebtTo.getId(), moneyBagDebtTo);
                        consumer.setBudget(consumer.getBudget() - consumer.getDebt()
                                + mapClientBills.get(consumer.getId()));
                    } else {
                        consumer.setBankrupt(true);
                    }
                }
            }
        }
        return moneyForDistributors;
    }

    /**
     *functie care completeaza functia getMoneyFromConsumers, ea aducand sumele de bani pe care
     * trebuie sa le primeasca distribuitorii de la clienti
     * @param moneyForDistributors hashMap cu banii care trebuie distribuitorii sa ii primeasca
     * @param distributorList lista distribuitorilor
     */
    public void giveMoneyToDistributors(final HashMap<Long, Long> moneyForDistributors,
                                        final List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            if (moneyForDistributors.get(distributor.getId()) != null) {
                distributor.setBudget(
                        distributor.getBudget() + moneyForDistributors.get(distributor.getId()));
            }
        }
    }

    /**
     * functie care calculeaza ce suma de bani trebuie sa plateasca un distribuitor pe luna
     * @param distributorList lista distribuitorilor
     */
    public void monthlyBillDistributors(final List<Distributor> distributorList) {
        Formulas formulas = new Formulas();
        for (Distributor distributor : distributorList) {
            if (!distributor.isBankrupt()) {
                distributor.setBudget(
                        distributor.getBudget() - formulas.getMonthlyDistributorBill(distributor));
            }
        }
    }

    /**
     * functie care verifica ce distribuitor au devenit falimentati, daca acestia sunt falimentati,
     * scoateam toti consumatorii din listele acestora si stergem datoriile pe care le pot avea
     * consumatorii la distribuitorul falimentat
     * @param consumerList lista de consumatori
     * @param distributorList lista distribuitorilor
     */
    public void checkBankruptDistributors(final List<Consumer> consumerList,
                                          final List<Distributor> distributorList) {
        for (Distributor distributor : distributorList) {
            if (distributor.getBudget() < 0) {
                distributor.setBankrupt(true);
                for (Consumer consumer : distributor.getContracts()) {
                    if (consumer.getDebtTo() == distributor.getId()) {
                        consumer.setDebt(0);
                        consumer.setDebtTo(0);
                    }
                    consumer.setContractTo(0);
                    consumer.setRemainedContractMonths(0);
                }
                for (Consumer consumer : consumerList) {
                    if (consumer.getDebtTo() == distributor.getId()) {
                        consumer.setDebtTo(0);
                        consumer.setDebt(0);
                    }
                }
            }
        }
    }

    /**
     * functie care decrementeaza numarul de luni ramase din contracul consumatorilor
     * @param consumerList lista consumatorilor
     */
    public void decrementContractMonths(final List<Consumer> consumerList) {
        for (Consumer consumer : consumerList) {
            if (consumer.getRemainedContractMonths() > 0) {
                consumer.setRemainedContractMonths(consumer.getRemainedContractMonths() - 1);
            }
        }
    }

    public void monthlyDistributorChanges(final List<DistributorChanges> distributorChanges,
                                   final List<Distributor> distributorList) {
        for (DistributorChanges changes : distributorChanges) {
            for (Distributor distributor : distributorList) {
                if (changes.getId() == distributor.getId()) {
                    distributor.setInfrastructureCost(changes.getInfraStructureCost());
                }
            }
        }
    }

    public void monthlyProducerChanges(final List<ProducerChanges> producerChanges,
                                       final List<Producer> producerList) {
        for(ProducerChanges changes : producerChanges){
            for(Producer producer : producerList){
                if(changes.getId() == producer.getId()) {
                    producer.setEnergyPerDistributor(changes.getEnergyPerDistributor());
                }
            }
        }
    }

    /**
     * functie care updateaza lunar lista tuturor consumatorilor
     * @param consumerList lista tuturor consumatorilor
     * @param newConsumerList lista de noi consumatori veniti
     */
    public void monthlyNewConsumers(final List<Consumer> consumerList,
                                    final List<Consumer> newConsumerList) {
        consumerList.addAll(newConsumerList);
    }

    /**
     * functie care sterge un consumator din lista de contract a unui distribuitor, daca acesta
     * a terminat numarul de luni pe baza caruia a fost realizat contractul sau daca acesta a
     * falimentat
     * @param distributorList lista distribuitorilor
     * @param consumerList lista consumatorilor
     */
    public void endContract(final List<Distributor> distributorList,
                            final List<Consumer> consumerList) {
        for (Consumer consumer : consumerList) {
            if (consumer.getRemainedContractMonths() == 0 || consumer.isBankrupt()) {
                for (Distributor distributor : distributorList) {
                    if (consumer.getContractTo() == distributor.getId()) {
                        distributor.getContracts().remove(consumer);
                    }
                }
            }
        }
    }

    /**
     * functie care populeaza hashMap-ul mapContractPrices cu preturile stabilite de distribuitori
     * la fiecare inceput de luna
     * @param distributorList lista distribuitorilor
     * @param mapContractPrices hashMap-ul care trebuie populat
     */
    public void getContractPrices(final List<Distributor> distributorList,
                                  final HashMap<Long, Long> mapContractPrices) {
        Formulas formulas = new Formulas();
        for (Distributor distributor : distributorList) {
            if (!distributor.isBankrupt()) {
                if (distributor.getContracts().size() > 0) {
                    if (mapContractPrices.get(distributor.getId()) != null) {
                        mapContractPrices.replace(distributor.getId(),
                                formulas.getFinalContract(distributor));
                    } else {
                        mapContractPrices
                                .put(distributor.getId(), formulas.getFinalContract(distributor));
                    }
                } else {
                    if (mapContractPrices.get(distributor.getId()) != null) {
                        mapContractPrices.replace(distributor.getId(),
                                formulas.getFinalContractNoClients(distributor));
                    } else {
                        mapContractPrices
                                .put(distributor.getId(),
                                        formulas.getFinalContractNoClients(distributor));
                    }
                }
            }
        }
    }

    /**
     * functie care sterge un consumator din lista de contracte a unui distribuitor doar daca acesta
     * a falimentat
     * @param distributorList lista distribuitorilor
     * @param consumerList lista consumatorilor
     */
    public void endContractOnlyIfBankrupt(final List<Distributor> distributorList,
                                          final List<Consumer> consumerList) {
        for (Consumer consumer : consumerList) {
            if (consumer.isBankrupt()) {
                for (Distributor distributor : distributorList) {
                    if (consumer.getContractTo() == distributor.getId()) {
                        distributor.getContracts().remove(consumer);
                    }
                }
            }
        }
    }

    public void addDistributorsToProducers(List<Producer> producerList, Distributor distributor, int month,
                                           long totalEnergy) {
        for(Producer producer : producerList){
            if(producer.getNumberOfDistributors() < producer.getMaxDistributors()
                    && totalEnergy < distributor.getEnergyNeeded()){
                totalEnergy += producer.getEnergyPerDistributor();
                producer.setNumberOfDistributors(producer.getNumberOfDistributors() + 1);
                distributor.addEnergyFrom(producer.getId());
                producer.getMonthlyStats().get(month).getDistributors().add(distributor);
            }
        }
    }
}

