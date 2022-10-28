package cat.uvic.teknos.m09.uf2.utilities;

import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltGenerator {
    public static void main(String[] args) throws IOException {
        var iterations = 20;
        if (args.length == 1) {
            iterations = Integer.parseInt(args[0]);
        }

        try (var writer = new BufferedWriter(new FileWriter("src/main/resources/salt.txt"))) {
           for(var i = 0; i < iterations; i++) {
               writer.write(getSalt());
               writer.newLine();
           }
        }
    }

    public static String getSalt() {
        var secureRandom = new SecureRandom();

        var salt = new byte[16];
        secureRandom.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }
}
