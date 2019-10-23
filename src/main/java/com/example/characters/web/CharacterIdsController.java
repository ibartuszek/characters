package com.example.characters.web;

import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.characters.domain.MarvelCharacter;
import com.example.characters.service.CharacterService;

@RestController
public class CharacterIdsController {

    private final CharacterService characterService;

    public CharacterIdsController(final CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping(value = "/characters", method = RequestMethod.GET, produces = "application/json")
    public List<Long> getCharacterIds() {
        return characterService.getCharacterIds();
    }

    @RequestMapping(value = "characters/{id}")
    public MarvelCharacter getCharacterById(@PathVariable("id") String id,
        @RequestParam(value = "language", required = false) Locale language) {
        return language != null ? characterService.getCharacterById(id, language)
            : characterService.getCharacterById(id);
    }

}
