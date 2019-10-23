package com.example.characters.marvelclient;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class MarvelClient {

    private static final String REQUEST_URL = "https://gateway.marvel.com/v1/public/characters";

    private static final Logger LOGGER = LoggerFactory.getLogger(MarvelClient.class);

    private final MarvelUrlProvider marvelUriBuilder;

    @Value("${marvel.timestamp:1}")
    private int timeStamp;

    @Value("${marvel.characters.limit:100}")
    private int limit;

    public MarvelClient(final MarvelUrlProvider marvelUriBuilder) {
        this.marvelUriBuilder = marvelUriBuilder;
    }

    @Cacheable("results")
    public JsonArray getCharacterResults() {
        int index = 1;
        JsonObject firstCallResponse = getData(index++, null);

        int total = firstCallResponse.get("data").getAsJsonObject().get("total").getAsInt();
        String etag = firstCallResponse.get("etag").getAsString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("If-None-Match", etag);

        JsonArray result = getResults(firstCallResponse);
        while (index * limit < total) {
            JsonObject object = getData(index++, headers);
            result.addAll(getResults(object));
            LOGGER.info(index + ":" + total + ":" + result.size() + "\n");
        }
        return result;
    }

    private JsonArray getResults(final JsonObject firstCallResponse) {
        return firstCallResponse.get("data").getAsJsonObject().get("results").getAsJsonArray();
    }

    private JsonObject getData(final int index, final HttpHeaders headers) {
        String url = marvelUriBuilder.provide(REQUEST_URL, timeStamp, createAdditionalUrlParameters(index));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
        return new Gson().fromJson(response.getBody(), JsonObject.class);
    }

    private Map<String, Object> createAdditionalUrlParameters(final int index) {
        Map<String, Object> additionalUrlParameters = new HashMap<>();
        additionalUrlParameters.put("limit", limit);
        additionalUrlParameters.put("offset", index);
        additionalUrlParameters.put("orderBy", "name");
        return additionalUrlParameters;
    }

    public JsonObject findById(final String id) {
        String url = marvelUriBuilder.provide(REQUEST_URL + "/" + id, timeStamp);
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        return new Gson().fromJson(response.getBody(), JsonObject.class);
    }
}
