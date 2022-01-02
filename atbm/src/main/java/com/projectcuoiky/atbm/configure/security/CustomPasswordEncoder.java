package com.projectcuoiky.atbm.configure.security;

import lombok.SneakyThrows;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @SneakyThrows
    @Override
    public String encode(CharSequence plainTextPassword) {
        return hashPassword(plainTextPassword.toString());
    }

    @SneakyThrows
    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return passwordInDatabase.equals(hashPassword(plainTextPassword.toString()));
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    public String hashPassword(String plainTextPassword) throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance("SHA-256", "BC");
        byte[] messageDigest = md.digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, messageDigest);
        String hashText = number.toString(16);
        return hashText;
    }
}
