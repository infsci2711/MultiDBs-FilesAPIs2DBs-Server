package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void testReadSPSS() {
		FileReader fileReader = new FileReader("DatasetTest.sav");
		Boolean result = fileReader.readSPSS();
	    //assertEquals(true, result);
	}

}
