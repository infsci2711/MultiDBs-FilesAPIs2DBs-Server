package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void testReadSPSS() {
		FileReader fileReader = new FileReader("upload_files/DatasetTest.sav");
		Boolean result = fileReader.readSPSS();
		assertEquals(true, result);
	}

}
