package smilecra.hypertext;

import com.google.inject.AbstractModule;
import smilecra.hypertext.persons.PersonResource;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PersonResource.class);
    }

}
