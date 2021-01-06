package months;

import consumers.Consumer;

import java.util.ArrayList;

public final class MonthlyUpdates {
    private ArrayList<Consumer> newConsumers = new ArrayList<>();
    private ArrayList<DistributorChanges> distributorChanges = new ArrayList<>();
    private ArrayList<ProducerChanges> producerChanges = new ArrayList<>();

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(ArrayList<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public MonthlyUpdates(ArrayList<Consumer> newConsumers,
                          ArrayList<DistributorChanges> distributorChanges,
                          ArrayList<ProducerChanges> producerChanges) {
        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
    }
}
