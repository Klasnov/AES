package AES;

import java.util.ArrayList;

/**
 * Finish the mix of colum operation.
 * @author Klasnov
 */

public class mixCol {
    private static final int N = 4;
    public static final int MIX = 0;
    public static final int DMX = 1;
    private static final byte[][] MXM = {
            new byte[] {2, 3, 1, 1},
            new byte[] {1, 2, 3, 1},
            new byte[] {1, 1, 2, 3},
            new byte[] {3, 1, 1, 2}
    };
    private static final byte[][] DMM = {
            new byte[] {0xe, 0xb, 0xd, 0x9},
            new byte[] {0x9, 0xe, 0xb, 0xd},
            new byte[] {0xd, 0x9, 0xe, 0xb},
            new byte[] {0xb, 0xd, 0x9, 0xe}
    };

    /**
     * Finish the mix colum matrix multiplication
     * @param a The matrix needed to be multiplied
     * @param type Mix or Anti-mix, use minCol
     * @return The matrix after multiplying
     */
    public static byte[][] mxCol(byte[][] a, int type) {
        byte[][] b = new byte[N][N];
        /* Matrix multiplication on Galois Field 2 ^ 8 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                b[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    switch (type) {
                        case MIX -> b[i][j] = (byte) ((b[i][j] ^ mulGF(MXM[i][k], a[k][j])) & 0x0ff);
                        case DMX -> b[i][j] = (byte) ((b[i][j] ^ mulGF(DMM[i][k], a[k][j])) & 0x0ff);
                        default -> System.out.println("Invalid value of argument type!");
                    }
                }
            }
        }
        return b;
    }

    /**
     * The multiplication on Galois Field 2 ^ 8
     * @param a The first multiplier
     * @param b The second multiplier
     * @return The multiplied result
     */
    private static byte mulGF(byte a, byte b) {
        /* If one of the multiplier is 1, return another multiplier directly*/
        if (a == 0x01) {
            return b;
        }
        if (b == 0x01) {
            return a;
        }
        /* Make the second multiplier always the smaller one */
        boolean flag = ((a & 0x080) != 0x080) && ((b & 0x080) == 0x080);
        if (flag) {
            byte tmp = b;
            b = a;
            a = tmp;
        }
        /* Analise each bit of the second multiplier */
        byte[] p = new byte[] {
                (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08,
                (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x80};
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            if (((b & p[i]) & 0x0ff) == p[i]) {
                idx.add(i);
            }
        }
        /* Use the distribution to do the multiplication */
        byte c, d, tmp, prm = 0x1b;
        ArrayList<Byte> rlt = new ArrayList<>();
        for (int i : idx) {
            c = a;
            d = (byte) ((0x01 << i) & 0x0ff);
            while (d != 0x01) {
                // If the first multiplier outset with 1
                if ((c & 0x080) == 0x080) {
                    // Operations on the first multiplier
                    tmp = c;
                    tmp <<= 1;
                    tmp &= 0x0ff;
                    tmp ^= prm;
                    c = (byte) (tmp & 0x0ff);
                }
                // If the first multiplier outset with 0
                else {
                    c <<= 1;
                    c = (byte) (c & 0x0ff);
                }
                d >>>= 1;
            }
            rlt.add(c);
        }
        /* Do the addition */
        byte rtn = 0x00;
        for (byte bt : rlt) {
            rtn ^= bt;
            rtn = (byte) (rtn & 0x0ff);
        }
        return rtn;
    }
}
