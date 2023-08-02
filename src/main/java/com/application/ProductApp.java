package com.application;

import com.operation.DataStore;
import com.operation.IOperation;
import com.types.Product;

public class ProductApp extends MainApp{
    private final IOperation<Product> products = new DataStore<>();
    @Override
    public void toSave() {
        Product p = new Product();
        printf("Please, type the name of the product: ");
        p.setName(es.nextLine());
        printf("Please, type the stock of the product " + p.getName() + ": ");
        p.setStock(checkInput());
        if(checkProduct(p)){
            if(products.save(p))
                printf("The product " + p.getName() + " was successfully saved");
            else
                printf("The product was not add, try again");
        }else {
            printf("The product " + p.getName() + " is already saved");
        }
        index();
    }
    public void index(){

        if(!products.getAll().isEmpty()){
            printf("%n%nProducts: %n");
            printf("-".repeat(40));
            showAll(products);
        }
        printInstructions();
        actions();
    }

    private boolean checkProduct(Product p){
        boolean result = false;
        if(!products.getAll().isEmpty()){
            for(int i=0; i < products.getAll().size(); i++){
                Product db = products.getAll().get(i);
                result =  !db.getName().equalsIgnoreCase(p.getName());
            }
        }
        else
            result = true;

        return result;
    }

    @Override
    public void toCount() {
        printf("The count of products in store is: " + products.count());
        index();
    }

    @Override
    public void toFind() {
        if(!products.getAll().isEmpty()){
            printf("%nPlease, enter the ID of the product: ");
            int id = checkInput();
            Product product = products.find(id);
            if(product != null){
                printf("%n"+id + "- Product Name: " + product.getName()
                        +" stock: " + product.getStock());
                index();
            }
            else{
                printf("Gring, I could not found that ID");
                index();
            }

        }
        else{
            printf("Gring, I don't have any product to show you");
            index();
        }
    }
}
