package com.example.characters.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.characters.domain.MarvelCharacter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class CharacterService {

    private final CharactersProxy charactersProxy;

    public CharacterService(final CharactersProxy charactersProxy) {
        this.charactersProxy = charactersProxy;
    }

    public List<Long> getCharacterIds() {
        List<Long> ids = new LinkedList<>();

        JsonArray results = charactersProxy.getCharacterResults();
        Iterator<JsonElement> iterator = results.iterator();

        while (iterator.hasNext()) {
            JsonElement current = iterator.next();
            ids.add(current.getAsJsonObject().get("id").getAsLong());
        }

        return ids;
    }

    public MarvelCharacter getCharacterById(final String id) {
        JsonObject object = charactersProxy.findById(id)
            .get("data").getAsJsonObject()
            .get("results").getAsJsonArray()
            .get(0)
            .getAsJsonObject();
        return new Gson().fromJson(object, MarvelCharacter.class);
    }
}
