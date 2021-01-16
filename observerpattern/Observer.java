package observerpattern;

import inclasses.Producer;

import java.util.List;

public interface Observer {
    /**
     * functie care updateaza producatorii unui observer/distribuitor
     * @param producerList lista de producatori din care o sa aleaga distribuitorul
     * @param month luna in care se face update-ul
     */
    void update(List<Producer> producerList, int month);
}
