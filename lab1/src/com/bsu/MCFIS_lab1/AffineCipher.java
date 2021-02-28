package com.bsu.MCFIS_lab1;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.bsu.MCFIS_lab1.ModulusOperations.applyModulus;
import static com.bsu.MCFIS_lab1.ModulusOperations.reverse;

public class AffineCipher {

    private final String[] alphabet;
    private final int reversAKey;
    private final int aKey;
    private final int bKey;

    public AffineCipher(int aKey, int bKey, String[] alphabet) {
        this.alphabet = alphabet;
        this.aKey = aKey;
        this.bKey = bKey;
        this.reversAKey = reverse(aKey, alphabet.length);
    }

    public String encode(String input) {
        return Arrays.stream(input.split(""))
                .map(symbol -> Arrays.binarySearch(alphabet, symbol))
                .map(index -> applyModulus(aKey * index + bKey, alphabet.length))
                .map(index -> alphabet[index])
                .collect(Collectors.joining());
    }

    public String decode(String input) {
        return Arrays.stream(input.split(""))
                .map(symbol -> Arrays.binarySearch(alphabet, symbol))
                .map(index -> applyModulus(reversAKey * (index - bKey), alphabet.length))
                .map(index -> alphabet[index])
                .collect(Collectors.joining());
    }
}
