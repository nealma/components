package com.nealma.account.shiro;

import com.nealma.framework.model.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.UnsupportedEncodingException;

/**
 * Created by nealpc on 5/24/16.
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(
            RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String algorithmName = "SHA-512";
        String username = "jiecao";
        String password = "123456";
        String salt1 = username;
        String salt2 = "827099c0e16190e1cdc73a33faf5c473";//new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password,
                username + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(salt2);
        System.out.println(encodedPassword);

        byte[] seed = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        byte[] b = ByteSource.Util.bytes(seed).getBytes();
        String bString = new String(b, "UTF-8").toString();

        System.out.println(bString);
    }
}