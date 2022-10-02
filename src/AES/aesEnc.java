package AES;

import java.util.ArrayList;

/**
 * AES algorithm encryption process.
 * @author Klasnov
 */

public class aesEnc {
    private final keyOpt key;
    private final cbcWrk cbc;

    public aesEnc() {
        key = new keyOpt();
        key.rdSed();
        key.extKey();
        cbc = new cbcWrk();
        cbc.rdPadPlt();
        cbc.rdIV();
        run();
    }

    /**
     * Run the AES encryption algorithm.
     */
    private void run() {
        final int N = 4;
        byte[][] bef, pltMtx, aft = new byte[N][N];
        /* Encryption process */
        key.prtAllKey();
        System.out.println("\nAES encrypting..................");
        ArrayList<byte[]> cph = new ArrayList<>();
        boolean flag = false;
        for (byte[] p : cbc.getPlt()) {
            pltMtx = stdOpt.aryToMtx(p);
            if (flag) {
                bef = stdOpt.mtxXor(aft, pltMtx);
            }
            else {
                bef = stdOpt.mtxXor(cbc.getIV(), pltMtx);
                flag = true;
            }
            aft = enc(bef, key);
            cph.add(stdOpt.mtxToAry(aft));
        }
        /* Output the ciphertext each byte */
        cbc.setCph(cph);
        stdOpt.prtLst(cbc.getCph());
    }

    /**
     * The core encryption process.
     * @param ipt The input matrix needing to be encrypted.
     * @param key The keyOpt class used.
     * @return The matrix after encryption.
     */
    private byte[][] enc(byte[][] ipt, keyOpt key) {
        final int RND = 10;
        byte[][] opt = stdOpt.mtxXor(ipt, key.getRdKey(0));
        for (int i = 1; i <= RND; i++) {
            opt = sBox.bytSst(opt, sBox.ENC);
            opt = shfRow.rowSft(opt, shfRow.LFT);
            if (i != RND) {
                opt = mixCol.mxCol(opt, mixCol.MIX);
            }
            opt = stdOpt.mtxXor(opt, key.getRdKey(i));
        }
        return opt;
    }

    /**
     * Get the CBC network of this encryption process.
     * @return The CBC network.
     */
    public cbcWrk getCbc() {
        return cbc;
    }

    /**
     * Get the keys of this encryption process.
     * @return The keys.
     */
    public keyOpt getKey() {
        return key;
    }
}
