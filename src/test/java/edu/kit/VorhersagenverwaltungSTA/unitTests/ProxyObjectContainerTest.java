package edu.kit.VorhersagenverwaltungSTA.unitTests;

import edu.kit.VorhersagenverwaltungSTA.model.core.CacheProxyObjectContainer;
import edu.kit.VorhersagenverwaltungSTA.model.core.ObjectContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProxyObjectContainerTest {
    private static final int CAPACITY = 5;
    private ObjectContainer<Integer, String> objectContainer;

    @BeforeEach
    void setup() {
        this.objectContainer = new CacheProxyObjectContainer<>(CAPACITY);
    }

    @Test
    void testFillWithObjects() {
        for (int i = 0; i < CAPACITY; i++) {
            this.objectContainer.add(i, String.valueOf(i));
        }

        for (int i = 0; i < CAPACITY; i++) {
            Assertions.assertEquals(String.valueOf(i), this.objectContainer.get(i));
        }
    }

    @Test
    void testReplacement() {
        for (int i = 0; i < CAPACITY + 1; i++) {
            this.objectContainer.add(i, String.valueOf(i));
        }

        Assertions.assertNull(this.objectContainer.get(0));

        for (int i = 1; i < CAPACITY + 1; i++) {
            Assertions.assertEquals(String.valueOf(i), this.objectContainer.get(i));
        }
    }

    @Test
    void testReplacementAfterGet() {
        for (int i = 0; i < CAPACITY / 2; i++) {
            this.objectContainer.add(i, String.valueOf(i));
        }

        this.objectContainer.get(0);

        for (int i = CAPACITY / 2; i < CAPACITY + 1; i++) {
            this.objectContainer.add(i, String.valueOf(i));
        }

        for (int i = 0; i < CAPACITY + 1; i++) {
            if (i == 1) Assertions.assertNull(this.objectContainer.get(i));
            else Assertions.assertEquals(String.valueOf(i), this.objectContainer.get(i));
        }
    }
}