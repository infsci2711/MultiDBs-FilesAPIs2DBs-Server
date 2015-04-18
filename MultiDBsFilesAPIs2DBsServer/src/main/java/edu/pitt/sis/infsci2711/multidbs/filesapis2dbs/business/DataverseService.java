package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;

public class DataverseService {
	
	static String dataverseKey = "4f382c12-c057-4a4a-b03c-29bce7203b30";

	public boolean dataverseById(final int id, final String name) throws IOException {
		
		
		String idString =String.valueOf(id);
		
		//Sent rest api by id
		
		//binary inputstream -> save to upload
		
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target(UriBuilder.fromUri("https://apitest.dataverse.org/api/access/datafile/" + idString + "?format=original").build());
//		WebTarget target = c.target(UriBuilder.fromUri("https://apitest.dataverse.org/api/dataverses/db?key=d0eeea84-b226-4f26-9ea6-67eae6247ff5").build());
//      String responseMsg = target.path("rest").path("test").request().get(String.class);
        InputStream responseMsg = target.request().get(InputStream.class);
//        System.out.println(responseMsg);
       
        String uploadedFileLocation = "upload"+ File.separatorChar + name;
   	 
		// save it
		writeToFile(responseMsg, uploadedFileLocation);
		
		FileReader2 fileReader = new FileReader2("upload"+ File.separatorChar + name);
		ArrayList<String> t = fileReader.readSPSSCreat();
		SpssService s = new SpssService();
		boolean flag = false;
		try {
			flag=s.create(t);
			 s.add(fileReader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
        return true;
	}
	
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {
			
			File fileToSaveFile = new File(uploadedFileLocation);
			System.out.println("Location of the upload file:" + fileToSaveFile.getAbsolutePath());
	 
			try (OutputStream out = new FileOutputStream(fileToSaveFile)) {
				
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				
			} catch (IOException e) {
	 
				e.printStackTrace();
			}
	 
		}
	
	
	@Path("{file_name}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public int dataverseByName(@PathParam("file_name") final String file_name) {
		
		//Sent rest api by file_name = trees
		
		//binary inputstream -> save to upload
		
		Client c = ClientBuilder.newClient();
		
		String url = "https://apitest.dataverse.org/api/search?q=" + file_name + "&key=" + dataverseKey;
		
        WebTarget target = c.target(UriBuilder.fromUri(url).build());
//        String responseMsg = target.path("rest").path("test").request().get(String.class);
        String responseMsg = target.request().get(String.class);
       JSONObject json;
       int id = 0;
	try {
		json = new JSONObject(responseMsg);
	       JSONObject data = json.getJSONObject("data");
	       JSONArray item = data.getJSONArray("items");
//	       String line = "";
	       for(int i=0; i<item.length();i++){
	    	   org.json.JSONObject file = item.getJSONObject(i);
	        if( file.has("file_id")){
	  //      	line = "{\"file_id\": \"" + file.getString("file_id") + "\"}";
	        	id = Integer.parseInt(file.getString("file_id"));
	        }
	       }
	       
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       

    //   org.json.JSONObject id = new org.json.JSONObject(line);

		return id;
		
	}
	
}
