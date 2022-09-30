package AES;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CBC {
    private int LEN = 16;
    private ArrayList<byte[]> plt;
    private byte[] intVec;

    public CBC() {
        this.plt = new ArrayList<>();
        this.intVec = new byte[LEN];
        rdPadPlt();
    }

    private void rdPadPlt() {
        String pltStr;
        int i, j, len, rnd, ram;
        byte[] tmp, pad;
        /* Read in all the input */
        System.out.println("Enter your plaintext: ");
        Scanner scn = new Scanner(System.in);
        pltStr = scn.nextLine();
        len = pltStr.length();
        rnd = len / LEN;
        ram = len % LEN;
        /* Split the input into many blocks with 16 bytes */
        byte[] pltAll = pltStr.getBytes(StandardCharsets.UTF_8);
        for (i = 0; i < rnd; i++) {
            tmp = Arrays.copyOfRange(pltAll, i * LEN, (i + 1) * LEN - 1);
            this.plt.add(tmp);
        }
        /* Padding the blank space */
        pad = new byte[LEN];
        if (ram == 0) {
            for (j = 0; j < LEN; j++) {
                pad[j] = (byte) 0xff;
            }
        }
        else {
            for (j = 0; j < LEN; j++) {
                if (j < ram) {
                    pad[j] = pltAll[i * LEN + j];
                }
                else {
                    pad[j] = (byte) (LEN - ram);
                }
            }
        }
        this.plt.add(pad);
    }
}
