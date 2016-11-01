package smilecra.hypertext;

import com.fasterxml.jackson.databind.MapperFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ManagerApplication extends Application<ManagerConfiguration> {

    @Override
    public void run(ManagerConfiguration configuration, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new ManagerModule());

        environment.getObjectMapper().configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);

        environment.jersey().register(injector.getInstance(PersonResource.class));
        environment.jersey().register(injector.getInstance(ManagerResource.class));
    }

    public static void main(String[] args) throws Exception {
        new ManagerApplication().run(args);
    }

}
