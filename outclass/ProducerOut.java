package outclass;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import entities.EnergyType;
import production.History;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "maxDistributors", "priceKW", "energyType", "energyPerDistributor",
        "monthlyStats"})
public class ProducerOut {
    private long id;
    private long maxDistributors;
    private double priceKW;
    private String energyType;
    private long energyPerDistributor;
    private ArrayList<HistoryOut> monthlyStats = new ArrayList<>();

    public ArrayList<HistoryOut> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(ArrayList<HistoryOut> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public long getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(long energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public ProducerOut(long id, long maxDistributors, double priceKW, String energyType,
                       long energyPerDistributor) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
    }
}
