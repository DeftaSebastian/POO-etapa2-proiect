package months;

public final class DistributorChanges {
    private long id;
    private long infraStructureCost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInfraStructureCost() {
        return infraStructureCost;
    }

    public void setInfraStructureCost(long infraStructureCost) {
        this.infraStructureCost = infraStructureCost;
    }

    public DistributorChanges(long id, long infraStructureCost) {
        this.id = id;
        this.infraStructureCost = infraStructureCost;
    }
}
