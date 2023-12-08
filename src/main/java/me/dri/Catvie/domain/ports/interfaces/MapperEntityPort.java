package me.dri.Catvie.domain.ports.interfaces;

public interface MapperEntityPort<T> {

    T convertEntity(T from, T to) throws NoSuchMethodException;

}
