package production;

import distributors.Distributor;
import entities.EnergyType;
import observerpattern.Observer;
import observerpattern.Subject;

import java.util.ArrayList;
import java.util.List;

public final class Producer implements Subject {
    private long id;
    private EnergyType energyType;
    private long maxDistributors;
    private double priceKW;
    private long energyPerDistributor;
    private long numberOfDistributors = 0;
    private ArrayList<History> monthlyStats = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();
    private ArrayList<Distributor> distributors = new ArrayList<>();

    public ArrayList<History> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(ArrayList<History> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public long getNumberOfDistributors() {
        return numberOfDistributors;
    }

    public void setNumberOfDistributors(long numberOfDistributors) {
        this.numberOfDistributors = numberOfDistributors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public long getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(long maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public void setPriceKW(long priceKW) {
        this.priceKW = priceKW;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public Producer(long id, EnergyType energyType, long maxDistributors, Double priceKW,
                    long energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
        distributors.add((Distributor) newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        observers.remove(observers.indexOf(deleteObserver));
    }

    @Override
    public void notifyObserver(List<Producer> producerList) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(producerList, monthlyStats.size() - 1);
        }
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

}
