package tue.student.iot.group18.service;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

@Service
public class ED25519 {

    Ed25519PrivateKeyParameters privateKey = null;
    Ed25519PublicKeyParameters publicKey = null;
    private String hexPublicKey  = "";

    public void generateKeypair(){


        try {
            System.out.println("ED25519 signature with BC and deriving public key from private key");
            Security.addProvider(new BouncyCastleProvider());
            // generate ed25519 keys
            SecureRandom RANDOM = new SecureRandom();
            Ed25519KeyPairGenerator keyPairGenerator = new Ed25519KeyPairGenerator();
            keyPairGenerator.init(new Ed25519KeyGenerationParameters(RANDOM));
            AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();
            privateKey = (Ed25519PrivateKeyParameters) asymmetricCipherKeyPair.getPrivate();
            publicKey = (Ed25519PublicKeyParameters) asymmetricCipherKeyPair.getPublic();

            System.out.print("public key code: ");
            int unsigned_pk[] = new int[32];

            for(int i = 0;i<32;i++){
                unsigned_pk[i] = Byte.toUnsignedInt(publicKey.getEncoded()[i]);
                System.out.print(unsigned_pk[i] + ", ");
            }
            hexPublicKey = intToHex(unsigned_pk);
            System.out.println();
            System.out.println("public key: " + hexPublicKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHexPublicKey(){
        if(hexPublicKey == null || privateKey == null){
            generateKeypair();
        }
        return hexPublicKey;
    }


    public String sign(String message){
        try {
            // the message
            byte[] m = message.getBytes(StandardCharsets.UTF_8);
            // create the signature
            Signer signer = new Ed25519Signer();
            signer.init(true, privateKey);
            signer.update(m, 0, m.length);
            byte[] signature = signer.generateSignature();

            int unsigned_sign[] = new int[signature.length];
            for (int i = 0; i < signature.length; i++) {
                unsigned_sign[i] = Byte.toUnsignedInt(signature[i]);
                System.out.print(unsigned_sign[i] + ", ");
            }
            System.out.println();
            String hexSign = intToHex(unsigned_sign);
            System.out.println("signature: " + intToHex(unsigned_sign));

            // verify the signature
            Signer verifier = new Ed25519Signer();
            verifier.init(false, publicKey);
            verifier.update(m, 0, m.length);
            boolean shouldVerify = verifier.verifySignature(signature);
            System.out.println("result: " + shouldVerify);
            return hexSign;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private final static char[] hexArray = "0123456789abcdef".toCharArray();
    public static String intToHex(int[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }


    public static byte[] getSHA256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
