package smilecra.hypertext.persons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import smilecra.hypertext.Views;

import java.net.URI;

public class Person {

    private Integer id;
    private URI self;
    private String name;
    private int age;

    public Person(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView({Views.ListPersons.class, Views.Managers.class, Views.SubOrdinates.class})
    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @JsonView({Views.ListPersons.class, Views.Managers.class, Views.SubOrdinates.class})
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
