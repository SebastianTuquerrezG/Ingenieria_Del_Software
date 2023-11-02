package co.edu.clientserver.clientserver.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class SoccerTeam {
    @NonNull
    private String countryName;
    @NonNull
    private String conf;
    @NonNull
    private int rankingFifa;
}
