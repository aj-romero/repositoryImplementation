package Operation;
import types.ITypes;
import java.util.ArrayList;
import java.util.List;

public class DataStore<T extends ITypes> implements IOperation<T>{
    List<T> appList = new ArrayList<>();
    @Override
    public boolean save(T element) {
        if(appList.contains(element)){
            return false;
        }
        else {
            appList.add(element);
        }
        return true;
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
        if(appList.isEmpty()){
            return null;
        }
        else{
            return this.appList;
        }
    }

}
