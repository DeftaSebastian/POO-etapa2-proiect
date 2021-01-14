package observerpattern;

import production.Producer;

import java.util.List;

public interface Subject {
    void register(Observer o);

    void unregister(Observer o);

    void notifyObserver(List<Producer> producerList);
}
