package me.dri.Catvie.infra.interfaces;

import java.util.List;

public interface IDozerMapper {

    <T> T map(Object source, Class<T> destinationClass);
    <T, U>List<U> mapCollections(List<T> source, Class<U> destinationClass);
}
