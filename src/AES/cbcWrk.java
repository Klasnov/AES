package AES;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Cipher block chaining work method
 * @author Klasnov
 */

public class cbcWrk {
    private final int LEN = 16;
    private final ArrayList<byte[]> plt;
    private byte[] intVec;

    public cbcWrk() {
        this.plt = new ArrayList<>();
        this.intVec = new byte[LEN];
        rdPadPlt();
        // rdIV();
    }

    /**
     * Read in the plaintext and ues PKCS7 method to pad it
     */
    private void rdPadPlt() {
        String pltStr;
        int i, j, len, rnd, ram;
        byte[] tmp, pad;
        /* Read in all the input */
        System.out.println("Enter your plaintext: ");
        Scanner scn = new Scanner(System.in);
        pltStr = scn.nextLine();
        System.out.println("The plaintext you input: " + pltStr);
        len = pltStr.length();
        rnd = len / LEN;
        ram = len % LEN;
        /* Split the input into many blocks with 16 bytes */
        byte[] pltAll = pltStr.getBytes(StandardCharsets.UTF_8);
        for (i = 0; i < rnd; i++) {
            tmp = Arrays.copyOfRange(pltAll, i * LEN, (i + 1) * LEN);
            this.plt.add(tmp);
        }
        /* Padding the blank space */
        pad = new byte[LEN];
        if (ram == 0) {
            for (j = 0; j < LEN; j++) {
                pad[j] = (byte) 0xff;
            }
        }
        else {
            for (j = 0; j < LEN; j++) {
                if (j < ram) {
                    pad[j] = pltAll[i * LEN + j];
                }
                else {
                    pad[j] = (byte) (LEN - ram);
                }
            }
        }
        this.plt.add(pad);
    }

    /**
     * Read in the initialization of CBC work method.
     */
    private void rdIV() {
        System.out.println("Please enter the initialization vector, which must contain 16 chars:");
        String ipt;
        Scanner scn = new Scanner(System.in);
        ipt = scn.nextLine();
        while (ipt.length() != LEN) {
            System.out.println("Invalid length! Please enter an initialization vector with 16 chars:");
            ipt = scn.nextLine();
        }
        intVec = ipt.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Get the plaintext blocks waiting to be encrypted.
     * @return Plaintext blocks.
     */
    public ArrayList<byte[]> getBlk() {
        return plt;
    }

    /**
     * Get the initialization vector matrix
     * @return The initialization vector matrix
     */
    public byte[][] getIV() {
        return stdMtx.aryToMtx(intVec);
    }
}
