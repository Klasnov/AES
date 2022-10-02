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
    private ArrayList<byte[]> cph;
    private byte[] intVec;

    public cbcWrk() {
        this.plt = new ArrayList<>();
        this.cph = new ArrayList<>();
        this.intVec = new byte[LEN];
    }

    /**
     * Read in the plaintext and ues PKCS7 method to pad it
     */
    public void rdPadPlt() {
        String pltStr;
        int i, j, len, rnd, ram;
        byte[] tmp, pad;
        /* Read in all the input */
        System.out.println("\nPlease enter the plaintext: ");
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
    public void rdIV() {
        String chc;
        Scanner scn = new Scanner(System.in);
        System.out.println("\nDo you need to set the initial vector manually? " +
                "(Otherwise it will be zeroed by default)");
        do {
            System.out.print("Please enter 'Y'/'N' to indicate yes or no: ");
            chc = scn.nextLine();
        } while ((chc.compareTo("Y") != 0) && (chc.compareTo("N") != 0));
        if (chc.compareTo("Y") == 0) {
            System.out.println("Please enter the initialization vector, which must contain 16 chars:");
            String ipt;
            ipt = scn.nextLine();
            while (ipt.length() != LEN) {
                System.out.println("Invalid length! Please enter an initialization vector with 16 chars:");
                ipt = scn.nextLine();
            }
            intVec = ipt.getBytes(StandardCharsets.UTF_8);
        }
        else {
            intVec = new byte[LEN];
        }
    }

    /**
     * Get the plaintext blocks waiting to be encrypted.
     * @return Plaintext blocks.
     */
    public ArrayList<byte[]> getBlk() {
        return plt;
    }

    /**
     * Record the
     * @param intVec
     */
    public void setIntVec(byte[] intVec) {
        this.intVec = intVec;
    }

    /**
     * Get the initialization vector matrix
     * @return The initialization vector matrix
     */
    public byte[][] getIV() {
        return stdMtx.aryToMtx(intVec);
    }

    /**
     * Record the ciphertext of the CBC network.
     * @param cph The ciphertext after encryption.
     */
    public void setCph(ArrayList<byte[]> cph) {
        this.cph = cph;
    }

    /**
     * Get the ciphertext of the CBC network.
     * @return CBC network's ciphertext.
     */
    public ArrayList<byte[]> getCph() {
        return cph;
    }
}
