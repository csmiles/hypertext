package smilecra.hypertext;

import smilecra.hypertext.managers.Manager;
import smilecra.hypertext.persons.Person;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class Service {

    private final Map<Integer, Person> persons = new HashMap<>();
    private final Map<Integer, Manager> managers = new HashMap<>();

    public Service() {
        Person alice = new Person(1, "Alice", 41);
        Person bob = new Person(2, "Bob", 27);
        Person charlie = new Person(3, "Charlie", 48);
        Person eve = new Person(4, "Eve", 31);

        persons.put(1, alice);
        persons.put(2, bob);
        persons.put(3, charlie);
        persons.put(4, eve);

        managers.put(1, new Manager(1, alice, bob, charlie));
    }

    public Collection<Person> getPersons() {
        return persons.values();
    }

    public Optional<Person> getPerson(Integer id) {
        return Optional.ofNullable(persons.get(id));
    }

    public Collection<Manager> getManagers() {
        return managers.values();
    }

    public Optional<Manager> getManager(Integer id) {
        return Optional.ofNullable(managers.get(id));
    }
}
