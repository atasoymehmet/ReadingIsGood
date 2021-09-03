package com.getir.readingisgood.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<S, T> implements Converter<S, T> {

    public List<T> convert(Collection<S> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
