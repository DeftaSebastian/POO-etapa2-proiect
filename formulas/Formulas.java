package formulas;

import inclasses.Consumer;
import inclasses.Distributor;
import inclasses.Producer;


public final class Formulas {
    private final double profitProduct = 0.2;
    private final double debtProduct = 1.2;
    private final double productionCostDivide = 10;

    /**
     * functie care calculeaza profitul unui distribuitor pe luna
     *
     * @param distributor distribuitorul caruia ii calculam profitul
     * @return intoarce suma calculata
     */
    public long getProfit(final Distributor distributor) {
        return Math.round(Math.floor(profitProduct * distributor.getProductionCost()));
    }

    /**
     * functie care calculeaza costul unui distribuitor
     *
     * @param distributor distribuitorul primit ca parametru
     * @return intoarce costul calculat
     */
    public double getCost(final Distributor distributor) {
        double cost = 0;
        for (Producer producer : distributor.getEnergyFrom()) {
            cost += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }
        return cost;
    }

    /**
     * functie care calculeaza si seteaza costul de productie al unui distribuitor
     *
     * @param distributor distribuitorul pentru care trebuie sa calculam costul de productie
     */
    public void setProductionCost(final Distributor distributor) {
        distributor.setProductionCost(
                Math.round(Math.floor(getCost(distributor) / productionCostDivide)));
    }

    /**
     * calculeaza pretul pe care un distribuitor o sa vrea sa il foloseasca la contract daca acesta
     * are clienti
     *
     * @param distributor distribuitorul pentru care calculam pretul de contract
     * @return intoarce suma calculata
     */
    public long getFinalContract(final Distributor distributor) {
        int numberOfClients = 0;
        for (Consumer consumer : distributor.getContracts()) {
            if (!consumer.isBankrupt()) {
                numberOfClients++;
            }
        }
        setProductionCost(distributor);
        if (numberOfClients != 0) {
            return Math.round(Math
                    .floor(distributor.getInfrastructureCost() / numberOfClients)
                    + distributor.getProductionCost() + getProfit(distributor));
        } else {
            return getFinalContractNoClients(distributor);
        }
    }

    /**
     * functie care calculeaza pretul pe care un distribuitor vrea sa il foloseasca in contract in
     * cazul in care nu are clienti
     *
     * @param distributor distribuitorul pentru care calculam pretul din contract
     * @return intoarcem suma calculata
     */
    public long getFinalContractNoClients(final Distributor distributor) {
        setProductionCost(distributor);
        return distributor.getInfrastructureCost() + distributor.getProductionCost()
                + getProfit(distributor);
    }

    /**
     * functie care calculeaza suma pe care un distribuitor trebuie sa o plateasca lunar
     *
     * @param distributor distribuitorul caruia vrem sa ii calculam cheltuielile
     * @return intoarcem suma calculata
     */
    public long getMonthlyDistributorBill(final Distributor distributor) {
        setProductionCost(distributor);
        return distributor.getInfrastructureCost()
                + distributor.getProductionCost() * distributor.getContracts().size();
    }

    /**
     * functie care calculeaza suma pe care un consumator trebuie sa o plateasca in cazul inc are
     * acesta are o datorie la un distribuitor
     *
     * @param contractPrice pretul initial al contractului
     * @return intoarcem suma calculata
     */
    public long getDebtAmount(final Long contractPrice) {
        return Math.round(Math.floor(debtProduct * contractPrice));
    }
}
