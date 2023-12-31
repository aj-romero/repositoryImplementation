package com.operation;
import com.types.ITypes;
import java.util.ArrayList;
import java.util.List;

public class DataStore<T extends ITypes> implements IOperation<T>{
    List<T> appList = new ArrayList<>();

    @Override
    public boolean save(T element) {
        return appList.add(element);
    }

    @Override
    public int count() {
        return appList.size();
    }

    @Override
    public T find(int idx) {
        if(appList.isEmpty()){
            return null;
        }
        else{
            if(appList.size() > idx && idx >= 0){
                return appList.get(idx);
            }
            else return null;
        }

    }
    @Override
    public List<T> getAll(){
        return appList;
    }


}
