package outclass;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"month", "distributorsIds"})
public final class HistoryOut {
    private long month;
    private ArrayList<Long> distributorsIds = new ArrayList<>();

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public ArrayList<Long> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Long> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
