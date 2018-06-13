package Business;

import Utilities.OperationPackage;

/**
 *
 * @author lucas.budelon
 * @param <T>
 */
public interface IBusiness<T> {

    public OperationPackage Insert(T entiti);

    public OperationPackage Update(T entiti);

    public OperationPackage Delete(int primarykey);

    public OperationPackage Delete(String alternativeKey);
    
    public OperationPackage Get(int primarykey);

    public OperationPackage Get(String alternativeKey);

    public OperationPackage GetAll();
}
