package observerPattern;

import distributors.Distributor;
import production.Producer;

import java.util.List;

public interface Observer {
    public void update(List<Producer> producerList, Distributor distributor, int month);
}