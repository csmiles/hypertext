package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;

import java.net.URI;
import java.util.Collection;

public class Manager {

    private URI self;
    private Integer id;
    private Person manager;
    private URI subOrdinatesLink;
    private Collection<Person> subOrdinates;

    public Manager(Integer id, Person manager, Person... subOrdinates) {
        this.id = id;
        this.manager = manager;
        this.subOrdinates = Lists.newArrayList(subOrdinates);
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
    @JsonProperty("subOrdinates")
    public URI getSubOrdinatesLink() {
        return subOrdinatesLink;
    }

    public void setSubOrdinatesLink(URI subOrdinatesLink) {
        this.subOrdinatesLink = subOrdinatesLink;
    }

    public Collection<Person> getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(Collection<Person> subOrdinates) {
        this.subOrdinates = subOrdinates;
    }
}
