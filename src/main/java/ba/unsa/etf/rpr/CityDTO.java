package ba.unsa.etf.rpr;

public class CityDTO {
    private Integer id;
    private String name;
    private Integer citizens;
    private Integer country;

    public CityDTO(Integer id, String name, Integer citizens, Integer country) {
        this.id = id;
        this.name = name;
        this.citizens = citizens;
        this.country = country;
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

    public Integer getCitizens() {
        return citizens;
    }

    public void setCitizens(Integer citizens) {
        this.citizens = citizens;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", citizens=" + citizens +
                ", country=" + country +
                '}';
    }
}
