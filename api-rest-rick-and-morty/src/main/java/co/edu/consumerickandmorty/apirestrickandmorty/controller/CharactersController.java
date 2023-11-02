package co.edu.consumerickandmorty.apirestrickandmorty.controller;

import co.edu.consumerickandmorty.apirestrickandmorty.List.CharacterList;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {
    private final String url = "https://rickandmortyapi.com/api/character";

    @GetMapping
    public CharacterList getCharacters() {
        RestTemplate restTemplate = new RestTemplate();
        CharacterList characterList = new CharacterList();

        int page = 1;
        boolean morePages = true;

        while (morePages) {
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("page",page)
                    .build();

            ResponseEntity<CharacterList> responseEntity = restTemplate
                    .exchange(uriComponents.toUriString(),
                            HttpMethod.GET,
                            null,
                            CharacterList.class
                    );

            CharacterList characters = responseEntity.getBody();

            if (characters != null) {
                characterList.getResults().addAll(characters.getResults());
                if (characters.getInfo().getNext() != null && !characters.getInfo().getNext().isEmpty()) {
                    page++;
                } else {
                    morePages = false;
                }
            } else {
                morePages = false;
            }

        }
        return characterList;
    }
}
