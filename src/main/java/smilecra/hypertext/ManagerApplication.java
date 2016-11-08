package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import smilecra.hypertext.managers.ManagerResource;
import smilecra.hypertext.persons.PersonResource;

public class ManagerApplication extends Application<ManagerConfiguration> {

    @Override
    public void run(ManagerConfiguration configuration, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new ManagerModule());

        environment.getObjectMapper().configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        environment.getObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        environment.jersey().register(injector.getInstance(IndexResource.class));
        environment.jersey().register(injector.getInstance(PersonResource.class));
        environment.jersey().register(injector.getInstance(ManagerResource.class));
    }

    public static void main(String[] args) throws Exception {
        new ManagerApplication().run(args);
    }

}
