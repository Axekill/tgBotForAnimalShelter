package pro.sky.telegrambot.model.sheters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.animals.Dog;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dog_shelter")
public class ShelterForCats extends Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @OneToMany(mappedBy = "cat_shelter")
    private List<Cat> cats;

    public ShelterForCats(String nameShelter, String address, LocalTime workingHours) {
        super(nameShelter, address, workingHours);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelterForCats that = (ShelterForCats) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShelterForCats{" +
                "id=" + id +
                super.toString()+
                ", cats=" + cats +
                '}';
    }
}
