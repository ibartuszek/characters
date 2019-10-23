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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Character query endpoints", value = "CharacterController", description = "Controller for character queries")
public class CharacterController {

    private static final String APPLICATION_JSON = "application/json";
    private final CharacterService characterService;

    public CharacterController(final CharacterService characterService) {
        this.characterService = characterService;
    }

    @RequestMapping(value = "/characters", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Get a list of the existing characters' ids")
    public List<Long> getCharacterIds() {
        return characterService.getCharacterIds();
    }

    @RequestMapping(value = "characters/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiOperation(value = "Get information about a character with the specified id")
    public MarvelCharacter getCharacterById(@PathVariable("id") String id,
        @RequestParam(value = "language", required = false) Locale language) {
        return language != null ? characterService.getCharacterById(id, language)
            : characterService.getCharacterById(id);
    }

}
