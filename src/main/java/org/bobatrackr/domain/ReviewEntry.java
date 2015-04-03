package org.bobatrackr.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by dburban on 2/14/2015.
 */
@Entity
@XmlRootElement(name = "review")
public class ReviewEntry {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
//    @NotNull
    private LocationEntry locationEntry;

    @Column
    @NotNull
    private int rating;

    @Column
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocationEntry getLocationEntry() {
        return locationEntry;
    }

    public void setLocationEntry(LocationEntry locationEntry) {
        this.locationEntry = locationEntry;
    }
}
