package smilecra.hypertext.managers;

import com.fasterxml.jackson.annotation.JsonView;
import smilecra.hypertext.*;
import smilecra.hypertext.persons.Person;
import smilecra.hypertext.persons.PersonCollection;
import smilecra.hypertext.persons.PersonResource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

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
    public ManagerCollection listManagers() {
        Collection<Manager> managers = service.getManagers();
        ManagerCollection managerCollection = new ManagerCollection(managers);
        addLinks(managerCollection);

        return managerCollection;
    }

    @JsonView(Views.Managers.class)
    @GET
    @Path("{id}")
    public Optional<Manager> getManager(@PathParam("id") Integer id) {
        Optional<Manager> manager = service.getManager(id);
        manager.ifPresent(this::addLinks);
        return manager;
    }

    @JsonView(Views.SubOrdinates.class)
    @GET
    @Path("{id}/subordinates")
    public Optional<PersonCollection> getSubOrdinates(@PathParam("id") Integer id) {
        Optional<Manager> manager = service.getManager(id);
        Optional<PersonCollection> subOrdinates = manager.map(Manager::getSubOrdinates);
        subOrdinates.ifPresent(col -> addLinks(manager.get().getId(), col));
        return subOrdinates;
    }

    private void addLinks(Integer managerId, PersonCollection personCollection) {
        URI self = UriBuilder.fromResource(ManagerResource.class)
                .path(ManagerResource.class, "getSubOrdinates")
                .build(managerId);
        personCollection.setSelf(self);
        personCollection.getPersons().forEach(this::addLinks);
    }

    private void addLinks(ManagerCollection managerCollection) {
        URI self = UriBuilder.fromResource(ManagerResource.class)
                .build();
        managerCollection.setSelf(self);
        managerCollection.getManagers().forEach(this::addLinks);
    }

    private void addLinks(Manager manager) {
        URI self = UriBuilder.fromResource(ManagerResource.class)
                .path(ManagerResource.class, "getManager")
                .build(manager.getId());
        manager.setSelf(self);

        addLinks(manager.getManager());
        addLinks(manager.getId(), manager.getSubOrdinates());
    }

    private void addLinks(Person person) {
        URI personSelf = UriBuilder.fromResource(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(person.getId());

        person.setSelf(personSelf);
    }

}
