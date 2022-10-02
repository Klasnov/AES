package AES;

import java.util.ArrayList;

/**
 * Normalization operations in the AES algorithm.
 * @author Klasnov
 */

public class stdOpt {
    private static final int N = 4;

    /**
     * Transpose a matrix.
     * @param ipt The matrix needing to be transposed.
     * @return The matrix after transposition.
     */
    public static byte[][] trs(byte[][] ipt) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opt[i][j] = ipt[j][i];
            }
        }
        return opt;
    }

    /**
     * The XOR operation between two matrix.
     * @param ipt1 The first computed matrix.
     * @param ipt2 The second computed matrix.
     * @return The matrix after XOR operation.
     */
    public static byte[][] mtxXor(byte[][] ipt1, byte[][] ipt2) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                opt[i][j] = (byte) (ipt1[i][j] ^ ipt2[i][j]);
            }
        }
        return opt;
    }

    /**
     * Converse an array into a matrix.
     * @param ipt The array needing to be conversed.
     * @return The conversed matrix.
     */
    public static byte[][] aryToMtx(byte[] ipt) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opt[j][i] = ipt[i * N + j];
            }
        }
        return opt;
    }

    /**
     * Converse a matrix into an array.
     * @param ipt The matrix needing to be conversed.
     * @return The conversed array.
     */
    public static byte[] mtxToAry(byte[][] ipt) {
        byte[] opt = new byte[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opt[i * N + j] = ipt[j][i];
            }
        }
        return opt;
    }

    /**
     * Print the ASCII code of list each byte.
     */
    public static void prtLst(ArrayList<byte[]> ipt) {
        System.out.println("\nThe ASCII code value is:");
        int tmp;
        for (byte[] c : ipt) {
            for (byte b : c) {
                tmp = b & 0x0ff;
                if ((tmp & 0x0f0) != 0) {
                    System.out.print("0x" + Integer.toHexString(tmp) + " ");
                }
                else {
                    System.out.print("0x0" + Integer.toHexString(tmp) + " ");
                }
            }
            System.out.println();
        }
    }
}
