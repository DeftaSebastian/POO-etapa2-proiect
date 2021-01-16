import actions.Actions;
import com.fasterxml.jackson.databind.ObjectMapper;
import comparingtools.SortByID;
import distributors.Distributor;
import consumers.Consumer;
import factory.Factory;
import months.DistributorChanges;
import months.MonthlyUpdates;
import months.ProducerChanges;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import outclass.OutClass;
import production.Producer;
import utils.Utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) throws Exception {
        JSONParser jsonParser = new JSONParser();
        List<Consumer> consumers = new ArrayList<>();
        List<Distributor> distributors = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        List<MonthlyUpdates> monthlyUpdatesList = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(args[0]));
            JSONObject initialData = (JSONObject) jsonObject.get("initialData");
            JSONArray jsonConsumers = (JSONArray)
                    initialData.get("consumers");
            JSONArray jsonDistributors = (JSONArray)
                    initialData.get("distributors");
            JSONArray jsonProducers = (JSONArray) initialData.get("producers");
            JSONArray monthlyUpdates =
                    (JSONArray) jsonObject.get("monthlyUpdates");
            for (Object month : monthlyUpdates) {
                ArrayList<Consumer> newConsumers = new ArrayList<>();
                ArrayList<DistributorChanges> newDistributorChanges = new ArrayList<>();
                ArrayList<ProducerChanges> newProducerChanges = new ArrayList<>();
                JSONArray newConsumer = (JSONArray) ((JSONObject) month).get("newConsumers");
                for (Object object : newConsumer) {
                    newConsumers.add(new Consumer((Long) ((JSONObject) object).get("id"),
                            (Long) ((JSONObject) object).get("initialBudget"),
                            (Long) ((JSONObject) object).get("monthlyIncome")));
                }

                JSONArray distributorChanges =
                        (JSONArray) ((JSONObject) month).get("distributorChanges");
                for (Object object : distributorChanges) {
                    newDistributorChanges
                            .add(new DistributorChanges((Long) ((JSONObject) object).get("id"),
                                    (Long) ((JSONObject) object).get("infrastructureCost")));
                }

                JSONArray producerChanges =
                        (JSONArray) ((JSONObject) month).get("producerChanges");
                for (Object object : producerChanges) {
                    newProducerChanges
                            .add(new ProducerChanges((Long) ((JSONObject) object).get("id"),
                                    (Long) ((JSONObject) object).get("energyPerDistributor")));
                }

                monthlyUpdatesList
                        .add(new MonthlyUpdates(newConsumers, newDistributorChanges,
                                newProducerChanges));
            }

            for (Object object : jsonConsumers) {
                consumers.add(new Consumer((Long) ((JSONObject) object).get("id"),
                        (Long) ((JSONObject) object).get("initialBudget"),
                        (Long) ((JSONObject) object).get("monthlyIncome")));
            }

            for (Object object : jsonDistributors) {
                distributors.add(new Distributor((Long) ((JSONObject) object).get("id"),
                        (Long) ((JSONObject) object).get("contractLength"),
                        (Long) ((JSONObject) object).get("initialBudget"),
                        (Long) ((JSONObject) object).get("initialInfrastructureCost"),
                        (Long) ((JSONObject) object).get("energyNeededKW"),
                        Utils.stringToEnergyStrategy(
                                (String) ((JSONObject) object).get("producerStrategy"))));
            }

            for (Object object : jsonProducers) {
                producers.add(new Producer((Long) ((JSONObject) object).get("id"),
                        Utils.stringToEnergyType((String) ((JSONObject) object).get("energyType")),
                        (Long) ((JSONObject) object).get("maxDistributors"),
                        (Double) ((JSONObject) object).get("priceKW"),
                        (Long) ((JSONObject) object).get("energyPerDistributor")));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        //Inceputul jocului
        int atLeastOneDistributorInTheGame;
        Actions actions = new Actions();
        HashMap<Long, Long> moneyForDistributors;
        HashMap<Long, Long> mapContractPrices = new HashMap<>();
        HashMap<Long, Long> mapClientBills = new HashMap<>();
        Distributor bestDistributor;

        //Prima runda cu datele initiale
        producers.sort(new SortByID()); //sortam crescator dupa id
        actions.addNewHistory(producers);
        actions.setStrategies(distributors);
        //actions.giveDistributorsNewProducers(producers, distributors);
        actions.findNewProducers(producers, distributors);
        actions.setProductionCostsDistributor(distributors); //setam costul de productie
        bestDistributor = actions.getBestDistributor(distributors);
        actions.getContractPrices(distributors, mapContractPrices);
        actions.makeContracts(consumers, bestDistributor, mapContractPrices, mapClientBills);
        actions.giveSalary(consumers);
        moneyForDistributors =
                actions.getMoneyFromConsumers(consumers, distributors, mapClientBills);
        actions.giveMoneyToDistributors(moneyForDistributors, distributors);
        actions.monthlyBillDistributors(distributors);
        actions.checkBankruptDistributors(consumers, distributors);
        actions.decrementContractMonths(consumers);
        //Restul de runde din joc
        for (MonthlyUpdates monthlyUpdates : monthlyUpdatesList) {

            atLeastOneDistributorInTheGame = 0;
            for (Distributor distributor : distributors) {
                if (!distributor.isBankrupt()) {
                    atLeastOneDistributorInTheGame = 1;
                }
            }
            if (atLeastOneDistributorInTheGame == 0) {
                break;
            }
            actions.addNewHistory(producers);
            actions.monthlyDistributorChanges(monthlyUpdates.getDistributorChanges(),
                    distributors);
            actions.monthlyNewConsumers(consumers,
                    monthlyUpdates.getNewConsumers());
            bestDistributor = actions.getBestDistributor(distributors);
            actions.getContractPrices(distributors, mapContractPrices);
            actions.endContract(distributors, consumers);
            actions.makeContracts(consumers, bestDistributor, mapContractPrices, mapClientBills);
            actions.giveSalary(consumers);
            actions.monthlyBillDistributors(distributors);
            moneyForDistributors =
                    actions.getMoneyFromConsumers(consumers, distributors, mapClientBills);
            actions.giveMoneyToDistributors(moneyForDistributors, distributors);
            actions.checkBankruptDistributors(consumers, distributors);
            actions.decrementContractMonths(consumers);
            actions.monthlyProducerChanges(monthlyUpdates.getProducerChanges(), producers);
            actions.findNewProducers(producers, distributors);
        }
        actions.endContractOnlyIfBankrupt(distributors, consumers);

        //output
        ObjectMapper objectMapper = new ObjectMapper();
        File outFile = new File(args[1]);
        Factory factory = Factory.getFactory();
        OutClass out =
                factory.createOutClass(consumers, distributors, producers, mapClientBills,
                        mapContractPrices);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outFile, out);
    }
}
