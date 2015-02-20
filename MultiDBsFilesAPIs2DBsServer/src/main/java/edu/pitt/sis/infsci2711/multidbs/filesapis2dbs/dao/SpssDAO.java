package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.JdbcUtil1;

public class SpssDAO {
public static boolean createTable(ArrayList<String> t) throws SQLException, Exception {
		
		try (Connection connection = JdbcUtil1.getConnection()) {
			
			String sql="create table "+t.get(0)+"( ";
			for(int i=1;i<t.size();i++){
				sql=sql+" "+t.get(i)+" ";
				i++;
				if(t.get(i)=="varchar"){
					sql+="varchar(40)";
					if((i+1)<t.size())
						sql+=",";
				}
				else 
					{sql+=t.get(i);
				if((i+1)<t.size())
					sql+=",";
					}
				
			}
			sql+=");";
			System.out.println(sql);
			try (Statement statement = connection.createStatement()){
				
				boolean flag= statement.execute(sql);
				
				
				
				return flag;
			}
		}
		
	}
	
}
