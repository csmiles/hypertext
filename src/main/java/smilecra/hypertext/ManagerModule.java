package smilecra.hypertext;

import com.google.inject.AbstractModule;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PersonResource.class);
    }

}
