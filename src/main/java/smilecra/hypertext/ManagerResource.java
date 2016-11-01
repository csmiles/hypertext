package smilecra.hypertext;

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

    @GET
    public Collection<Manager> listManagers() {
        Collection<Manager> managers = service.getManagers();
        managers.forEach(this::addLinks);
        return managers;
    }

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

        URI managerLink = generatePersonLink(manager.getManagerId());
        manager.setManager(managerLink);

        List<URI> subOrdinateLinks = manager.getSubOrdinateIds().stream()
                .map(this::generatePersonLink)
                .collect(toList());
        manager.setSubOrdinates(subOrdinateLinks);
    }

    private URI generatePersonLink(Integer id) {
        return UriBuilder.fromResource(PersonResource.class)
                .path(PersonResource.class, "getPerson")
                .build(id);
    }

}
