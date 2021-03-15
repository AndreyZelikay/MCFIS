package com.bsu.MCFIS_lab2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    private static final int[][] keysArray = {
            {1, 4, 7, 10, 2, 5, 8, 1},
            {2, 5, 8, 11, 3, 6, 9, 12},
            {3, 6, 9, 12, 10, 4, 7, 1}
    };

    private static final int[][] transformMatrix = {
            {1, 0xD, 2, 9, 7, 0xA, 6, 0, 8, 0xC, 4, 5, 0xF, 3, 0xB, 0xE},
            {0xB, 0xA, 0xF, 5, 0, 0xC, 0xE, 8, 6, 2, 3, 9, 1, 7, 0xD, 4}
    };

    private static final int[] p = {1, 4, 7, 2, 5, 8, 3, 6};

    public static void main(String[] args) {
        int x = 0b1000111;
        int k = 4096 - 11 * 6 * 7;

        System.out.println(k);
        System.out.println(x);

        int[] kArray = Arrays.stream(keysArray).mapToInt(keys -> bitPermutation(k, keys, 12)).toArray();

        System.out.println(Arrays.toString(kArray));

        int t = Arrays.stream(kArray).reduce(x, (accum, elem) -> (accum + elem) % 256);

        System.out.println("T: " + t);

        String tBinary = Integer.toBinaryString((1 << 8) | t).substring(1);

        int t1 = Integer.parseInt(tBinary.substring(0, 4), 2);
        int t2 = Integer.parseInt(tBinary.substring(4), 2);

        int n1 = transformMatrix[0][t1];
        int n2 = transformMatrix[1][t2];

        int n = Integer.parseInt(
                Integer.toBinaryString((1 << 4) | n1).substring(1) + Integer.toBinaryString((1 << 4) | n2).substring(1),
                2);

        System.out.println("N: " + Integer.toBinaryString(n));

        int y = bitPermutation(n, p, 8);

        System.out.println("Y: " + y);
    }

    private static int bitPermutation(int value, int[] bitIndexes, int bitNumber) {
        return Integer.parseInt(Arrays.stream(bitIndexes)
                .mapToObj(index -> Integer.toBinaryString((1 << bitNumber) | value).substring(1).charAt(index - 1))
                .map(String::valueOf)
                .collect(Collectors.joining()), 2);
    }
}
