package com.example.characters.marvelclient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MarvelUrlProvider {

    private static final String ALGORITHM = "MD5";

    @Value("#{systemEnvironment['MARVEL_PUBLIC_KEY']}")
    private String publicKey;

    @Value("#{systemEnvironment['MARVEL_PRIVATE_KEY']}")
    private String privateKey;

    public String provide(final String url, final int timeStamp, final Map<String, Object> additionalUrlParameters) {
        UriComponentsBuilder builder = createBaseUriComponentFromUrl(url);
        for (final Map.Entry current : additionalUrlParameters.entrySet()) {
            builder.queryParam((String) current.getKey(), current.getValue());
        }
        addStandardParameters(timeStamp, builder);
        return builder.toUriString();
    }

    private UriComponentsBuilder createBaseUriComponentFromUrl(final String url) {
        return UriComponentsBuilder
            .fromUriString(url);
    }

    private void addStandardParameters(final int timeStamp, final UriComponentsBuilder builder) {
        builder.queryParam("ts", timeStamp)
            .queryParam("apikey", publicKey)
            .queryParam("hash", getHash(timeStamp));
    }

    private String getHash(final int timeStamp) {
        String hash;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update((timeStamp + privateKey + publicKey).getBytes());
            hash = DatatypeConverter.printHexBinary(messageDigest.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm: " + ALGORITHM);
        }
        return hash;
    }

    public String provide(final String url, final int timeStamp) {
        UriComponentsBuilder builder = createBaseUriComponentFromUrl(url);
        addStandardParameters(timeStamp, builder);
        return builder.toUriString();
    }

}
