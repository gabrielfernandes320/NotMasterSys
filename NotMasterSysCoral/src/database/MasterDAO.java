package database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

public abstract class MasterDAO {
	
	public abstract
	List<Object>		SelectAll()
		throws			SQLException;
	
	public abstract
	Object				Select(Object parameter)
		throws			SQLException;
	
	public abstract void Update(Object parameter) throws SQLException;
	
	public abstract void Insert(Object parameter) throws SQLException;
	
	public abstract
	int				Delete(Object parameter)
		throws			SQLException;
	
	public List<Object> SelectAll(Object parameter) throws SQLException {
		return null;
	}
	
	public
	void		Set(PreparedStatement pst, int position, Object value) 
			throws SQLException 
	{
		
		if (value == null) {
			pst.setNull(position, Types.NULL);
		}
		else if (value instanceof String) {
			pst.setString(position, (String) value);
		}
		else if (value instanceof Date) {
			pst.setDate(position, new java.sql.Date(((Date)value).getTime()));			
			// pst.setTimestamp(position, new Timestamp(((java.util.Date) value).getTime()));
		}
		else if (value instanceof Character) {
			pst.setString(position,((Character)value).toString());
		}
		else if	(value	instanceof	Boolean) {
			pst.setString(position,((Boolean) value).booleanValue() ? "S" : "N");
		}
		else if	(value	instanceof	BigDecimal) {
			pst.setBigDecimal(position,(BigDecimal) value);
		}
		else if (value instanceof Double) {
			pst.setDouble(position, (Double) value);
		}
	}

	

}
















