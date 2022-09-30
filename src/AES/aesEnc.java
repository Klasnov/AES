package AES;

import java.util.ArrayList;

/**
 * AES algorithm encryption process
 * @author Klasnov
 */

public class aesEnc {
    private static final int N = 4;
    private static final int RND = 10;

    /**
     * Run the AES encryption algorithm.
     */
    public static void run() {
        ArrayList<byte[]> plt;
        byte[][] iv, bef, aft, pltMtx;
        int cnt;
        keyOpt key = new keyOpt();
        cbcWrk cbc = new cbcWrk();
        plt = cbc.getBlk();
        iv = cbc.getIV();
        cnt = 0;
        for (byte[] p : plt) {
            pltMtx = stdMtx.aryToMtx(p);
            if (cnt != 0) {

            }
            else {
                bef = mtxXor(iv, pltMtx);
                cnt++;
            }
        }
    }

    /**
     * The XOR operation between two matrix.
     * @param ipt1 The first computed matrix.
     * @param ipt2 The second computed matrix.
     * @return The matrix after XOR operation.
     */
    private static byte[][] mtxXor(byte[][] ipt1, byte[][] ipt2) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                opt[i][j] = (byte) (ipt1[i][j] ^ ipt2[i][j]);
            }
        }
        return opt;
    }
}
