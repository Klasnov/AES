package AES;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * The operation on the keys in AES algorithm.
 * @author Klasnov
 */

public class keyOpt {
    private final int NK = 4;
    private final int LEN = 16;
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

    private byte[] seed;

    public keyOpt() {
        seed = new byte[LEN];
        rdSed();
        extKey();
    }

    /**
     * Get the seed of encryption's key
     */
    private void rdSed() {
        String ipt;
        System.out.println("\nPlease enter the key which contains 16 chars:");
        Scanner scn = new Scanner(System.in);
        ipt = scn.nextLine();
        while (ipt.length() != LEN) {
            System.out.println("Incorrect length! Please enter the key which contains 16 chars:");
            ipt = scn.nextLine();
        }
        System.out.println("The key you input: " + ipt);
        seed = ipt.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Extend the key seed into sufficient keys used in each round
     */
    private void extKey() {
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

    /**
     * Get the key of one round.
     * @param i The round number.
     * @return Key matrix.
     */
    public byte[][] getRdKey(int i) {
        byte[][] keyMtx = new byte[NK][NK];
        System.arraycopy(key, i * 4, keyMtx, 0, NK);
        return stdMtx.trs(keyMtx);
    }

    /**
     * Print all the keys on the screen.
     */
    public void prtAllKey() {
        System.out.println("Round keys..................");
        int rdKy;
        for (int i = 0; i < WN; i++) {
            rdKy = (key[i][0] << 24) & 0x0ff000000;
            rdKy += (key[i][1] << 16) & 0x0ff0000;
            rdKy += (key[i][2] << 8) & 0x0ff00;
            rdKy += key[i][3] & 0x0ff;
            if (i > 9) {
                System.out.print("w[" + i + "] = 0x" + Integer.toHexString(rdKy) + "\t");
            }
            else {
                System.out.print("w[0" + i + "] = 0x" + Integer.toHexString(rdKy) + "\t");
            }
            System.out.print("\t");
            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
