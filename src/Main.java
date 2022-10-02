import AES.*;

import java.io.IOException;

/**
 * The entire AES encryption and decryption process demonstration.
 * @author Klasnov
 */

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("***************************$ Statement information $*******************************");
        System.out.println("Copyright Notice: Spread and commercial use without authorization are prohibited!");
        System.out.println("Usage Notice: This procedure is the AES password demonstration procedure.");
        System.out.println("***************************$ Statement information $*******************************");
        System.out.println("================ AES cryptographic algorithm program demonstration ================");
        aesEnc enc = new aesEnc();
        filOpt fil = new filOpt(enc.getCbc());
        fil.wrtFil();
        if (fil.rdFil()) {
            aesDec dec = new aesDec(enc.getKey(), fil.getCbc());
            fil.rwtFil(dec.getPlt());
        }
    }
}
