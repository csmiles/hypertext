package smilecra.hypertext.managers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import smilecra.hypertext.persons.Person;
import smilecra.hypertext.persons.PersonCollection;
import smilecra.hypertext.Views;

import java.net.URI;

public class Manager {

    private URI self;
    private Integer id;
    private Person manager;
    private PersonCollection subOrdinates;

    public Manager(Integer id, Person manager, Person... subOrdinates) {
        this.id = id;
        this.manager = manager;
        this.subOrdinates = new PersonCollection(Lists.newArrayList(subOrdinates));
    }

    @JsonView(Views.Managers.class)
    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(Views.Managers.class)
    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    @JsonView(Views.Managers.class)
    public PersonCollection getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(PersonCollection subOrdinates) {
        this.subOrdinates = subOrdinates;
    }
}
