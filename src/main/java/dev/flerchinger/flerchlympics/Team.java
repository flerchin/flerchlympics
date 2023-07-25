package dev.flerchinger.flerchlympics;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;
@JsonSerialize
public class Team {
    List<Member> members;

    public Team(List<Member> members) {
        this.members = members;
    }
}
