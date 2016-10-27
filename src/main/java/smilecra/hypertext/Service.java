package smilecra.hypertext;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class Service {

    private final Map<Integer, Person> persons = new HashMap<>();

    public Service() {
        persons.put(1, new Person(1, "Alice", 41));
        persons.put(2, new Person(2, "Bob", 27));
    }

    public Collection<Person> getPersons() {
        return persons.values();
    }


    public Optional<Person> getPerson(Integer id) {
        return Optional.ofNullable(persons.get(id));
    }
}
