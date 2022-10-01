package AES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File operation in the AES algorithm
 * @author Klasnov
 */

public class filOpt {
    private boolean decFlag;

    public filOpt(cbcWrk cbc, ArrayList<byte[]> cph) throws IOException {
        wrtFil(cbc, cph);
    }

    /**
     * Write the ciphertext into the specified file.
     */
    private void wrtFil(cbcWrk cbc, ArrayList<byte[]> cph) throws IOException {
        boolean ext;
        String filNam;
        File file;
        /* Specify the record file */
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter the file name for saving the ciphertext, as \"test.txt\":");
            filNam = scn.nextLine();
            file = new File(filNam);
            ext = !file.createNewFile();
            /* If the file has already existed, assure whether to cover it */
            if (ext) {
                String chc;
                System.out.println("The file has already existed. Are you sure you want to cover it?");
                do {
                    System.out.print("Please enter 'Y'/'N' to indicate yes or no: ");
                    chc = scn.nextLine();
                } while ((chc.compareTo("Y") != 0) && (chc.compareTo("N") != 0));
                if (chc.compareTo("Y") == 0) {
                    break;
                }
            }
            else {
                break;
            }
        }
        /* Output the initialization vector and ciphertext */
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(stdMtx.mtxToAry(cbc.getIV()));
        fos.write('\n');
        for (byte[] bts : cph) {
            fos.write(bts);
            fos.write('\n');
        }
        fos.close();
    }
}
