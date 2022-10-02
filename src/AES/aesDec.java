package AES;

import java.util.ArrayList;

/**
 * AES algorithm decryption process.
 * @author Klasnov
 */

public class aesDec {
    private final keyOpt key;
    private final cbcWrk cbc;
    private String plt;

    public aesDec(keyOpt key, cbcWrk cbc) {
        this.key = key;
        this.cbc = cbc;
        run();
    }

    /**
     * Run the AES decryption algorithm.
     */
    private void run() {
        final int N = 4;
        byte[][] cphMtx, fmr = new byte[N][N];
        /* Decryption process */
        System.out.println("\nAES decryption..................");
        ArrayList<byte[]> plt = new ArrayList<>();
        boolean flag = false;
        for (byte[] c : cbc.getCph()) {
            cphMtx = stdOpt.aryToMtx(c);
            if (flag) {
                plt.add(stdOpt.mtxToAry(stdOpt.mtxXor(fmr, dec(cphMtx, key))));
            } else {
                plt.add(stdOpt.mtxToAry(stdOpt.mtxXor(cbc.getIV(), dec(cphMtx, key))));
                flag = true;
            }
            fmr = cphMtx;
        }
        /* Output the plaintext each byte */
        cbc.setPlt(plt);
        cbc.prsPlt();
        stdOpt.prtLst(cbc.getPlt());
        prtPlt();
    }

    /**
     * The core decryption process.
     *
     * @param ipt The input matrix needing to be decrypted.
     * @param key The keyOpt class used.
     * @return The matrix after decryption.
     */
    private byte[][] dec(byte[][] ipt, keyOpt key) {
        final int RND = 10;
        byte[][] opt = stdOpt.mtxXor(ipt, key.getRdKey(RND));
        for (int i = RND - 1; i >= 0; i--) {
            opt = shfRow.rowSft(opt, shfRow.RIT);
            opt = sBox.bytSst(opt, sBox.DEC);
            opt = stdOpt.mtxXor(opt, key.getRdKey(i));
            if (i != 0) {
                opt = mixCol.mxCol(opt, mixCol.DMX);
            }
        }
        return opt;
    }

    /**
     * Print the plaintext string.
     */
    private void prtPlt() {
        System.out.println("\nThe plaintext is:");
        StringBuilder str = new StringBuilder();
        for (byte[] bts : cbc.getPlt()) {
            str.append(new String(bts)).append("\n");
        }
        plt = str.toString();
        System.out.print(str);
    }

    /**
     * Get the plaintext string.
     * @return The plaintext string.
     */
    public String getPlt() {
        return plt;
    }
}
