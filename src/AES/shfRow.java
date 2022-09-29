package AES;

/**
 * Finish the row shifting operation in the AES
 * @author Klasnov
 */

public class shfRow {
    final static private int N = 4;
    final static public int LFT = 0;
    final static public int RIT = 1;

    /**
     * Do the leftwards row shifting of the matrix
     * @param a The original matrix
     * @param dir The shift direction, use shfRow.LFT or shfRow.RIT to define
     * @return The shifted matrix
     */
    static public byte[][] rowSft(byte[][] a, int dir) {
        byte[][] b = new byte[N][];
        int i;
        for (i = 0; i < N; i++) {
            switch (dir) {
                case LFT -> b[i] = lftOpt(a[i], i);
                case RIT -> b[i] = ritOpt(a[i], i);
                default -> System.out.println("Invalid value of argument dir!");
            }
        }
        return b;
    }

    /**
     * Do the leftwards row shifting of one row
     * @param a The original row of data
     * @param n The length of the row
     * @return The shifted row of data
     */
    static private byte[] lftOpt(byte[] a, int n) {
        byte[] b = new byte[N];
        int i;
        for (i = 0; i < N; i++) {
            b[i] = a[(i + n) % N];
        }
        return b;
    }

    /**
     * Do the rightwards row shifting of one row
     * @param a The original row of data
     * @param n The length of the row
     * @return The shifted row of data
     */
    static private byte[] ritOpt(byte[] a, int n) {
        byte[] b = new byte[N];
        int i;
        for (i = N - 1; i >= 0; i--) {
            b[i] = a[(i - n) % N];
        }
        return b;
    }
}