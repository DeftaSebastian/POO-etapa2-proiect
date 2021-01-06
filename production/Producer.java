package production;

import entities.EnergyType;
import observerPattern.Observer;
import observerPattern.Subject;

import java.util.ArrayList;

public class Producer implements Subject {
    private long id;
    private EnergyType energyType;
    private long maxDistributors;
    private long priceKW;
    private long energyPerDistributor;
    private long numberOfDistributors = 0;
    private ArrayList<History> monthlyStats = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();

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

    public long getPriceKW() {
        return priceKW;
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

    public Producer(long id, EnergyType energyType, long maxDistributors, long priceKW,
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
    }

    @Override
    public void unregister(Observer deleteObserver) {
        observers.remove(observers.indexOf(deleteObserver));
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers){
            observer.update(monthlyStats.size());
        }
    }
}
