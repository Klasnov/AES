import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Extending the keys in all the rounds from the seed.
 * @author Klasnov
 */

public class extKey {
    private final ArrayList<byte[]> key = new ArrayList<>();

    public extKey(@NotNull String str) {
        final int nk = 4;
        final int wn = 44;
        // Parse the key seed from the input string
        byte[] seed = str.getBytes(StandardCharsets.UTF_8);
        // Initialize the W[0] to W[3]
        int cnt, tmpIdx;
        byte[] tmpAry;
        for (cnt = 0; cnt < nk; cnt++) {
            tmpIdx = cnt * 4;
            tmpAry = new byte[] {seed[tmpIdx], seed[tmpIdx + 1], seed[tmpIdx + 2], seed[tmpIdx + 3]};
            key.add(tmpAry);
        }
        // Calculate the rest W[i]
    }

    // Converts the input seed characters to the standard key format
    static public int fmtKey(byte[] k) {
        return (k[0] << 24) + (k[1] << 16) + (k[2] << 8) + k[3];
    }

    // Get the keys of AES
    public ArrayList<byte[]> getKey() {
        return key;
    }
}
