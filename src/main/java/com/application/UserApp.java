package com.application;

import com.operation.DataStore;
import com.operation.IOperation;
import com.types.User;

public class UserApp extends MainApp{
    private final IOperation<User> users = new DataStore<>();
    @Override
    public void toSave() {
        User u = new User();
        printf("Please, type the username: ");
        u.setUserName(es.nextLine());
        printf("Please, type the first name: ");
        u.setFirstName(es.nextLine());
        printf("Please, type the last name: ");
        u.setLastName(es.nextLine());
        if(validate(u)){
            if(users.save(u))
                printf("The User " + u.getUserName() + " was successfully saved");
        }else {
            printf("The User " + u.getUserName() + " is already saved");
        }
        index();
    }
    private boolean validate(User u){
        boolean result = false;
        if(!users.getAll().isEmpty()){
            for(int i=0; i < users.getAll().size(); i++){
                User db = users.getAll().get(i);
                result =  !db.getUserName().equalsIgnoreCase(u.getUserName());
            }
        }
        else
            result = true;

        return result;
    }
    public void index(){
        if(!users.getAll().isEmpty()){
            printf("%n%nUsers: %n");
            printf("-".repeat(40));
            showAll(users);
        }
        printInstructions();
        actions();
    }

    @Override
    public void toCount() {
        printf("The count of Users in store is: " + users.count());
        index();
    }

    @Override
    public void toFind() {
        if(!users.getAll().isEmpty()){
            printf("%nPlease, enter the ID of the User: ");
            int id = checkInput();
            User u = users.find(id);
            if(u != null){
                printf("%n"+id + "- User Name: " + u.getUserName() + ", first name: " + u.getFirstName()
                        +", last name: " + u.getLastName());
                index();
            }
            else {
                printf("Gring, I could not found that ID");
                index();
            }
        }
        else{
            printf("Gring, I don't have any user to show you");
            index();
        }
    }
}
