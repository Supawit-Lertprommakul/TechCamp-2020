package com.digitalacademy.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private final Person person = new Person("Noon","Bow","Jane",18);

    // To define that a certain method is a test method, annotate it with the @Test annotation.
    @Test
    void testGetPerson() {
        assertEquals("Noon",person.getFirstName());
        assertEquals("Bow",person.getLastName());
        assertEquals("Jane",person.getNickname());
        assertEquals(18,person.getAge().intValue());

        assertAll("person",
                () -> assertEquals("Noon",person.getFirstName()),
                () -> assertEquals("Bow",person.getLastName()),
                () -> assertEquals("Jane",person.getNickname())
        );

        String firstName = person.getFirstName();
        assertNotNull(firstName);

        assertAll("first name",
                () -> assertTrue(firstName.startsWith("N")),
                () -> assertTrue(firstName.endsWith("n"))
        );
    }

    @Test
    void testNewPersonObject() {
        Person person = new Person();
        person.setFirstName("Michael");
        person.setLastName("Mick");
        person.setNickname("Chan");

        String firstName = person.getFirstName();

        assertAll("first name",
                () -> assertTrue(firstName.startsWith("M")),
                () -> assertTrue(firstName.endsWith("l"))
        );
    }

}
