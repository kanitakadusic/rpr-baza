package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

final public class GeographyDAO {
    private static Connection connection = connect();

    private static PreparedStatement getCityPS, getCountryPS, getCitiesPS, getCountriesPS, getCountryOfPS, getCapitalOfPS;
    private static PreparedStatement addCityPS, addCountryPS, updateCityPS, updateCountryPS, deleteCityPS, deleteCountryPS, beforeDeleteCityPS, beforeDeleteCountryPS;

    private static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:src/resources/rpr-geography.sqlite");
                prepareStatements();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connection;
    }

    private static void prepareStatements() throws SQLException {
        getCityPS = connection.prepareStatement("select * from cities where name = ?");
        getCountryPS = connection.prepareStatement("select * from countries where name = ?");

        getCitiesPS = connection.prepareStatement("select * from cities order by citizens desc");
        getCountriesPS = connection.prepareStatement("select * from countries order by name");

        getCountryOfPS = connection.prepareStatement("select co.* from cities ci, countries co where ci.country = co.id and ci.name = ?");
        getCapitalOfPS = connection.prepareStatement("select ci.* from cities ci, countries co where ci.country = co.id and co.name = ?");

        addCityPS = connection.prepareStatement("insert into cities (id, name, citizens, country) values (?, ?, ?, ?)");
        addCountryPS = connection.prepareStatement("insert into countries (id, name, capital) values (?, ?, ?)");

        updateCityPS = connection.prepareStatement("update cities set name = ?, citizens = ?, country = ? where id = ?");
        updateCountryPS = connection.prepareStatement("update countries set name = ?, capital = ? where id = ?");

        deleteCityPS = connection.prepareStatement("delete from cities where name = ?");
        deleteCountryPS = connection.prepareStatement("delete from countries where name = ?");

        beforeDeleteCityPS = connection.prepareStatement("update countries set capital = null where id = (select id from cities where name = ?)");
        beforeDeleteCountryPS = connection.prepareStatement("delete from cities where country = (select id from countries where name = ?)");
    }

    public static CityDTO getCity(String city) {
        try {
            getCityPS.setString(1, city);
            ResultSet result = getCityPS.executeQuery();

            result.next();
            return new CityDTO(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static CountryDTO getCountry(String country) {
        try {
            getCountryPS.setString(1, country);
            ResultSet result = getCountryPS.executeQuery();

            result.next();
            return new CountryDTO(result.getInt(1), result.getString(2), result.getInt(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<CityDTO> getCities() {
        ArrayList<CityDTO> cities = new ArrayList<>();

        try {
            ResultSet result = getCitiesPS.executeQuery();

            while (result.next())
                cities.add(new CityDTO(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return cities;
    }

    public static ArrayList<CountryDTO> getCountries() {
        ArrayList<CountryDTO> countries = new ArrayList<>();

        try {
            ResultSet result = getCountriesPS.executeQuery();

            while (result.next())
                countries.add(new CountryDTO(result.getInt(1), result.getString(2), result.getInt(3)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return countries;
    }

    public static CountryDTO getCountryOf(String city) {
        try {
            getCountryOfPS.setString(1, city);
            ResultSet result = getCountryOfPS.executeQuery();

            result.next();
            return new CountryDTO(result.getInt(1), result.getString(2), result.getInt(3));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static CityDTO getCapitalOf(String country) {
        try {
            getCapitalOfPS.setString(1, country);
            ResultSet result = getCapitalOfPS.executeQuery();

            result.next();
            return new CityDTO(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void addCity(CityDTO city) {
        try {
            addCityPS.setInt(1, city.getId());
            addCityPS.setString(2, city.getName());
            addCityPS.setInt(3, city.getCitizens());
            addCityPS.setInt(4, city.getCountry());
            addCityPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCountry(CountryDTO country) {
        try {
            addCountryPS.setInt(1, country.getId());
            addCountryPS.setString(2, country.getName());
            addCountryPS.setInt(3, country.getCapital());
            addCountryPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCity(CityDTO city) {
        try {
            updateCityPS.setString(1, city.getName());
            updateCityPS.setInt(2, city.getCitizens());
            updateCityPS.setInt(3, city.getCountry());
            updateCityPS.setInt(4, city.getId());
            updateCityPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCountry(CountryDTO country) {
        try {
            updateCountryPS.setString(1, country.getName());
            updateCountryPS.setInt(2, country.getCapital());
            updateCountryPS.setInt(3, country.getId());
            updateCountryPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCity(String city) {
        try {
            beforeDeleteCityPS.setString(1, city);
            beforeDeleteCityPS.executeUpdate();

            deleteCityPS.setString(1, city);
            deleteCityPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCountry(String country) {
        try {
            beforeDeleteCountryPS.setString(1, country);
            beforeDeleteCountryPS.executeUpdate();

            deleteCountryPS.setString(1, country);
            deleteCountryPS.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
