package me.dri.Catvie.infra.utils;

import me.dri.Catvie.infra.interfaces.IDozerMapper;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DozerMapperImpl implements IDozerMapper {

    private DozerBeanMapper mapper;

    @Autowired
    public DozerMapperImpl(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T map(Object source, Class<T> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }
}
