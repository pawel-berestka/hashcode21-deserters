package com.hashcode2021.deserters.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Component
public class JavaSerializer {
    public final <T> byte[] serialize(T object) {
        if (null == object) {
            throw new IllegalArgumentException("Cannot serialize null object.");
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cannot serialize object due to: [%s].", e.getMessage()));
        }
    }
}