package months;

public class ProducerChanges {
    private long id;
    private long energyPerDistributor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public ProducerChanges(long id, long energyPerDistributor) {
        this.id = id;
        this.energyPerDistributor = energyPerDistributor;
    }
}
