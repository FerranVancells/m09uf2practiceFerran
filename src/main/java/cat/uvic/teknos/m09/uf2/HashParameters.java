package cat.uvic.teknos.m09.uf2;

import java.util.Base64;

public class HashParameters {
    private String salt;
    private String algorithm;

    public HashParameters(String algorithm, String salt) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String getSalt() {
        return salt;
    }

    public byte[] getSaltBytes() {
        return Base64.getDecoder().decode(salt);
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
