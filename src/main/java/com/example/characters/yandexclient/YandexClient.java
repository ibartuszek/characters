package com.example.characters.yandexclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.aurasphere.jyandex.Jyandex;
import co.aurasphere.jyandex.dto.Language;
import co.aurasphere.jyandex.dto.TranslateTextResponse;

@Component
public class YandexClient {

    @Value("#{systemEnvironment['YANDEX_KEY']}")
    private String apiKey;

    public String getTranslation(final String text, final String targetLanguage) {
        Jyandex client = new Jyandex(apiKey);
        TranslateTextResponse result = client.translateText(text, Language.ENGLISH, targetLanguage);
        return result.getTranslatedText()[0];
        // return client.translateText(text, Language.ENGLISH, targetLanguage).toString();
    }
}
