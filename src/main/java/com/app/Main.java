package com.app;
import com.operation.DataStore;
import com.operation.IOperation;
import com.types.Country;
import com.types.Product;
import com.types.User;

import java.util.Scanner;

public class Main {
    private static final IOperation<Product> products = new DataStore<>();
    private static final IOperation<User> users = new DataStore<>();
    private static final IOperation<Country> countries = new DataStore<>();
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
                case 1 -> ProductsUI.index(products);
                case 2 -> UsersUI.index(users);
                case 3 -> CountriesUI.index(countries);
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

