package com.example.characters.web;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.characters.service.CharacterIdsService;

@RestController
public class CharacterIdsController {

    private final CharacterIdsService characterIdsService;

    public CharacterIdsController(final CharacterIdsService characterIdsService) {
        this.characterIdsService = characterIdsService;
    }

    @RequestMapping(value = "/characters", method = RequestMethod.GET, produces = "application/json")
    public List<Long> getCharacterIds() {
        return characterIdsService.getCharacterIds();
    }

}
