package AES;

public class stdMtx {
    private static final int N = 4;

    public static byte[][] trs(byte[][] ipt) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opt[i][j] = ipt[j][i];
            }
        }
        return opt;
    }

    public static byte[][] aryToMtx(byte[] ipt) {
        byte[][] opt = new byte[][] {new byte[N], new byte[N], new byte[N], new byte[N]};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                opt[j][i] = ipt[i * N + j];
            }
        }
        return opt;
    }
}
