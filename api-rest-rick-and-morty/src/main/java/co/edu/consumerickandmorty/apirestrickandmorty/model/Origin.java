package co.edu.consumerickandmorty.apirestrickandmorty.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Origin {
    @NonNull
    private String name;
    @NonNull
    private String url;
}
