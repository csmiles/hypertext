package smilecra.hypertext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import java.net.URI;
import java.util.Collection;

public class Manager {

    private URI self;
    private Integer id;
    private URI manager;
    private Integer managerId;
    private Collection<URI> subOrdinates;
    private Collection<Integer> subOrdinateIds;

    public Manager(Integer id, Integer managerId, Integer... subOrdinateIds) {
        this.id = id;
        this.managerId = managerId;
        this.subOrdinateIds = Lists.newArrayList(subOrdinateIds);
    }

    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public URI getManager() {
        return manager;
    }

    public void setManager(URI manager) {
        this.manager = manager;
    }

    public Collection<Integer> getSubOrdinateIds() {
        return subOrdinateIds;
    }

    @JsonIgnore
    public void setSubOrdinateIds(Collection<Integer> subOrdinateIds) {
        this.subOrdinateIds = subOrdinateIds;
    }

    public Collection<URI> getSubOrdinates() {
        return subOrdinates;
    }

    public void setSubOrdinates(Collection<URI> subOrdinates) {
        this.subOrdinates = subOrdinates;
    }
}
