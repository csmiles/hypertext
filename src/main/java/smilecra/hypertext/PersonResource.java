package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonView;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final Service service;

    @Inject
    public PersonResource(Service service) {
        this.service = service;
    }

    @GET
    @JsonView(Person.SummaryView.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Person> listPersons() {
        Collection<Person> ps = service.getPersons();
        ps.forEach(this::addSelf);
        return ps;
    }

    @GET
    @Path("{id}")
    public Response getPerson(@PathParam("id") Integer id) {
        Optional<Person> pOpt = service.getPerson(id);
        if (pOpt.isPresent()) {
            Person p = pOpt.get();
            addSelf(p);
            return Response.ok(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private void addSelf(Person p) {
        Integer id = p.getId();

        URI self = UriBuilder.fromResource(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(id);

        p.setSelf(self);
    }

}
