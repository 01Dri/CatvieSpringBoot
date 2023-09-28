package me.dri.Catvie.infra.interfaces;

public interface IDozerMapper {

    <T> T map(Object source, Class<T> destinationClass);
}
