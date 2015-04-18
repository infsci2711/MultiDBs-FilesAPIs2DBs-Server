package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DatasetRestApiTest {

	@Test
	public void testGetDataset() throws Exception {
		DatasetRestApi datasetRestApi = new DatasetRestApi();
		datasetRestApi.getDataset("person");
		assertEquals(1,1);
	}

}
