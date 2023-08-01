package com.app;
import com.operation.IOperation;
import com.types.Product;

public class ProductsUI {
    private ProductsUI(){}
    protected static void index(IOperation<Product> list){

        if(list.getAll() !=null){
            Main.printf("%n%nProducts: %n");
            Main.printf("-".repeat(40));
            showAllProducts(list);
        }
        printInstructions();
        actions(list);
    }

    protected static void showAllProducts(IOperation<Product> list){
        for(int i=0; i < list.getAll().size(); i++){
            Main.printf("%n"+i + "- Product Name: " + list.getAll().get(i).getName()
                                +" stock: " + list.getAll().get(i).getStock());
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
    protected static void actions(IOperation<Product> list) {
        int action =  Main.checkInput();
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

    protected static void toSave(IOperation<Product> list){
        Product p = new Product();
        Main.printf("Please, type the name of the product: ");
        p.setName(Main.es.nextLine());
        Main.printf("Please, type the stock of the product " + p.getName() + ": ");
        p.setStock(Main.checkInput());
        if(findNew(list, p)){
            if(list.save(p))
                Main.printf("The product " + p.getName() + " was successfully saved");
            else
                Main.printf("The product was not add, try again");
        }else {
            Main.printf("The product " + p.getName() + " is already saved");
        }
        index(list);
    }
    protected static void toCount(IOperation<Product> list){
        Main.printf("The count of products in store is: " + list.count());
        index(list);
    }

    protected static void toFind(IOperation<Product> list){
        if(list.getAll() !=null){
            Main.printf("%nPlease, enter the ID of the product: ");
            int id = Main.checkInput();
            Product product = list.find(id);
            if(product != null){
                Main.printf("%n"+id + "- Product Name: " + product.getName()
                        +" stock: " + product.getStock());
                index(list);
            }
            else{
                Main.printf("Gring, I could not found that ID");
                index(list);
            }

        }
        else{
            Main.printf("Gring, I don't have any product to show you");
            index(list);
        }
    }
    protected static boolean findNew(IOperation<Product> list, Product nproduct){
        boolean result = false;
        if(list.getAll() != null){
            for(int i=0; i < list.getAll().size(); i++){
                Product db = list.getAll().get(i);
                result =  !db.getName().equalsIgnoreCase(nproduct.getName());
            }
        }
        else
            result = true;

        return result;
    }
}
