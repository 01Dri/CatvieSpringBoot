package me.dri.Catvie.domain.ports.interfaces;

import java.util.List;

public interface DozerMapperPort {

    <T> T map(Object source, Class<T> destinationClass);
    <T, U>List<U> mapCollections(List<T> source, Class<U> destinationClass);
}
