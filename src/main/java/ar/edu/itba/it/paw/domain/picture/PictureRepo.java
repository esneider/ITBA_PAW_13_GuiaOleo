package ar.edu.itba.it.paw.domain.picture;


public interface PictureRepo {

    /**
     * Returns a single Picture by its id
     */
    public Picture getPictureById(int id);

    /**
     * Saves a picture
     */
    public void save(Picture p);
}

