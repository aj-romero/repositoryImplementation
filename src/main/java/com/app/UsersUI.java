package com.app;

import com.operation.IOperation;
import com.types.User;

public class UsersUI {
    private UsersUI(){

    }
    protected static void index(IOperation<User> list){

        if(list.getAll() !=null){
            Main.printf("%n%nUsers: %n");
            Main.printf("-".repeat(40));
            showAll(list);
        }
        printInstructions();
        actions(list);
    }

    protected static void showAll(IOperation<User> list){
        for(int i=0; i < list.getAll().size(); i++){
            Main.printf("%n"+i + "- User Name: " + list.getAll().get(i).getUserName()
                                +", last name: " + list.getAll().get(i).getLastName());
        }
        Main.printf("%n");
        Main.printf("-".repeat(40));
    }

    protected static void printInstructions(){
        Main.printf("%nOptions avaible: ");
        Main.printf("%n0 - To quit the application");
        Main.printf("%n1 - To save");
        Main.printf("%n2 - To count");
        Main.printf("%n3 - To find");
        Main.printf("%n4 - Return to main options");

        Main.printf("%nEnter your choice[]: ");
    }
    protected static void actions(IOperation<User> list) {
        int action = Main.checkInput();
        if (action >= 0 && action <= 4) {
            switch (action) {
                case 0 -> {
                    Main.printf("%n See you later!");
                    System.exit(0);
                }
                case 1 -> toSave(list);
                case 2 -> toCount(list);
                case 3 -> toFind(list);
                case 4 -> {
                    Main.printInstructions();
                    Main.actions();
                }
                default -> {
                    printInstructions();
                    actions(list);
                }
            }
        } else {
            printInstructions();
            Main.printf("%nPlease, type again your choice [0 - 4]: ");
            actions(list);
        }
    }

    protected static void toSave(IOperation<User> list){
        User u = new User();
        Main.printf("Please, type the username: ");
        u.setUserName(Main.es.nextLine());
        Main.printf("Please, type the first name: ");
        u.setFirstName(Main.es.nextLine());
        Main.printf("Please, type the last name: ");
        u.setLastName(Main.es.nextLine());
        if(findNew(list, u)){
            if(list.save(u))
                Main.printf("The User " + u.getUserName() + " was successfully saved");
        }else {
            Main.printf("The User " + u.getUserName() + " is already saved");
        }
        index(list);
    }
    protected static void toCount(IOperation<User> list){
        Main.printf("The count of Users in store is: " + list.count());
        index(list);
    }

    protected static void toFind(IOperation<User> list){
        if(list.getAll() !=null){
            Main.printf("%nPlease, enter the ID of the User: ");
            int id = Main.checkInput();
            User u = list.find(id);
            if(u != null){
                Main.printf("%n"+id + "- User Name: " + u.getUserName() + ", first name: " + u.getFirstName()
                        +", last name: " + u.getLastName());
                index(list);
            }
            else {
                Main.printf("Gring, I could not found that ID");
                index(list);
            }
        }
        else{
            Main.printf("Gring, I don't have any user to show you");
            index(list);
        }
    }

    protected static boolean findNew(IOperation<User> list, User newUser){
        boolean result = false;
        if(list.getAll() != null){
            for(int i=0; i < list.getAll().size(); i++){
                User db = list.getAll().get(i);
                result =  !db.getUserName().equalsIgnoreCase(newUser.getUserName());
            }
        }
        else
            result = true;

        return result;
    }
}
