package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

import com.pmstation.spss.reader.SPSSReader;

public class FileReader {

	private String filepath = null;
	
	public FileReader(String filepath){
		this.filepath = filepath;
	}
	
	public boolean readSPSS(){
		if (!checkOrShowUsage(filepath))
			return false;
		try {
			// All data read as GMT so we'll set default time zone
			// to suppress output conversion
			TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

			// init some basic profiling
			Date start = new Date();

			// Initializing reader with file path and empty charset
			SPSSReader reader = new SPSSReader(filepath, null);
			// profiling checkpoint
			Date parsed = new Date();
			// Iterate thru variable and print them
			// see SPSSVariable.toString()
			Iterator it = reader.getVariables().iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			// profiling checkpoint
			Date variablesPrinted = new Date();
			// will read data section one row in time
			while (reader.read()) {
				// cycling thru variables to get corresponding values
				for (int i = 0; i < reader.getVariables().size(); i++) {
					// get current row variable value
					Object res = reader.getValue(i);

					// print result to System.out
					if (res instanceof String) {
						System.out.print(((String) res).trim() + " ");
					} else if (res instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy.MM.dd HH:mm:ss Z");
						System.out.print(sdf.format(res) + " ");
					} else
						System.out.print(res + " ");
				}
				System.out.println();
			}
			// profiling checkpoint
			Date finish = new Date();
			// printing profiler data to System.out
			System.out.println("Parsing dictionary: "
					+ makeTimeDiffString(start, parsed));
			System.out.println("Printing out variables: "
					+ makeTimeDiffString(parsed, variablesPrinted));
			System.out.println("Parsing and printing " + reader.getRowsRead()
					+ " rows of data: "
					+ makeTimeDiffString(variablesPrinted, finish));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	// /Make Date diff string
	private static String makeTimeDiffString(Date start, Date finish) {
		return (finish.getTime() - start.getTime()) + " ms";
	}
	// /Check params value and prints usage if something wrong.
	private static boolean checkOrShowUsage(String filepath) {
		if (filepath == null) {
			System.out.println("Usage: java Demo2 <existing SPSS file>");
			return false;
		} else
			return true;
	}
}
