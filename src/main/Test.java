package main;

import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        String currentPaht = Paths.get("").toAbsolutePath().toString();
        System.out.println(currentPaht);
    }
}
