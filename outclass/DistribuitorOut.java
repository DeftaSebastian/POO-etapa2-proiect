package outclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "budget", "isBankrupt", "contracts"})
public final class DistribuitorOut {
    private long id;
    private long budget;
    private boolean isBankrupt;
    private List<ConsumerForDistributor> contracts = new ArrayList<>();

    public DistribuitorOut(final long id, final long budget, final boolean isBankrupt) {
        this.id = id;
        this.budget = budget;
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
}

