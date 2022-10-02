package AES;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File operation in the AES algorithm
 * @author Klasnov
 */

public class filOpt {
    private cbcWrk cbc;

    public filOpt(cbcWrk cbc) {
        this.cbc = cbc;
    }

    public static void main(String[] args) throws IOException {
        filOpt fil = new filOpt(new cbcWrk());
        fil.rdFil();
    }

    /**
     * Write the ciphertext into the specified file.
     */
    public void wrtFil() throws IOException {
        boolean ext;
        String filNam;
        File file;
        /* Specify the record file */
        Scanner scn = new Scanner(System.in);
        do {
            System.out.println("\nEnter the file name for saving the ciphertext, as \"test.txt\":");
            filNam = scn.nextLine();
            file = new File(filNam);
            ext = !file.createNewFile();
            /* If the file has already existed, assure whether to cover it */
        } while (ext && !jdgLop());
        /* Output the initialization vector and ciphertext */
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(stdMtx.mtxToAry(cbc.getIV()));
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
        /* Assure whether the user wants to decrypt the specified file */
        System.out.println("\nWould you like to decrypt the file?");
        if (jdgLop()) {
            /* Specify the file to load in */
            String filNm;
            File file;
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
                        cbc.setIntVec(tmp.clone());
                        ArrayList<byte[]> cph = new ArrayList<>();
                        while ((fis.read(tmp, 0, LEN)) != -1) {
                            cph.add(tmp.clone());
                        }
                        cbc.setCph(cph);
                        return true;
                    }
                    /* If the file is empty, assure whether the user wants to decrypt */
                    else {
                        System.out.println("\nThe file you specified is empty!");
                        System.out.println("Would you like to decrypt the file?");
                        if (!jdgLop()) break;
                    }
                }
                /* If the file doesn't exist, assure whether the user wants to decrypt */
                else {
                    System.out.println("\nThe file you specified doesn't exist!");
                    System.out.println("Would you like to decrypt the file?");
                    if (!jdgLop()) break;
                }
            }
        }
        return false;
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
