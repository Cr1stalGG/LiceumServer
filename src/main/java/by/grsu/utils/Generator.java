package by.grsu.utils;

import java.util.Random;

public class Generator {
    private final Random random = new Random(System.currentTimeMillis());

    public String generateCardNumber(){
        StringBuilder cardNumber = new StringBuilder();

        for(int i = 0; i < 4; ++i)
            cardNumber.append(String.format("%04d", random.nextInt( 0,9999)));

        return cardNumber.toString();
    }

    public String generateBonusToken(byte LENGTH){
        final String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder token = new StringBuilder();

        for(byte i = 0; i < LENGTH; ++i)
            token.append(dict.charAt(random.nextInt(dict.length())));

        return String.valueOf(token);
    }
}
