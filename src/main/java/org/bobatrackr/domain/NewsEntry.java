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
@XmlRootElement(name = "news")
public class NewsEntry {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String content;

    public NewsEntry() {
    }

    public NewsEntry(long id, String b) {
        this.id = id;
        this.content = b;
    }

    public long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}