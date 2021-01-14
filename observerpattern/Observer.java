package observerpattern;

import production.Producer;

import java.util.List;

public interface Observer {
    void update(List<Producer> producerList, int month);
}
