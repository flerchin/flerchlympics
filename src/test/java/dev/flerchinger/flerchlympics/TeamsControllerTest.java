package dev.flerchinger.flerchlympics;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class TeamsControllerTest {

    @Test
    void teamsBySize() {
        TeamsController teamsController = new TeamsController();
        assertEquals(teamsController.teams(null, 2, null).size(), 9);
        assertEquals(teamsController.teams(null, 1, null).size(), 17);
        assertEquals(teamsController.teams(null, 17, null).size(), 1);
    }

    @Test
    void teamsBySizeWithoutMom() {
        TeamsController teamsController = new TeamsController();
        assertEquals(teamsController.teams(null, 2, "Debbie").size(), 8);
    }

    @Test
    void teamsByNumWithoutMom() {
        TeamsController teamsController = new TeamsController();
        List<List<String>> teams = teamsController.teams(4, null, "Debbie");
        assertEquals(teams.size(), 4);
        teams.forEach(team ->{
            assertEquals(team.size(), 4);
            assertFalse(team.contains("Debbie"));
        });
    }

    @Test
    void teamsByNum() {
        TeamsController teamsController = new TeamsController();
        IntStream.range(1,5).forEach(numTeams ->{
            assertEquals(numTeams, teamsController.teams(numTeams, null, null).size());
        });
    }
    @Test
    void teamsByNumShouldHaveAllMembersUnique() {
        TeamsController teamsController = new TeamsController();
        Set<String> members = teamsController.teams(4, null, null).stream().flatMap(List::stream).collect(Collectors.toSet());
        assertEquals(Family.members.size(), members.size());
    }


}
