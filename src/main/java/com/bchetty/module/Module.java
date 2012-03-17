package com.bchetty.module;

public interface Module {

    void configure();

     <T> Class<? extends T> getMapping(Class<T> type);
}