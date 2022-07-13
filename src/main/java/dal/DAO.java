package dal;


import dal.DALException;

import java.util.List;

public interface DAO<T> {
	public void create(T objet) throws DALException;
	public void update(T objet) throws DALException;
	public void delete(T objet)throws DALException;
	public List<T> selectAll() throws DALException;
	public T selectById(long id ) throws DALException;
}
