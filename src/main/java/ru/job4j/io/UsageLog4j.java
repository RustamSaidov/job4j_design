package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 1000;
        int i = 1000000;
        long l = 999999999;
        float f = 1.5f;
        double d = 2.5;
        boolean bool = true;
        char c = 'c';

        LOG.debug("User info name byte b : {}, short sh : {}, int i : {}, long l : {}, float f : {}, double d : {}, " +
                "boolean bool : {}, char c : {}", b, sh, i, l, f, d, bool, c);
    }
}