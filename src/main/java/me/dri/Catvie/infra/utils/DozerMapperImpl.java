package me.dri.Catvie.infra.utils;

import me.dri.Catvie.domain.ports.interfaces.DozerMapperPort;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DozerMapperImpl implements DozerMapperPort {

    private DozerBeanMapper mapper;

    @Autowired
    public DozerMapperImpl(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }


    // Here, i am using DozerMapper for convert DTO class into a FilmEntity class
    @Override
    public <T> T map(Object source, Class<T> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

    @Override
    public <T, U> List<U> mapCollections(List<T> source, Class<U> destinationClass) {
        return source.stream()
                .map(item -> map(item , destinationClass)).collect(Collectors.toList());
    }
}
