package strategies;

import distributors.Distributor;
import production.Producer;

import java.util.List;

public interface Strategy {

    public void Strategy(List<Producer> producerList, Distributor distributor, int month);

}
