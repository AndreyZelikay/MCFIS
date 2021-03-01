package com.bsu.MCFIS_lab1;

public class Main {

    private static final String[] alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".split("");
    private static final String[] exchangeAlphabet = "ИЮЧЪЩЁЗМШСХУРЦЭЬЫНФДБТВЛГАПЯЕОЖКЙ".split("");

    public static void main(String[] args) {
        AffineCipher affineCipher = new AffineCipher(17, 24, alphabet);
        System.out.println(affineCipher.encode("КРИПТОСИСТЕМА"));
        System.out.println(affineCipher.decode(affineCipher.encode("КРИПТОСИСТЕМА")));

        SimpleExchangeCipher sec = new SimpleExchangeCipher(alphabet, exchangeAlphabet);

        System.out.println(sec.decode("ЩМЁУТНБД"));
        System.out.println(sec.encode("ДЖЕКФРУТ"));
    }
}
