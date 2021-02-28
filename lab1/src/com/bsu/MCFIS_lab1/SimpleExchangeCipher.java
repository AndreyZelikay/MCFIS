package com.bsu.MCFIS_lab1;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleExchangeCipher {

    private final Map<String, String> exchangeMap;

    public SimpleExchangeCipher(String[] alphabet, String[] exchangedAlphabet) {
        if(alphabet.length != exchangedAlphabet.length) {
            throw new IllegalArgumentException("incorrect exchange alphabet length");
        }

        exchangeMap = IntStream.range(0, alphabet.length).boxed()
                .collect(Collectors.toMap(index -> alphabet[index], index -> exchangedAlphabet[index]));
    }

    public String encode(String input) {
        return Arrays.stream(input.split(""))
                .map(exchangeMap::get)
                .collect(Collectors.joining());
    }

    public String decode(String input) {
        return Arrays.stream(input.split(""))
                .map(symbol -> exchangeMap.entrySet().stream()
                        .filter(entry -> entry.getValue().equals(symbol))
                        .findAny()
                        .map(Map.Entry::getKey)
                        .orElseThrow(IllegalArgumentException::new)
                ).collect(Collectors.joining());
    }
}
