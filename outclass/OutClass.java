package outclass;

import java.util.ArrayList;

public final class OutClass {
    private ArrayList<ConsumerOut> consumers = new ArrayList<>();
    private ArrayList<DistribuitorOut> distributors = new ArrayList<>();

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

