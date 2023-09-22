package pro.sky.telegrambot.model.animals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.sky.telegrambot.model.shelters.DogShelter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dogs")
public class Dog extends Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shelter_dog_id")
    private DogShelter dogShelter;

    public Dog(String name, int age, String breed) {
        super(name, age, breed);
    }

    public Dog() {

    }


    public long getId() {
        return id;
    }

    public DogShelter getDogShelter() {
        return dogShelter;
    }

    public void setDogShelter(DogShelter dogShelter) {
        this.dogShelter = dogShelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return id == dog.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                super.toString() +
                ", dogShelter=" + dogShelter +
                '}';
    }
}
