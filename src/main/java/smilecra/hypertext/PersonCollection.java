package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonView;

import java.net.URI;
import java.util.Collection;

public class PersonCollection {

    private URI self;
    private Collection<Person> persons;

    public PersonCollection(Collection<Person> persons) {
        this.persons = persons;
    }

    @JsonView({Views.Managers.class, Views.SubOrdinates.class, Views.ListPersons.class})
    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @JsonView({Views.SubOrdinates.class, Views.ListPersons.class})
    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
}
