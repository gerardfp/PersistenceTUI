package com.company;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Thing {
    Integer thingid;
    String title;
    LocalDateTime dateTime;
    Integer personid;

    Thing(Integer thingid, String title) {
        this.thingid = thingid;
        this.title = title;
    }

    Thing(String title, Integer personid) {
        this.title = title;
        this.personid = personid;
    }

    Stream<String> toDetail() {
        return Stream.of(new StringJoiner(", ", Thing.class.getSimpleName() + ": ", "")
                .add("thingid=\033[36m" + thingid + "\033[0m")
                .add("title='\033[36m" + title + "\033[0m'")
                .add("dateTime=\033[36m" + dateTime + "\033[0m").toString());
    }
}
