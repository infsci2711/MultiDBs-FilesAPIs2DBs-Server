package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils;

// http://spss.pmstation.com/spssr_index.jsp
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

import com.pmstation.spss.reader.SPSSReader;
import com.pmstation.spss.variable.DateVariable;
import com.pmstation.spss.variable.NumericVariable;
import com.pmstation.spss.variable.StringVariable;

public class FileReader2 {

	private String filepath = null;
	private SPSSReader reader;

	public FileReader2(String filepath) throws IOException {
		this.filepath = filepath;
		reader = new SPSSReader(filepath, null);
	}

	public ArrayList<String> readSPSSCreat() {
		ArrayList<String> tableList = new ArrayList();
		// if (!checkOrShowUsage(filepath))
		// return false;
		try {
			// All data read as GMT so we'll set default time zone
			// to suppress output conversion
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

			// init some basic profiling
			Date start = new Date();

			// Initializing reader with file path and empty charset
		
			// profiling checkpoint
			Date parsed = new Date();
			// Iterate thru variable and print them
			// see SPSSVariable.toString()
			// Iterator it = reader.getVariables().iterator();
			Vector var = reader.getVariables();
			String sf = filepath.replace(".sav", "");
			sf = sf.replace("upload" + File.separatorChar, "");
			tableList.add(sf);
			for (int j = 0; j < var.size(); j++) {
				String s;
				String[] a;
				if (var.get(j) instanceof StringVariable) {
					StringVariable s1 = (StringVariable) var.get(j);
					s = s1.toString();
					a = s.split(":"); // a.length=2
					a[1] = a[1].replace("(", "");
					a[1] = a[1].replace(")", "");
					a[1] = a[1].replace("|", "");
					a[1] = a[1].replace("{", "");
					a[1] = a[1].replace("}", "");

					tableList.add(a[1]);
					tableList.add("varchar");
				} else if (var.get(j) instanceof NumericVariable) {
					NumericVariable s2 = (NumericVariable) var.get(j);
					s = s2.toString();
					a = s.split(":");
					a[1] = a[1].replace("(", "");
					a[1] = a[1].replace(")", "");
					a[1] = a[1].replace("|", "");
					a[1] = a[1].replace("{", "");
					a[1] = a[1].replace("}", "");
					tableList.add(a[1]);
					tableList.add("varchar");
				} else {
					DateVariable s3 = (DateVariable) var.get(j);
					s = s3.toString();
					a = s.split(":");
					a[1] = a[1].replace("(", "");
					a[1] = a[1].replace(")", "");
					a[1] = a[1].replace("|", "");
					a[1] = a[1].replace("{", "");
					a[1] = a[1].replace("}", "");
					tableList.add(a[1]);
					tableList.add("varchar");
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tableList;
	}
	
	public ArrayList<ArrayList<String>> addTuples(){
		ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();
		
		try {
			
			int row = 0;
			while (reader.read()) {
				ArrayList<String> tuples = new ArrayList<String>();
				// cycling thru variables to get corresponding values
				for (int i = 0; i < reader.getVariables().size(); i++) {
					// get current row variable value
					Object res = reader.getValue(i);
	                
					// print result to System.out
					if (res instanceof String) {
						System.out.print(((String) res).trim() + " ");
						tuples.add(res.toString());
					} else if (res instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy.MM.dd HH:mm:ss Z");
						System.out.print(sdf.format(res) + " ");
						tuples.add(res.toString());
					} else{
						System.out.print(res + " ");
						tuples.add(res.toString());
					}
				}
				System.out.println();
				rows.add(tuples);
				row++;
			}
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rows;
		
	}
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


}
