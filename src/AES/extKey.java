package AES;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Extending the keys in all the rounds from the seed.
 * @author Klasnov
 */

public class extKey {
    private final int nk = 4;
    private final int wn = 44;
    private final byte[][] key = new byte[wn][nk];
    private final int[] rndCst = new int[] {
            0x01000000, 0x02000000,
            0x04000000, 0x08000000,
            0x10000000, 0x20000000,
            0x40000000, 0x80000000,
            0x1b000000, 0x36000000
    };

    public extKey(@NotNull String str) {
        // Parse the key seed from the input string
        byte[] seed = str.getBytes(StandardCharsets.UTF_8);
        // Initialize the W[0] to W[3]
        int cnt, tmpIdx;
        byte[] tmpAry;
        for (cnt = 0; cnt < nk; cnt++) {
            tmpIdx = cnt * 4;
            tmpAry = new byte[] {seed[tmpIdx], seed[tmpIdx + 1], seed[tmpIdx + 2], seed[tmpIdx + 3]};
            key[cnt] = tmpAry;
        }
        // Calculate the rest W[i]
    }

    private int keyToHex(byte[] k) {
        return (k[0] << 24) + (k[1] << 16) + (k[2] << 8) + k[3];
    }

    private byte[] keyToByte(int key) {
        byte b1, b2, b3, b4;
        return new byte[] {};
    }

    // Get the keys of AES
    public byte[][] getKey() {
        return key;
    }
}
