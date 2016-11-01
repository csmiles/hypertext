package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonView;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Path("/managers")
@Produces(MediaType.APPLICATION_JSON)
public class ManagerResource {

    private final Service service;

    @Inject
    public ManagerResource(Service service) {
        this.service = service;
    }

    @JsonView(Views.Managers.class)
    @GET
    public Collection<Manager> listManagers() {
        Collection<Manager> managers = service.getManagers();
        managers.forEach(this::addLinks);
        return managers;
    }

    @JsonView(Views.Managers.class)
    @GET
    @Path("{id}")
    public Optional<Manager> getManager(@PathParam("id") Integer id) {
        Optional<Manager> manager = service.getManager(id);
        manager.ifPresent(this::addLinks);
        return manager;
    }

    private void addLinks(Manager manager) {
        URI self = UriBuilder.fromResource(ManagerResource.class)
                .path(ManagerResource.class, "getManager")
                .build(manager.getId());
        manager.setSelf(self);

        addPersonSelf(manager.getManager());
        manager.getSubOrdinates().forEach(this::addPersonSelf);
    }

    private void addPersonSelf(Person person) {
        URI personSelf = UriBuilder.fromResource(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(person.getId());

        person.setSelf(personSelf);
    }

}
