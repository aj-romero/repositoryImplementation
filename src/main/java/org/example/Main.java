package org.example;
import Operation.DataStore;
import Operation.IOperation;
import types.*;

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
        int action = 0;
        action = checkInput();
        if (action >= 0 && action <= 3) {
            switch (action) {
                case 0:
                    printf("%n See you later!");
                    System.exit(0);
                    break;
                case 1:
                    ProductsUI.index(products);
                    break;
                case 2:
                    UsersUI.index(users);
                    break;
                case 3:
                    CountriesUI.index(countries);
                    break;
                default:
                    printInstructions();
                    actions();
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
                b = true;
                printf("El dato no es valido, favor ingreselo de nuevo");
            }
        }
        return acc;
    }
}

