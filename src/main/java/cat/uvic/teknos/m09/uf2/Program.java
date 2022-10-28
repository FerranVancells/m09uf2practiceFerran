package cat.uvic.teknos.m09.uf2;

import com.jogamp.common.util.RunnableExecutor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

public class Program {
    private static boolean follow = true;
    private static Scanner in = new Scanner(System.in);
    private static HashParameters hashParameters;

    public static void main(String[] args) throws NoSuchAlgorithmException, URISyntaxException, IOException {
        var properties = new Properties();
        properties.load(Program.class.getResourceAsStream("/hash.properties"));


        var Thread=new Thread(Program::UpdateProperties);
        Thread.start();

        hashParameters = new HashParameters(properties.getProperty("algorithm"), properties.getProperty("salt"));


        while (follow) {
            System.out.println("Type the path of the file you want to hash");
            System.out.println("Digest: " + getDigest(in.nextLine(), hashParameters));
            System.out.println("Salt: " + hashParameters.getSalt());

            askToFollow();
        }
        Thread.stop();
        System.out.println("Bye!");
    }

    private static void askToFollow() {
        System.out.println("Type 'e' to exit or just enter to follow");
        var exit = in.nextLine();

        if (exit.trim().toLowerCase().equals("e")) {
            follow = false;

        }
    }

    private static String getDigest(String path, HashParameters parameters) throws NoSuchAlgorithmException, URISyntaxException, IOException {
        String digestBase64 = null;
        var pathObj = Paths.get(path);
        if (Files.exists(pathObj)) {
            var data = Files.readAllBytes(pathObj);

            var messageDigest = MessageDigest.getInstance(parameters.getAlgorithm());
            messageDigest.update(parameters.getSaltBytes());

            var digest = messageDigest.digest(data);

            var base64Encoder = Base64.getEncoder();

            digestBase64 =  base64Encoder.encodeToString(digest);
        }

        return digestBase64;
    }


    public static HashParameters getHashParameters() {
        return hashParameters;
    }

    public static void setHashParameters(HashParameters hashParameters) {
        Program.hashParameters = hashParameters;
    }
    public static void UpdateProperties(){
        while(true){
            try {
                Thread.sleep(60*1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var properties = new Properties();
            try {
                properties.load(Program.class.getResourceAsStream("/hash.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if(!properties.getProperty("salt").equals(hashParameters.getSalt()) || !properties.getProperty("algorithm").equals(hashParameters.getAlgorithm())){
                System.out.println("el hash.properties ha canviat");
                try {
                    hashParameters=CreateHashParameters();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static HashParameters CreateHashParameters() throws IOException {
        var properties = new Properties();
        properties.load(Program.class.getResourceAsStream("/hash.properties"));
        var hashParameters = new HashParameters(properties.getProperty("algorithm"), properties.getProperty("salt"));
        return hashParameters;
    }
}