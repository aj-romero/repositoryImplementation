package Operation;

import types.ITypes;

public interface ISCF<T extends ITypes> {
    boolean save(T element);
    int count();
    T find(int idx);
}
