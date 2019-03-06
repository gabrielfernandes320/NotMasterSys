package			database;

import			java.sql.Connection;
import			java.sql.DriverManager;
import			java.sql.SQLException;

public class		ConnectionFactory {
	
	/**
	Returns a PostgreSQL database connection.
	@param	as_database
		Database name.
	@param	as_user
		Connetion user.
	@param	as_pass
		Connection p455w0rd. 
	@return
		PostgreSQL Connecttion.
	*/
	public	static
	Connection		getConnection
				(
					String			as_database,
					String			as_user,
					String			as_pass
				)
	{
		//
		// Returns database connection
		//
		return	getConnection
			(
				"localhost",
				"5432",
				as_database,
				as_user,
				as_pass
			);
	}
	
	/**
	Returns a PostgreSQL database connection.
	@param	as_adress
		Server adress(Computer name or IP).
	@param	as_port
		Server port.
	@param	as_database
		Database name.
	@param	as_user
		Connetion user.
	@param	as_pass
		Connection p455w0rd. 
	@return
		PostgreSQL Connecttion.
	*/
	public	static
	Connection		getConnection
				(
					String			as_adress,
					String			as_port,
					String			as_database,
					String			as_user,
					String			as_pass
				)
	{
		try
		{
			return	DriverManager.getConnection
				(
					"jdbc:postgresql://"
						+	as_adress
						+	":"
						+	as_port
						+	"/"
						+	as_database,
					as_user,
					as_pass
				);
		}
		catch (SQLException	e)
		{
			throw	new RuntimeException(e);
		}

	}
}
