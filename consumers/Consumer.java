package consumers;

public final class Consumer {
    private long id;
    private long budget;
    private long monthlyIncome;
    private boolean isBankrupt = false;
    private long remainedContractMonths;
    private long debt = 0;
    private long debtTo;
    private long contractTo;

    public long getDebt() {
        return debt;
    }

    public void setDebt(final long debt) {
        this.debt = debt;
    }

    public long getDebtTo() {
        return debtTo;
    }

    public void setDebtTo(final long debtTo) {
        this.debtTo = debtTo;
    }

    public long getContractTo() {
        return contractTo;
    }

    public void setContractTo(final long contractTo) {
        this.contractTo = contractTo;
    }

    public Consumer(final long id, final long initialBudget, final long monthlyIncome) {
        this.id = id;
        this.budget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(final long initialBudget) {
        this.budget = initialBudget;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public long getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final long remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}

