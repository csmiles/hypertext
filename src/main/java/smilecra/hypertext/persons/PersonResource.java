package smilecra.hypertext.persons;

import com.fasterxml.jackson.annotation.JsonView;
import smilecra.hypertext.Service;
import smilecra.hypertext.Views;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import static javax.ws.rs.core.Response.created;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final Service service;

    @Inject
    public PersonResource(Service service) {
        this.service = service;
    }

    @GET
    @JsonView(Views.ListPersons.class)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonCollection listPersons() {
        Collection<Person> ps = service.getPersons();
        PersonCollection personCollection = new PersonCollection(ps);
        addLinks(personCollection);
        return personCollection;
    }

    @GET
    @Path("{id}")
    public Optional<Person> getPerson(@PathParam("id") Integer id) {
        Optional<Person> p = service.getPerson(id);
        p.ifPresent(this::addLinks);
        return p;
    }

    @POST
    public Response createPerson(Person p) {
        service.createPerson(p);
        addLinks(p);

        return Response.created(p.getSelf())
                .entity(p)
                .build();
    }

    private void addLinks(PersonCollection personCollection) {
        URI self = UriBuilder.fromResource(PersonResource.class)
                .build();
        personCollection.setSelf(self);
        personCollection.getPersons().forEach(this::addLinks);
    }

    private void addLinks(Person p) {
        Integer id = p.getId();

        URI self = UriBuilder.fromResource(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(id);

        p.setSelf(self);
    }

}
