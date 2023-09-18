package pro.sky.telegrambot.model.sheters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.sky.telegrambot.model.animals.Dog;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dog_shelter")
public class DogShelter extends Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @OneToMany(mappedBy = "dog_shelter")
    private List<Dog> dogs;

    public DogShelter(String nameShelter, String address, LocalTime workingHours) {
        super(nameShelter, address, workingHours);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogShelter that = (DogShelter) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DogShelter{" +
                "id=" + id +
                super.toString()+
                ", dogs=" + dogs +
                '}';
    }
}
