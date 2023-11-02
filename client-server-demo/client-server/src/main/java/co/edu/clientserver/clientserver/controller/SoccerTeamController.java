package co.edu.clientserver.clientserver.controller;

import co.edu.clientserver.clientserver.model.SoccerTeam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/soccer-teams")
public class SoccerTeamController {
    ArrayList<SoccerTeam> soccerTeams = new ArrayList<>(
            List.of(
                    new SoccerTeam("Uruguay", "CONMEBOL", 1),
                    new SoccerTeam("Argentina", "CONMEBOL", 2),
                    new SoccerTeam("Brasil", "CONMEBOL", 3),
                    new SoccerTeam("Francia", "UEFA", 4),
                    new SoccerTeam("España", "UEFA", 5)
            )
    );

    @GetMapping
    public List<SoccerTeam> getSoccerTeams() {
        return soccerTeams;
    }

    @GetMapping("/{countryName}")
    public SoccerTeam getSoccerTeam(@PathVariable String countryName) {
        for (SoccerTeam soccerTeam : soccerTeams) {
            if (soccerTeam.getCountryName().equalsIgnoreCase(countryName)) {
                return soccerTeam;
            }
        }
        return null;
    }

    @PostMapping
    public void addSoccerTeam(@RequestBody SoccerTeam soccerTeam) {
        soccerTeams.add(soccerTeam);
    }

    @DeleteMapping("/{countryName}")
    public String deleteSoccerTeam(@PathVariable String countryName) {
        Optional<SoccerTeam> soccerTeamOptional = soccerTeams.stream()
                .filter(soccerTeam -> soccerTeam.getCountryName().equalsIgnoreCase(countryName))
                .findFirst();
        if (soccerTeamOptional.isPresent()) {
            soccerTeams.remove(soccerTeamOptional.get());
            return "Soccer team deleted";
        } else {
            return "Soccer team not found";
        }
    }

    @PutMapping("/{countryName}")
    public void updateSoccerTeam(@RequestBody SoccerTeam soccerTeam) {
        for (SoccerTeam soccerTeamAux : soccerTeams) {
            if (soccerTeamAux.getCountryName().equalsIgnoreCase(soccerTeam.getCountryName())) {
                soccerTeamAux.setConf(soccerTeam.getConf());
                soccerTeamAux.setRankingFifa(soccerTeam.getRankingFifa());
            }
        }
    }
}
