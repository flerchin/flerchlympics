package dev.flerchinger.flerchlympics;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TeamsController {

    private List<Member> members;

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<List<String>> teams(
            @RequestParam(value = "numTeams", required = false) Integer numTeams,
            @RequestParam(value = "teamSize", required = false) Integer teamSize,
            @RequestParam(value = "skippy", required = false) String personToSkip
            ) {
        handleSkip(personToSkip);
        if (null != numTeams) {
            return getTeamsByNum(numTeams);
        } else if (null != teamSize ){
            return getTeamsbySize(teamSize);
        } else {
            return new ArrayList<>();
        }
    }

    private void handleSkip(String personToSkip) {
        members = Family.members;
        if (null != personToSkip) {
            members = members.stream().filter(member -> !member.firstName.equals(personToSkip)).collect(Collectors.toList());
        }
    }

    private List<List<String>> getTeamsbySize(Integer teamSize) {
        List<List<String>> teams = new ArrayList<>();
        Collections.shuffle(members);
        int membersLeft = members.size()-1;
        while (membersLeft >= 0) {
            List<String> curTeam = new ArrayList<>();
            while (curTeam.size()< teamSize && membersLeft > -1) {
                curTeam.add(members.get(membersLeft).firstName);
                membersLeft --;
            }
            teams.add(curTeam);
        }
        return teams;
    }

    private List<List<String>> getTeamsByNum(Integer numTeams) {

        List<List<String>> teams = new ArrayList<>();
        IntStream.range(0, numTeams).forEach(i -> {
            teams.add(new ArrayList<>());
        });

        Collections.shuffle(members);
        List<String> memberList = members.stream().map(m -> m.firstName).collect(Collectors.toList());
        while (memberList.size()> 0) {
            int currTeam = memberList.size() % numTeams;
            String member = memberList.iterator().next();
            teams.get(currTeam).add(member);
            memberList.remove(member);
        }
        return teams;
    }
}
