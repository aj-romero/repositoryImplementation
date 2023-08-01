package com.operation;

import com.types.ITypes;
import java.util.List;

public interface IOperation<T extends ITypes> extends ISCF<T>{

    List<T> getAll();

}

