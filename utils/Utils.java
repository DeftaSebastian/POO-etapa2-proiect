package utils;

import entities.EnergyType;
import strategies.EnergyChoiceStrategyType;

public final class Utils {

    private Utils() {
    }

    /**
     * functie care intoarce o constanta in functie de string-ul dat ca parametru
     * @param strategyType string-ul dat ca parametru
     * @return constanta gasita
     */
    public static EnergyChoiceStrategyType stringToEnergyStrategy(final String strategyType) {
        return switch (strategyType) {
            case "GREEN" -> EnergyChoiceStrategyType.GREEN;
            case "PRICE" -> EnergyChoiceStrategyType.PRICE;
            case "QUANTITY" -> EnergyChoiceStrategyType.QUANTITY;
            default -> null;
        };
    }

    /**
     * functie care intoarce o constanta in functie de string-ul primit ca parametru
     * @param energyType string primit ca parametru
     * @return intoarce constanta gasita
     */
    public static EnergyType stringToEnergyType(final String energyType) {
        return switch (energyType) {
            case "WIND" -> EnergyType.WIND;
            case "SOLAR" -> EnergyType.SOLAR;
            case "HYDRO" -> EnergyType.HYDRO;
            case "COAL" -> EnergyType.COAL;
            case "NUCLEAR" -> EnergyType.NUCLEAR;
            default -> null;
        };
    }
}
