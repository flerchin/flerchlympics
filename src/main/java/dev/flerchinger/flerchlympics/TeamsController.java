package dev.flerchinger.flerchlympics;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        } else {
            return getTeamsbySize(teamSize);
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
        Collections.shuffle(members);
        int teamSize = members.size()/ numTeams;
        int remainders = members.size() % numTeams;
        for (int team = 0; team < numTeams; team++) {
            int remainder = remainders > 0? 1:0;
            int currPos = team*(teamSize+remainder);
            List<String> firstNames = members.subList(currPos, currPos + teamSize+remainder)
                    .stream().map(member -> member.firstName).collect(Collectors.toList());
            teams.add(firstNames);
            remainders--;
        }
        return teams;
    }
}
