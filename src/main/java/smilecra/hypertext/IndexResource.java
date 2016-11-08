package smilecra.hypertext;

import smilecra.hypertext.managers.ManagerResource;
import smilecra.hypertext.persons.PersonResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

    @GET
    public Map<String, URI> getIndex() {
        Map<String, URI> index = new HashMap<>();

        URI managerResource = UriBuilder.fromResource(ManagerResource.class)
                .build();
        index.put("managers", managerResource);

        URI personResource = UriBuilder.fromResource(PersonResource.class)
                .build();
        index.put("persons", personResource);

        return index;
    }

}
