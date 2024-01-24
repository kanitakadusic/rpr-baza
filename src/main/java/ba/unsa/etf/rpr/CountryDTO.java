package ba.unsa.etf.rpr;

public class CountryDTO {
    private Integer id;
    private String name;
    private Integer capital;

    public CountryDTO(Integer id, String name, Integer capital) {
        this.id = id;
        this.name = name;
        this.capital = capital;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                '}';
    }
}
