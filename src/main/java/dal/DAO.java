package dal;


public interface DAO<T> {
	public void create(T objet) throws DALException;


}
