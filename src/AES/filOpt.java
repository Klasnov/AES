package AES;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File operation in the AES algorithm.
 * @author Klasnov
 */

public class filOpt {
    private boolean read;
    private String filNm;
    private cbcWrk cbc;
    private File file;

    public filOpt(cbcWrk cbc) {
        this.cbc = cbc;
        this.read = false;
    }

    /**
     * Write the ciphertext into the specified file.
     */
    public void wrtFil() throws IOException {
        boolean ext;
        /* Specify the record file */
        Scanner scn = new Scanner(System.in);
        while (true) {
            System.out.println("\nEnter the file name for saving the ciphertext, as \"test.txt\":");
            filNm = scn.nextLine();
            file = new File(filNm);
            ext = !file.createNewFile();
            /* If the file has already existed, assure whether to cover it */
            if (ext) {
                System.out.println("The file you specified has already existed. Would you like to cover it?");
                if (jdgLop()) break;
            }
        }
        /* Output the initialization vector and ciphertext */
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(stdOpt.mtxToAry(cbc.getIV()));
        for (byte[] bts : cbc.getCph()) {
            fos.write(bts);
        }
        fos.close();
    }

    /**
     * Load the ciphertext from specified file.
     * @return If the loaded file needs to be decrypted, return true. Otherwise, return false.
     */
    public boolean rdFil() throws IOException {
        read = true;
        /* Assure whether the user wants to decrypt the specified file */
        System.out.println("\nWould you like to decrypt the file?");
        if (jdgLop()) {
            /* Specify the file to load in */
            cbc = new cbcWrk();
            Scanner scn = new Scanner(System.in);
            while (true) {
                System.out.println("\nPlease enter the file name you want to decrypt. " +
                        "It needs to be in the current directory.");
                filNm = scn.nextLine();
                file = new File(filNm);
                /* If the file exists, load in its initialization vector and ciphertext */
                if (file.exists()) {
                    final int LEN = 16;
                    byte[] tmp = new byte[LEN];
                    FileInputStream fis = new FileInputStream(file);
                    /* If the file isn't empty, load in its initialization vector and ciphertext */
                    if ((fis.read(tmp, 0, LEN)) != -1) {
                        cbc.setIV(tmp.clone());
                        ArrayList<byte[]> cph = new ArrayList<>();
                        while ((fis.read(tmp, 0, LEN)) != -1) {
                            cph.add(tmp.clone());
                        }
                        cbc.setCph(cph);
                        fis.close();
                        return true;
                    }
                    /* If the file is empty, assure whether the user wants to decrypt */
                    else {
                        System.out.println("The file you specified is empty!");
                        System.out.println("Would you like to decrypt the file?");
                        if (!jdgLop()) {
                            fis.close();
                            break;
                        }
                    }
                }
                /* If the file doesn't exist, assure whether the user wants to decrypt */
                else {
                    System.out.println("The file you specified doesn't exist!");
                    System.out.println("Would you like to decrypt the file?");
                    if (!jdgLop()) break;
                }
            }
        }
        return false;
    }

    /**
     * Rewrite the plaintext string into specified file. Must be used after filOpt.rdFil().
     * @param str The plaintext string.
     */
    public void rwtFil(String str) throws IOException {
        /* Judge whether process has read ciphertext from the file */
        if (read) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(str);
            bw.flush();
            bw.close();
            System.out.println("\nNow you can open " + filNm + " to check the decrypted ciphertext.");
        }
        else {
            System.out.println("Invalid operation! Used filOpt.rwtFil() before filOpt.rdFil().");
        }
    }

    /**
     * Get the CBC network during current file operation.
     * @return The CBC network being used.
     */
    public cbcWrk getCbc() {
        return cbc;
    }

    /**
     * The circle loop to assure the user's intention.
     * @return If the user confirmed, return true. Otherwise, return false.
     */
    private boolean jdgLop() {
        Scanner scn = new Scanner(System.in);
        String chc;
        do {
            System.out.print("Please enter 'Y'/'N' to indicate yes or no: ");
            chc = scn.nextLine();
        } while ((chc.compareTo("Y") != 0) && (chc.compareTo("N") != 0));
        return (chc.compareTo("Y") == 0);
    }
}
