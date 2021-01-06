package distributors;

import consumers.Consumer;
import observerPattern.Observer;
import production.Producer;
import strategies.EnergyChoiceStrategyType;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import strategies.Strategy;

import java.util.ArrayList;

public final class Distributor implements Observer {
    private long id;
    private long contractLength;
    private long initialBudget;
    private long initialInfrastructureCost;
    private long initialProductionCost;
    private long energyNeeded;
    private EnergyChoiceStrategyType energyChoiceStrategyType;
    private boolean isBankrupt = false;
    private ArrayList<Consumer> contracts = new ArrayList<>();
    private ArrayList<Long> energyFrom = new ArrayList<>();
    private Strategy strategy;
    private ArrayList<Producer> producers = new ArrayList<>();

    public ArrayList<Long> getEnergyFrom() {
        return energyFrom;
    }

    public void setEnergyFrom(ArrayList<Long> energyFrom) {
        this.energyFrom = energyFrom;
    }

    public void addEnergyFrom(Long id){
        this.energyFrom.add(id);
    }

    public long getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(long initialBudget) {
        this.initialBudget = initialBudget;
    }

    public long getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(long initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public long getInitialProductionCost() {
        return initialProductionCost;
    }

    public void setInitialProductionCost(long initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    public long getEnergyNeeded() {
        return energyNeeded;
    }

    public void setEnergyNeeded(long energyNeeded) {
        this.energyNeeded = energyNeeded;
    }

    public EnergyChoiceStrategyType getEnergyChoiceStrategyType() {
        return energyChoiceStrategyType;
    }

    public void setEnergyChoiceStrategyType(EnergyChoiceStrategyType energyChoiceStrategyType) {
        this.energyChoiceStrategyType = energyChoiceStrategyType;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getContractLength() {
        return contractLength;
    }

    public void setContractLength(final long contractLength) {
        this.contractLength = contractLength;
    }

    public long getBudget() {
        return initialBudget;
    }

    public Distributor(long id, long contractLength, long initialBudget,
                       long initialInfrastructureCost,
                       long energyNeeded, EnergyChoiceStrategyType energyChoiceStrategyType) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.energyNeeded = energyNeeded;
        this.energyChoiceStrategyType = energyChoiceStrategyType;
    }

    public void setBudget(final long budget) {
        this.initialBudget = budget;
    }

    public long getInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInfrastructureCost(final long infrastructureCost) {
        this.initialInfrastructureCost = infrastructureCost;
    }

    public long getProductionCost() {
        return initialProductionCost;
    }

    public void setProductionCost(final long productionCost) {
        this.initialProductionCost = productionCost;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Consumer> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Consumer> contracts) {
        this.contracts = contracts;
    }

    public void setStrategy(){
        if(energyChoiceStrategyType.equals("GREEN"))
            strategy = new GreenStrategy();
        if(energyChoiceStrategyType.equals("PRICE"))
            strategy = new PriceStrategy();
        if(energyChoiceStrategyType.equals("QUANTITY"))
            strategy = new QuantityStrategy();
    }

    @Override
    public void update(int month) {
        strategy.Strategy(producers, this, month);
    }
}
