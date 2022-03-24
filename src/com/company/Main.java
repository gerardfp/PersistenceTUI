package com.company;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    static ArrayRepository repository = new ArrayRepository();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
	    populate();
        startApp();
    }

    static void populate() {
        repository.createPerson(new Person("joan")).findFirst().ifPresent(p -> {
            repository.createThing(new Thing("piso", p.personid));
            repository.createThing(new Thing("casa", p.personid));
        });

        repository.createPerson(new Person("anna")).findFirst().ifPresent(p -> {
            repository.createThing(new Thing("yate", p.personid));
        });

        repository.createPerson(new Person("laia")).findFirst().ifPresent(p -> {
            repository.createThing(new Thing("boli", p.personid));
            repository.createThing(new Thing("clip", p.personid));
            repository.createThing(new Thing("bloc", p.personid));
        });
    }

    static void startApp(){
        while (true) {
            System.out.println("\33[1;30;45m--- MASTER SCREEN ---\33[0m\n");
            repository.readAllPersons().flatMap(Person::toMaster).forEach(System.out::println);
            System.out.print("\n\33[1;30;45m[PERSON] CREATE/READ/UPDATE/DELETE or QUIT:\33[0m ");
            String option = scanner.next().substring(0,1).toLowerCase(Locale.ROOT);

            if (option.equals("q")) {
                break;
            } else if (option.equals("c")){
                System.out.print("Person name: ");
                String name = scanner.next();
                repository.createPerson(new Person(name));
            } else {
                System.out.print("Person ID: ");
                int personid = scanner.nextInt();
                if (option.equals("u")) {
                    System.out.println("Person new name: ");
                    String newName = scanner.next();
                    repository.updatePerson(new Person(personid, newName));
                } else if (option.equals("d")) {
                    repository.deletePerson(personid);
                } else if (option.equals("r")) {
                    while (true) {
                        System.out.println("\33[1;30;104m--- DETAIL SCREEN ---\33[0m\n");
                        repository.readPerson(personid).flatMap(Person::toDetail).forEach(System.out::println);
                        System.out.print("\n\33[1;30;104m[THING] CREATE/UPDATE/DELETE or BACK:\33[0m ");
                        option = scanner.next().substring(0, 1).toLowerCase(Locale.ROOT);
                        if (option.equals("b")) {
                            break;
                        } else if (option.equals("c")) {
                            System.out.println("Thing title: ");
                            String title = scanner.next();
                            repository.createThing(new Thing(title, personid));
                        } else {
                            System.out.print("Thing ID: ");
                            int thingid = scanner.nextInt();

                            if (option.equals("u")) {
                                System.out.println("Thing new title: ");
                                String newTitle = scanner.next();
                                repository.updateThing(new Thing(thingid, newTitle));
                            } else if (option.equals("d")) {
                                repository.deleteThing(thingid);
                            }
                        }
                    }
                }
            }
        }
    }
}
