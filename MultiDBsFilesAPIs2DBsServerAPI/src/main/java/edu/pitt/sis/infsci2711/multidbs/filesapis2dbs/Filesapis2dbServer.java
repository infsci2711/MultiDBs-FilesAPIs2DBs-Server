package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs;

import java.io.File;

import edu.pitt.sis.infsci2711.multidbs.utils.JerseyJettyServer;
import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

public class Filesapis2dbServer {

	private final static String PROPERTY_PORT = "port";
	private final static int DEFAULT_PORT = 7654;
	
	public static void main(final String[] args) throws Exception {
//		JerseyJettyServer server = new JerseyJettyServer(7654, "edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest");
//		server.start();
		
		if (args.length > 0) {
			String propertiesFilePath = args[0];
			File propertiesFile = new File(propertiesFilePath);
			PropertiesManager.getInstance().loadProperties(propertiesFile);
		}
		
		final JerseyJettyServer server = new JerseyJettyServer(PropertiesManager.getInstance().getIntProperty(PROPERTY_PORT, DEFAULT_PORT), 
				"edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest");
		
		server.start();
	}
}
