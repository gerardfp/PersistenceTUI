package com.company;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person {
    Integer personid;
    String name;
    Stream<Thing> things;

    Person(String name) {
        this.name = name;
    }

    Person(Integer personid, String name) {
        this.personid = personid;
        this.name = name;
    }

    static Stream<String> toMaster(Person p) {
        return Stream.of(new StringJoiner(", ")
                .add("personid=\33[34m" + p.personid + "\33[0m")
                .add("name='\33[34m" + p.name + "\33[0m'")
                .toString());
    }

    static Stream<String> toDetail(Person p) {
        return Stream.of(new StringJoiner(", ", Person.class.getSimpleName() + ": ", "")
                .add("personid=\33[34m" + p.personid + "\33[0m")
                .add("name='\33[34m" + p.name + "\33[0m'").toString(),
                "\t" + p.things.flatMap(Thing::toDetail).collect(Collectors.joining("\n\t")));
    }
}
