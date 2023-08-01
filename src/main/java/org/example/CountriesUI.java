package org.example;

import Operation.DataStore;
import Operation.IOperation;
import types.Country;

public class CountriesUI {
    private CountriesUI(){

    }
    protected static void index(IOperation<Country> list){

        if(list.getAll() !=null){
            Main.printf("%n%nCountries: %n");
            Main.printf("-".repeat(40));
            showAll(list);
        }
        printInstructions();
        actions(list);
    }

    protected static void showAll(IOperation<Country> list){
        for(int i=0; i < list.getAll().size(); i++){
            Main.printf("%n"+i + "- Country Name: " + list.getAll().get(i).getName()
                                +", iso code: " + list.getAll().get(i).getIsoCode());
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
    protected static void actions(IOperation<Country> list) {
        int action = 0;
        action = Main.checkInput();
        if (action >= 0 && action <= 4) {
            switch (action) {
                case 0:
                    Main.printf("%n See you later!");
                    System.exit(0);
                    break;
                case 1:
                    toSave(list);
                    break;
                case 2:
                    toCount(list);
                    break;
                case 3:
                    toFind(list);
                    break;
                case 4:
                    Main.printInstructions();
                    Main.actions();
                    break;
                default:
                    printInstructions();
                    actions(list);
            }
        } else {
            printInstructions();
            Main.printf("%nPlease, type again your choice [0 - 4]: ");
            actions(list);
        }
    }

    protected static void toSave(IOperation<Country> list){
        Country u = new Country();
        Main.printf("Please, type the country name: ");
        u.setName(Main.es.nextLine());
        Main.printf("Please, type the iso code: ");
        u.setIsoCode(Main.es.nextLine());
        if(findNew(list, u)){
            if(list.save(u))
                Main.printf("The Country " + u.getName() + " was successfully saved");
        }else {
            Main.printf("The Country " + u.getName() + " is already saved");
        }
        index(list);
    }
    protected static void toCount(IOperation<Country> list){
        Main.printf("The count of countries in store is: " + list.count());
        index(list);
    }

    protected static void toFind(IOperation<Country> list){
        if(list.getAll() !=null){
            Main.printf("%nPlease, enter the ID of the Country: ");
            int id = Main.checkInput();
            Country c = list.find(id);
            if(c != null){
                Main.printf("%n"+id + "- Country Name: " + c.getName() + ", iso code: " + c.getIsoCode()
                        );
                index(list);
            }
            else {
                Main.printf("Gring, I could not found that ID");
                index(list);
            }
        }
        else{
            Main.printf("Gring, I don't have any Country to show you");
            index(list);
        }
    }

    protected static boolean findNew(IOperation<Country> list, Country newCountry){
        boolean result = false;
        if(list.getAll() != null){
            for(int i=0; i < list.getAll().size(); i++){
                Country db = list.getAll().get(i);
                result =  !db.getName().equalsIgnoreCase(newCountry.getName());
            }
        }
        else
            result = true;

        return result;
    }
}
