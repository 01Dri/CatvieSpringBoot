package me.dri.Catvie.utils;

public interface EntityModel {

    /**
     *This interface is used to uncouple the dependencies in BuilderFilm
     * That will when the class needs the objects Director and User, that  will call the methods of this class
     * that are implemented on the entities
     */

    Object getDirectorObj();
    Object getUserObj();

}
