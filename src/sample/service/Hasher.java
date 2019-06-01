package sample.service;

public class Hasher {
    public static String hash(String p) {
        return String.valueOf(p.hashCode());
    }
}
