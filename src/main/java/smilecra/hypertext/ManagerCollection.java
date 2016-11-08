package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonView;

import java.net.URI;
import java.util.Collection;

public class ManagerCollection {

    private URI self;
    private Collection<Manager> managers;

    public ManagerCollection(Collection<Manager> managers) {
        this.managers = managers;
    }

    @JsonView(Views.Managers.class)
    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @JsonView(Views.Managers.class)
    public Collection<Manager> getManagers() {
        return managers;
    }

    public void setManagers(Collection<Manager> managers) {
        this.managers = managers;
    }

}
