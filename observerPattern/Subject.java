package observerPattern;

import production.Producer;

import java.util.List;

public interface Subject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver(List<Producer> producerList);
}
