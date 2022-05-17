package com.taiko.taikoproject.taiko.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordCrypto {

    public String passwordCrypting(String password) throws NoSuchAlgorithmException {
        String salt = password;
        String hex = null;
        MessageDigest msg = MessageDigest.getInstance("SHA-512");
        msg.update(salt.getBytes());

        hex = String.format("%128x", new BigInteger(1, msg.digest()));

        System.out.println("hex@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + hex);
        return hex;
    }
}
