package ar.edu.itba.it.paw.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AbstractModel {

    protected final static int NO_ID = -1;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    protected AbstractModel () {

        this.id = NO_ID;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public int hashCode() {

        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        AbstractModel other = (AbstractModel) obj;

        if (id != other.id)
            return false;

        return true;
    }
}

