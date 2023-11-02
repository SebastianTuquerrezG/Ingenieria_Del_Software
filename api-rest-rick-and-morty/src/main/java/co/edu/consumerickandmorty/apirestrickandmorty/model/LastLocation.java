package co.edu.consumerickandmorty.apirestrickandmorty.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LastLocation {
    @NonNull
    private String name;
    @NonNull
    private String url;
}
