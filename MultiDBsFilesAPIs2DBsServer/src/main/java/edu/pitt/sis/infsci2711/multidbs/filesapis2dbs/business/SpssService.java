package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao.SpssDAO;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models.TableDBModel;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;

public class SpssService {
	SpssDAO spssDAO;

	public boolean create(ArrayList<String> t) throws SQLException,
			Exception {
		spssDAO = new SpssDAO();
		boolean flag = spssDAO.create(t);
		return flag;
	}

	public int add(final FileReader2 file) throws SQLException, Exception {

		String fileName = file.getFilepath();
		String tableName = fileName.replace(".sav", "");
		tableName = tableName.replace("upload" + File.separatorChar, "");

		int res = spssDAO.save(tableName, file.addTuples());

		return res;
	}
	public TableDBModel readtable(String tbname) throws Exception{
		spssDAO = new SpssDAO();
		TableDBModel table=spssDAO.readtable(tbname);
		return table;
	}
}
