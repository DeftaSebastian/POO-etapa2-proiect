package strategies;

import inclasses.Distributor;
import inclasses.Producer;

import java.util.List;

public interface Strategy {

    /**
     * functie care ordoneaza o lista de producatori in ordinea stabilita de distribuitorul primit
     * ca parametru si apoi adauga producatorii ca surse de energie pentru distribuitor
     * @param producerList lista de producatori care trebuie sa fie ordonata
     * @param distributor distribuitorul pentru care ordonam lista
     * @param month luna in care se intampla aceasta metoda
     */
    void method(List<Producer> producerList, Distributor distributor, int month);

}
