package utils;

import entities.EnergyType;
import strategies.EnergyChoiceStrategyType;

public class Utils {

    private Utils() {
    }

    public static EnergyChoiceStrategyType stringToEnergyStrategy(final String strategyType) {
        return switch (strategyType) {
            case "GREEN" -> EnergyChoiceStrategyType.GREEN;
            case "PRICE" -> EnergyChoiceStrategyType.PRICE;
            case "QUANTITY" -> EnergyChoiceStrategyType.QUANTITY;
            default -> null;
        };
    }

    public static EnergyType stringToEnergyType(final String energyType){
        return switch (energyType){
            case "WIND" -> EnergyType.WIND;
            case "SOLAR" -> EnergyType.SOLAR;
            case "HYDRO" -> EnergyType.HYDRO;
            case "COAL" -> EnergyType.COAL;
            case "NUCLEAR" -> EnergyType.NUCLEAR;
            default -> null;
        };
    }
}
