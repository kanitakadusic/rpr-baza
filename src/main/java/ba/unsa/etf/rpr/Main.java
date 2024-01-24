package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void printCapital(String country) {
        CityDTO capital = GeographyDAO.getCapitalOf(country);
        System.out.println("The capital of " + country + " is " + (capital == null ? "NIL" : capital.getName()) + ".");
    }

    public static String citiesInfo() {
        ArrayList<CityDTO> cities = GeographyDAO.getCities();
        StringBuilder stringBuilder = new StringBuilder();

        for (CityDTO city : cities) {
            stringBuilder.append(city.getName()).append(" (");
            stringBuilder.append(Objects.requireNonNull(GeographyDAO.getCountryOf(city.getName())).getName());
            stringBuilder.append(") - ").append(city.getCitizens()).append('\n');
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        printCapital("France");
        System.out.println(citiesInfo());
    }
}