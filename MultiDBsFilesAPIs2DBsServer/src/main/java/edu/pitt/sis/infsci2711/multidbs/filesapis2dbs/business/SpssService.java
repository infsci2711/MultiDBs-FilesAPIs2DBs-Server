package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao.PersonDAO;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.dao.SpssDAO;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.models.PersonDBModel;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileTuples;

public class SpssService {
	SpssDAO spssDAO;

	public boolean createTable(ArrayList<String> t) throws SQLException,
			Exception {
		spssDAO = new SpssDAO();
		boolean flag = spssDAO.createTable(t);
		return flag;
	}

	public int add(final FileTuples file) throws SQLException, Exception {

		String fileName = file.getFilepath();
		String tableName = fileName.replace(".sav", "");
		tableName = tableName.replace("upload/", "");

		int res = spssDAO.save(tableName, file.addTuples());

		return res;
	}
}
