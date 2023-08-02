package com.app;
import com.application.CountryApp;
import com.application.ProductApp;
import com.application.UserApp;

import java.util.Scanner;

public class Main {
    private static final ProductApp appProduct = new ProductApp();
    private static final CountryApp appCountry = new CountryApp();
    private static final UserApp appUser = new UserApp();
    protected static Scanner es = new Scanner(System.in);
    public static void main(String[] args) {
        printInstructions();
        actions();
    }

    protected static void printInstructions(){
        printf("%nOptions avaible: ");
        printf("%n0 - To quit the application");
        printf("%n1 - To management products");
        printf("%n2 - To management users");
        printf("%n3 - To management countries");
        printf("%nEnter your choice[]: ");
    }
    protected static void printf(String s) {
        System.out.printf(s);
    }
    protected static void actions() {
        int action = checkInput();
        if (action >= 0 && action <= 3) {
            switch (action) {
                case 0 -> {
                    printf("%n See you later!");
                    System.exit(0);
                }
                case 1 -> appProduct.index();
                case 2 -> appUser.index();
                case 3 -> appCountry.index();
                default -> {
                    printInstructions();
                    actions();
                }
            }
        } else {
            printInstructions();
            printf("%nPlease, type again your choice [0 - 3]: ");
            actions();
        }
    }

    protected static int checkInput() {
        int acc = 0;
        boolean b = true;
        while (b) {
            try {
                acc = Integer.parseInt(es.nextLine());
                b = false;
            } catch (Exception e) {
                printf("Please, type again a valid integer[]: ");
            }
        }
        return acc;
    }
}

