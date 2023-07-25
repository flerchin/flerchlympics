package dev.flerchinger.flerchlympics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Family {
    static List<Member> members = new ArrayList<>(
            Arrays.asList(
                    new Member("Jon"),
                    new Member("Stephanie"),
                    new Member("Ethan"),
                    new Member("Abby"),
                    new Member("Jim"),
                    new Member("Debbie"),
                    new Member("Christi", "Maroney"),
                    new Member("Jack", "Maroney"),
                    new Member("McKenna", "Maroney"),
                    new Member("Erik"),
                    new Member("Felicia"),
                    new Member("Rori"),
                    new Member("Wolfie"),
                    new Member("Dan"),
                    new Member("Aiyana"),
                    new Member("Carter"),
                    new Member("Addy")
            )
    );
}
