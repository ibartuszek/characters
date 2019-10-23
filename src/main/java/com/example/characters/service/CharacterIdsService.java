package com.example.characters.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

@Service
public class CharacterIdsService {

    private final CharactersProxy charactersProxy;

    public CharacterIdsService(final CharactersProxy charactersProxy) {
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

}
