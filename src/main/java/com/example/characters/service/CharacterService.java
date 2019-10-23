package com.example.characters.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.characters.domain.MarvelCharacter;
import com.example.characters.marvelclient.MarvelClient;
import com.example.characters.yandexclient.YandexClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class CharacterService {

    private final MarvelClient marvelClient;
    private final YandexClient yandexClient;

    public CharacterService(final MarvelClient marvelClient, final YandexClient yandexClient) {
        this.marvelClient = marvelClient;
        this.yandexClient = yandexClient;
    }

    public List<Long> getCharacterIds() {
        List<Long> ids = new LinkedList<>();

        JsonArray results = marvelClient.getCharacterResults();
        Iterator<JsonElement> iterator = results.iterator();

        while (iterator.hasNext()) {
            JsonElement current = iterator.next();
            ids.add(current.getAsJsonObject().get("id").getAsLong());
        }

        return ids;
    }

    public MarvelCharacter getCharacterById(final String id) {
        JsonObject object = marvelClient.findById(id)
            .get("data").getAsJsonObject()
            .get("results").getAsJsonArray()
            .get(0)
            .getAsJsonObject();
        return new Gson().fromJson(object, MarvelCharacter.class);
    }

    public MarvelCharacter getCharacterById(final String id, final Locale language) {
        MarvelCharacter character = getCharacterById(id);
        MarvelCharacter result;
        if (notEnglish(language)) {
            String translatedDescription = yandexClient.getTranslation(character.getDescription(), language.getLanguage());
            result = MarvelCharacter.builder(character)
                .withDescription(translatedDescription)
                .build();
        } else {
            result = character;
        }
        return result;
    }

    private boolean notEnglish(final Locale language) {
        return !language.getLanguage().equals(Locale.ENGLISH.getLanguage());
    }
}
