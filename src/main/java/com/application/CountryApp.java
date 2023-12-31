package com.application;

import com.operation.DataStore;
import com.operation.IOperation;
import com.types.Country;

public class CountryApp extends MainApp{
    private final IOperation<Country> countries = new DataStore<>();
    @Override
    public void toSave() {
        Country u = new Country();
        printf("Please, type the country name: ");
        u.setName(es.nextLine());
        printf("Please, type the iso code: ");
        u.setIsoCode(es.nextLine());
        if(validate(u)){
            if(countries.save(u))
                printf("The Country " + u.getName() + " was successfully saved");
        }else {
            printf("The Country " + u.getName() + " is already saved");
        }
        index();
    }
    public void index(){

        if(!countries.getAll().isEmpty()){
            printf("%n%nCountries: %n");
            printf("-".repeat(40));
            showAll(countries);
        }
        printInstructions();
        actions();
    }

    private boolean validate(Country p){
        boolean result = false;
        if(!countries.getAll().isEmpty()){
            for(int i=0; i < countries.getAll().size(); i++){
                Country db = countries.getAll().get(i);
                result =  !db.getName().equalsIgnoreCase(p.getName());
            }
        }
        else
            result = true;

        return result;
    }

    @Override
    public void toCount() {
        toCount(countries, "The count of products in store is: ");
        index();
    }

    @Override
    public void toFind() {
        toFind(countries, "\nPlease, enter the ID of the Country: ");
        index();
    }
}
