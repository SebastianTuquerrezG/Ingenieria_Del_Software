package co.edu.consumerickandmorty.apirestrickandmorty.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class Characters {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String status;
    @NonNull
    private String species;
    @NonNull
    private String type;
    @NonNull
    private String gender;
    @NonNull
    private Origin origin;
    @NonNull
    private LastLocation location;
    @NonNull
    private String image;
    @NonNull
    private List<String> episode;
    @NonNull
    private String url;
    @NonNull
    private String created;
}

