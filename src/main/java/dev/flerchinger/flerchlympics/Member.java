package dev.flerchinger.flerchlympics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Member {
    String firstName;
    String lastName;

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Member(String firstName) {
        this.firstName = firstName;
        this.lastName = "Flerchinger";
    }
}
