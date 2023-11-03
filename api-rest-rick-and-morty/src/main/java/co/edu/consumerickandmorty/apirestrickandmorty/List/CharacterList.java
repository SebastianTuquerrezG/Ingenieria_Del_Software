package co.edu.consumerickandmorty.apirestrickandmorty.List;

import co.edu.consumerickandmorty.apirestrickandmorty.model.Characters;
import co.edu.consumerickandmorty.apirestrickandmorty.model.Info;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CharacterList {
    @NonNull
    private List<Characters> results = new ArrayList<>();
    private Info info;
}
