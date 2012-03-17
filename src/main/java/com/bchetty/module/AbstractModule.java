package com.bchetty.module;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractModule implements Module {

    private Map<Class<?>, Class<?>> classMap = new HashMap<Class<?>, Class<?>>();

    public abstract void configure();

     <T> void createMapping(Class<T> baseClass, Class<? extends T> subClass) {
        classMap.put(baseClass, subClass.asSubclass(baseClass));
    }

    public <T> Class<? extends T> getMapping(Class<T> type) {
        Class<?> implementation = classMap.get(type);

        if (implementation == null) {
            throw new IllegalArgumentException("Couldn't find the mapping for : " + type);
        }

        return implementation.asSubclass(type);
    }
}
