package ar.edu.itba.it.paw.domain.picture;

import javax.persistence.Basic;
import javax.persistence.Entity;

import ar.edu.itba.it.paw.domain.PersistentEntity;


@Entity
public class Picture extends PersistentEntity {

	private static final long serialVersionUID = 811873056474640618L;

	private String mime;

    @Basic
    private byte[] img;

    Picture() {}

    public Picture(byte[] img, String mime) {

        this.img = img;
        this.mime = mime;
    }

    public String getMime() {

        return mime;
    }

    public byte[] getBytes() {

        return this.img;
    }
}

