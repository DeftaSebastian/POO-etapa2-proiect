package outclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "energyNeededKW", "contractCost", "budget", "producerStrategy",
        "isBankrupt", "contracts"})
public final class DistribuitorOut {
    private long id;
    private long energyNeededKW;
    private long contractCost;
    private long budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private List<ConsumerForDistributor> contracts = new ArrayList<>();

    public DistribuitorOut(long id, long energyNeededKW, long contractCost, long budget,
                           String producerStrategy, boolean isBankrupt) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }

    @JsonGetter("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public List<ConsumerForDistributor> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<ConsumerForDistributor> contracts) {
        this.contracts = contracts;
    }

    public long getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(long energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public long getContractCost() {
        return contractCost;
    }

    public void setContractCost(long contractCost) {
        this.contractCost = contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public void setContracts(List<ConsumerForDistributor> contracts) {
        this.contracts = contracts;
    }
}

