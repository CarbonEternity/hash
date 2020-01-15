package ua.nure.popova.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("password", "SHA-256"));
        System.out.println(hash("passwort", "SHA-256"));
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));

        //912EC803B2CE49E4A541068D495AB570
        //F0E4C2F76C58916EC258F246851BEA091D14D4247A2FC3E18694461B1816E13B
    }

    private static String hash(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);   //Creating the MessageDigest object
        md.update(message.getBytes());   //Passing data to the created MessageDigest Object
        byte[] digest = md.digest();   //Compute the message digest
        StringBuilder hexString = new StringBuilder();  //Converting the byte array in to HexString format

        for (byte b : digest) {
            hexString.append(Integer.toHexString(0xFF & b).toUpperCase());
        }
        return hexString.toString();
    }
}
