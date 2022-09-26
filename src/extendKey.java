import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Extending the keys in all the rounds from the seed.
 * @author Klasnov
 */

public class extendKey {
    private final ArrayList<Integer> key = new ArrayList<>();

    public extendKey(@NotNull String str) {
        final int nk = 4;
        final int wn = 44;
        // Parse the key seed from the input string
        byte[] seedChar = str.getBytes(StandardCharsets.UTF_8);
        int cnt, temp;
        // Initialize the W[0] to W[3]
        for (cnt = 0; cnt < nk; cnt++) {
            temp = cnt * 4;
            key.add(fmtKey(seedChar[temp], seedChar[temp + 1], seedChar[temp + 2], seedChar[temp + 3]));
        }
        // Calculate the rest W[i]
    }

    // Converts the input seed characters to the standard key format
    static public Integer fmtKey(byte k1, byte k2, byte k3, byte k4) {
        return ((int)k1 << 24) + ((int)k2 << 16) + ((int)k3 << 8) + (int)k4;
    }

    public ArrayList<Integer> getKeys() {
        return key;
    }
}
