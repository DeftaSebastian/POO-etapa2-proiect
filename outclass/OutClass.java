package outclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"consumers", "distributors", "energyProducers"})
public final class OutClass {
    private ArrayList<ConsumerOut> consumers = new ArrayList<>();
    private ArrayList<DistribuitorOut> distributors = new ArrayList<>();
    private ArrayList<ProducerOut> energyProducers = new ArrayList<>();

    @JsonGetter("energyProducers")
    public ArrayList<ProducerOut> getProducers() {
        return energyProducers;
    }

    public void setProducers(ArrayList<ProducerOut> producers) {
        this.energyProducers = producers;
    }

    public ArrayList<ConsumerOut> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerOut> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistribuitorOut> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistribuitorOut> distributors) {
        this.distributors = distributors;
    }
}

