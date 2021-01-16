package observerpattern;

import production.Producer;

import java.util.List;

public interface Subject {
    /**
     * functie care adauga un observer la lista de observeri a unui producator
     * @param o observer-ul care trebuie adaugat
     */
    void register(Observer o);

    /**
     * functie care scoate un observer din lista de observeri a unui producator
     * @param o observer-ul care trebuie sa fie scos
     */
    void unregister(Observer o);

    /**
     * functie care notifica observerii producatorilor de schimbari ale acestora
     * @param producerList lista de producatori din care trebuie sa aleaga eventual distribuitorii
     */
    void notifyObserver(List<Producer> producerList);
}
