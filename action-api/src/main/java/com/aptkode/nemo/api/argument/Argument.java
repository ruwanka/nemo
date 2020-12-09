package com.aptkode.nemo.api.argument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public abstract class Argument<V> {
    private String key;
    private V value;

    public Argument() {
        // to allow subclasses have default constructor
    }

    @JsonCreator
    public Argument(@JsonProperty("key") String key, @JsonProperty("value") V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public abstract Class<? extends Argument<V>> type();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Argument<?> argument = (Argument<?>) o;
        return Objects.equals(key, argument.key) &&
                Objects.equals(value, argument.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
