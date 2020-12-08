package org.springframework.samples.petclinic.model;

import java.util.List;

public class OwnerListContainer {
    private List<Owner> owners;

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
}
