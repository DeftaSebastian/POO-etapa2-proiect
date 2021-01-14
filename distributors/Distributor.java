package distributors;

import consumers.Consumer;
import observerPattern.Observer;
import observerPattern.Subject;
import production.Producer;
import strategies.EnergyChoiceStrategyType;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import strategies.Strategy;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Producer> energyFrom = new ArrayList<>();
    private Strategy strategy;
    private List<Producer> producers = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject newSubject) {
        subjects.add(newSubject);
    }

    private boolean hasToMove;

    public ArrayList<Producer> getEnergyFrom() {
        return energyFrom;
    }

    public void setEnergyFrom(ArrayList<Producer> energyFrom) {
        this.energyFrom = energyFrom;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void addProducer(Producer producer) {
        producers.add(producer);
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
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

    public void setStrategy() {
        if (energyChoiceStrategyType.label.equals("GREEN")) {
            strategy = new GreenStrategy();
        }
        if (energyChoiceStrategyType.label.equals("PRICE")) {
            strategy = new PriceStrategy();
        }
        if (energyChoiceStrategyType.label.equals("QUANTITY")) {
            strategy = new QuantityStrategy();
        }
    }

    public boolean hasToMove() {
        return hasToMove;
    }

    public void setHasToMove(boolean hasToMove) {
        this.hasToMove = hasToMove;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(List<Producer> producerList, int month) {
        this.setHasToMove(true);
    }
}
