package org.bobatrackr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dburban on 2/3/15.
 */
@Entity
@XmlRootElement(name = "location")
public class LocationEntry {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    public LocationEntry() {
    }

    public LocationEntry(long id, String b, String c) {
        this.id = id;
        this.name = b;
        this.address = c;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
