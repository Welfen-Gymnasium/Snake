package onl.deepspace.mysql;

import java.sql.ResultSet;
import java.sql.Statement;

public class SqlExe {
	
	public static void exeSQL(String query, String database){
		
		try{
			Connector.dblogin(database);
			
			Statement sta = Connector.st;
			
			sta.execute(query);
			
			Connector.conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet getSelect(String query, String database){
		
		Connector.dblogin(database);
		ResultSet s = null;
		
		try{
			s = Connector.st.executeQuery(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return s;		
	}
	
}
