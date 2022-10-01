package AES;

/**
 * The class to converse matrix shape
 * @author Klasnov
 */

public class stdMtx {
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
}
