package AES;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

/**
 * Extending the keys in all the rounds from the seed.
 * @author Klasnov
 */

public class extKey {
    private final int NK = 4;
    private final int WN = 44;
    private final byte[][] key = new byte[WN][NK];
    private final byte[][] CST = new byte[][] {
            new byte[] {0x01, 0x00, 0x00, 0x00},
            new byte[] {0x02, 0x00, 0x00, 0x00},
            new byte[] {0x04, 0x00, 0x00, 0x00},
            new byte[] {0x08, 0x00, 0x00, 0x00},
            new byte[] {0x10, 0x00, 0x00, 0x00},
            new byte[] {0x20, 0x00, 0x00, 0x00},
            new byte[] {0x40, 0x00, 0x00, 0x00},
            new byte[] {(byte) 0x80, 0x00, 0x00, 0x00},
            new byte[] {0x1b, 0x00, 0x00, 0x00},
            new byte[] {0x36, 0x00, 0x00, 0x00}
    };

    public extKey(@NotNull String str) {
        // Parse the key seed from the input string
        byte[] seed = str.getBytes(StandardCharsets.UTF_8);
        // Initialize the W[0] to W[3]
        int cnt, tmpIdx;
        byte[] tmpAry;
        for (cnt = 0; cnt < NK; cnt++) {
            tmpIdx = cnt * 4;
            tmpAry = new byte[] {seed[tmpIdx], seed[tmpIdx + 1], seed[tmpIdx + 2], seed[tmpIdx + 3]};
            key[cnt] = tmpAry;
        }
        // Calculate the rest W[i]
        int rnd = 0;
        for (; cnt < WN; cnt++) {
            if (cnt % NK != 0) {
                for (int i = 0; i < NK; i++) {
                    key[cnt][i] = (byte) (key[cnt - NK][i] ^  key[cnt - 1][i]);
                }
            }
            else {
                for (int i = 0; i < NK; i++) {
                    key[cnt][i] = (byte) ((sBox.slgSst(key[cnt - 1][(i + 1) % 4], sBox.ENC)
                            ^ CST[rnd][i]) ^ key[cnt - NK][i]);
                }
                rnd++;
            }
        }
    }

    public static void optKys() {

    }
}
