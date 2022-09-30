import AES.*;

import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("***************************$ Statement information $*******************************");
        System.out.println("Copyright Notice: Spread and commercial use without authorization are prohibited!");
        System.out.println("Usage Notice: This procedure is the AES password demonstration procedure.");
        System.out.println("***************************$ Statement information $*******************************");
        System.out.println("================ AES cryptographic algorithm program demonstration ================\n");

        String ipt;
        while (true) {
            System.out.println("Enter the key which contains 16 chars: ");
            Scanner scn = new Scanner(System.in);
            ipt = scn.nextLine();
            int len = ipt.length();
            if (len != 16) {
                System.out.println("Incorrect length! The key must contain 16 char.");
            }
            else {
                System.out.println("The key you input: " + ipt);
                break;
            }
        }
    }
}
