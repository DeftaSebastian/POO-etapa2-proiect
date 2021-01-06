package outclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public final class ConsumerOut {
    private long id;
    private boolean isBankrupt;
    private long budget;

    public ConsumerOut(final long id, final boolean isBankrupt, final long budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
    @JsonGetter("isBankrupt")
    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long budget) {
        this.budget = budget;
    }
}

