package dal;



import bo.*;

public class DAOFactory {

	public static DAO<Actor> getActorDAO()
	{
		return new ActorDAO();
	}

	public static DAO<Director> getDirectorDAO()
	{
		return new DirectorDAO();
	}

	public static DAO<Movie> getMovieDAO()
	{
		return new MovieDAO();
	}

	public static DAO<Genre> getGenreDAO()
	{
		return new GenreDAO();
	}

	public static DAO<Country> getPaysDAO()
	{
		return new PaysDAO();
	}

	public static DAO<Role> getRoleDAO()
	{
		return new RoleDAO();
	}
	public static DAO<ShootingLocation> getShootingLocationDAO()
	{
		return new ShootingLocationDAO();
	}
}
