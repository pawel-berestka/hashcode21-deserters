package com.hashcode2021.deserters.util;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class JavaDeserializer {
    public final <T> T deserialize(byte[] objectAsByteArray) {
        if(null == objectAsByteArray){
            throw new IllegalArgumentException("Cannot deserialize null byte[] array.");
        }

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(objectAsByteArray);
            ObjectInputStream in = new ObjectInputStream(bis);
            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(String.format("Cannot deserialize byte array to object due to: [%s].", e.getMessage()));
        }
    }
}
