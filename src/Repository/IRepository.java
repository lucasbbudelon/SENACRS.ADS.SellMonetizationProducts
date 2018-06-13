package Repository;

import Utilities.OperationPackage;

/**
 *
 * @author lucas.budelon
 * @param <T>
 */
public interface IRepository<T> {

    public OperationPackage Insert(T entiti);

    public OperationPackage Update(T entiti);

    public OperationPackage Delete(int id);

    public OperationPackage SearchByPK(int primarykey);

    public OperationPackage SearchByAK(String alternativeKey);

    public OperationPackage SearchAll();
    
    public OperationPackage ValidateDuplicateData(T model);
}
