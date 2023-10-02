package pro.sky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.sky.telegrambot.model.animals.Cat;
import pro.sky.telegrambot.model.animals.Dog;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private int numberPhone;
    private String email;
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Cat> cats;
    @JsonIgnore
    @OneToMany(mappedBy = "users")
    private List<Dog> dogs;

    public Users() {
    }

    public Users(String firstName, String lastName, int age, int numberPhone, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.numberPhone = numberPhone;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(int numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
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
        Users users = (Users) o;
        return Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", numberPhone=" + numberPhone +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", cats=" + cats +
                ", dogs=" + dogs +
                '}';
    }
}
