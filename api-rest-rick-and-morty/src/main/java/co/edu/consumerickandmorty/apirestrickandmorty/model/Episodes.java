package co.edu.consumerickandmorty.apirestrickandmorty.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Episodes {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String air_date;
    @NonNull
    private String episode;
    @NonNull
    private List<String> characters;
    @NonNull
    private String url;
    @NonNull
    private String created;
}
