package dal;


public interface DAO<T> {
	public void create(T objet) throws DALException;
	public T selectById(long id ) throws DALException;
}
