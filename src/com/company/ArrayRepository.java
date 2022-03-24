package com.company;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayRepository {
    static int personId = 0, thingId = 0;

    List<Person> personList = new ArrayList<>();
    List<Thing> thingList = new ArrayList<>();

    Stream<Person> createPerson(Person person) {
        person.personid = ++personId;
        personList.add(person);
        return Stream.of(person);
    }

    Stream<Person> readPerson(Integer personid) {
        return personList.stream()
                .filter(p -> p.personid.equals(personid))
                .peek(p -> p.things = readAllThingsByPersonid(p.personid))
                .limit(1);
    }

    Stream<Person> readAllPersons(){
        return personList.stream()
                .peek(p -> p.things = readAllThingsByPersonid(p.personid));
    }

    void updatePerson(Person person){
        personList.stream()
                .filter(p -> p.personid.equals(person.personid))
                .findFirst().ifPresent(p -> p.name = person.name);
    }

    void deletePerson(Integer personid) {
        personList.removeIf(p -> p.personid.equals(personid));
        thingList.removeIf(t -> t.personid.equals(thingId));
    }

    Stream<Thing> createThing(Thing thing) {
        thing.thingid = ++thingId;
        thing.dateTime = LocalDateTime.now();
        thingList.add(thing);
        return Stream.of(thing);
    }

    Stream<Thing> readAllThingsByPersonid(Integer personid) {
        return thingList.stream().filter(t -> t.personid.equals(personid));
    }

    void updateThing(Thing thing){
        thingList.stream()
                .filter(t -> t.thingid.equals(thing.thingid))
                .findFirst().ifPresent(t -> t.title = thing.title);
    }

    void deleteThing(Integer thingid) {
        thingList.removeIf(t -> t.thingid.equals(thingid));
    }
}
