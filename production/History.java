package production;

import distributors.Distributor;

import java.util.ArrayList;

public class History {
    private ArrayList<Distributor> distributors = new ArrayList<>();

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }
}
