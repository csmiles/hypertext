package smilecra.hypertext;

import smilecra.hypertext.managers.Manager;
import smilecra.hypertext.persons.Person;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class Service {

    private final AtomicInteger personCount = new AtomicInteger(0);
    private final AtomicInteger managerCount = new AtomicInteger(0);

    private final Map<Integer, Person> persons = new ConcurrentHashMap<>();
    private final Map<Integer, Manager> managers = new ConcurrentHashMap<>();

    public Service() {
        Person alice = new Person(personCount.getAndIncrement(), "Alice", 41);
        Person bob = new Person(personCount.getAndIncrement(), "Bob", 27);
        Person charlie = new Person(personCount.getAndIncrement(), "Charlie", 48);
        Person eve = new Person(personCount.getAndIncrement(), "Eve", 31);

        persons.put(alice.getId(), alice);
        persons.put(bob.getId(), bob);
        persons.put(charlie.getId(), charlie);
        persons.put(eve.getId(), eve);

        Manager manager = new Manager(managerCount.getAndIncrement(), alice, bob, charlie);
        managers.put(manager.getId(), manager);
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

    public Person createPerson(Person p) {
        int id = personCount.getAndIncrement();
        p.setId(id);
        persons.put(id, p);
        return p;
    }
}
