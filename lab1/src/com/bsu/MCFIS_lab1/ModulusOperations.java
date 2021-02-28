package com.bsu.MCFIS_lab1;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class ModulusOperations {

    public static int applyModulus(int x, int modulus) {
        return x >= 0 ? x % modulus : modulus - (-x) % modulus;
    }

    public static int reverse(int a, int modulus) {
        int euler = (int) primeFactors(modulus).stream()
                .mapToDouble(Integer::doubleValue)
                .reduce(modulus, (accum, value) -> accum * (1 - 1 / value));
        int result = BigInteger.valueOf(a).modPow(BigInteger.valueOf(euler - 1), BigInteger.valueOf(modulus)).intValue();

        if(result*a % modulus != 1) {
            throw new RuntimeException("illegal key");
        }

        return result;
    }

    private static List<Integer> primeFactors(int value) {
        List<Integer> factors = new LinkedList<>();

        for (int i = 2; i <= value; i++) {
            while (value % i == 0) {
                factors.add(i);
                value /= i;
            }
        }

        return factors;
    }
}
