package co.edu.consumerickandmorty.apirestrickandmorty.controller;

import co.edu.consumerickandmorty.apirestrickandmorty.List.CharacterList;
import co.edu.consumerickandmorty.apirestrickandmorty.model.Characters;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/characters")
public class CharactersController {
    private final String urlAll = "https://rickandmortyapi.com/api/character";
    private final String urlSingle = "https://rickandmortyapi.com/api/character/{id}";

    @GetMapping
    public CharacterList getCharacters() {
        RestTemplate restTemplate = new RestTemplate();
        CharacterList characterList = new CharacterList();

        int page = 1;
        boolean morePages = true;

        while (morePages) {
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(urlAll)
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

    @GetMapping("/{id}")
    public Characters getCharacter(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(urlSingle, Characters.class, id);
    }

    @GetMapping("/filter")
    public CharacterList filterCharacters(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "species", required = false) String species,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "gender", required = false) String gender
    ) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://rickandmortyapi.com/api/character/");

        if (name != null) {
            builder.queryParam("name", name);
        }
        if (status != null) {
            builder.queryParam("status", status);
        }
        if (species != null) {
            builder.queryParam("species", species);
        }
        if (type != null) {
            builder.queryParam("type", type);
        }
        if (gender != null) {
            builder.queryParam("gender", gender);
        }

        ResponseEntity<CharacterList> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                CharacterList.class
        );

        return responseEntity.getBody();
    }

    @PostMapping
    public Characters createCharacter(@RequestBody Characters character) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Characters> responseEntity = restTemplate.postForEntity(
                urlAll,
                character,
                Characters.class
        );
        return responseEntity.getBody();
    }
}
