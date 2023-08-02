package com.application;

import com.app.Main;
import com.operation.IOperation;
import com.types.ITypes;
import java.util.Scanner;

public abstract class MainApp {
    protected Scanner es = new Scanner(System.in);
    public  void printInstructions(){
        printf("%nOptions avaible: ");
        printf("%n0 - To quit the application");
        printf("%n1 - To save");
        printf("%n2 - To count");
        printf("%n3 - To find");
        printf("%n4 - Return to main options");

        printf("%nEnter your choice[]: ");
    }

    public void printf(String s) {
        System.out.printf(s);
    }

    public  void actions() {
        int action =  checkInput();
        if (action >= 0 && action <= 4) {
            switch (action) {
                case 0 -> {
                    printf("%n See you later!");
                    System.exit(0);
                }
                case 1 -> toSave();
                case 2 -> toCount();
                case 3 -> toFind();
                case 4 -> {
                    Main.main(new String[]{"Arg", " Arg"});
                }
                default -> {
                    printInstructions();
                    actions();
                }
            }
        } else {
            printInstructions();
            printf("%nPlease, type again your choice [0 - 4]: ");
            actions();
        }
    }

    public int checkInput() {
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

    public void showAll(IOperation<? extends ITypes> list){
        for(int i=0; i < list.getAll().size(); i++){
            printf("%n"+i + "- " + list.getAll().get(i).toString());
        }
        printf("%n");
        printf("-".repeat(40));
    }

    public abstract void toSave();
    public abstract void toCount();
    public abstract void toFind();


}
