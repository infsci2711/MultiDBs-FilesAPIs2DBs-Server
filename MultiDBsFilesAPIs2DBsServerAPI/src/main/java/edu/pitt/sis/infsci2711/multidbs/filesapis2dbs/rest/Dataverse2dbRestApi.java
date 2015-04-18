package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;

@Path("dataverse/")
public class Dataverse2dbRestApi<GetMethod> {

//	@Path("{id}")
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//	public Response dataverseById(@PathParam("id") final int id,@PathParam("name") final String name) throws IOException {
//		
//		String idString =String.valueOf(id);
//		
//		//Sent rest api by id
//		
//		//binary inputstream -> save to upload
//		
//		Client c = ClientBuilder.newClient();
//		WebTarget target = c.target(UriBuilder.fromUri("https://apitest.dataverse.org/api/access/datafile/" + idString + "?format=original").build());
////		WebTarget target = c.target(UriBuilder.fromUri("https://apitest.dataverse.org/api/dataverses/db?key=d0eeea84-b226-4f26-9ea6-67eae6247ff5").build());
////      String responseMsg = target.path("rest").path("test").request().get(String.class);
//        InputStream responseMsg = target.request().get(InputStream.class);
////        System.out.println(responseMsg);
//       
//        String uploadedFileLocation = "upload"+ File.separatorChar + name;
//   	 
//		// save it
//		writeToFile(responseMsg, uploadedFileLocation);
//		
//		FileReader2 fileReader = new FileReader2("upload"+ File.separatorChar + name);
//		ArrayList<String> t = fileReader.readSPSSCreat();
//		SpssService s = new SpssService();
//		boolean flag = false;
//		try {
//			flag=s.createTable(t);
//			 s.add(fileReader);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			if(flag==true){
//				System.out.println("success!");
//			}
//		}
//		
//		
//        return Response.status(200).entity("{\"msg\" : \"Download Success\"}").build();
//	}
//	
//	private void writeToFile(InputStream uploadedInputStream,
//			String uploadedFileLocation) {
//			
//			File fileToSaveFile = new File(uploadedFileLocation);
//			System.out.println("Location of the upload file:" + fileToSaveFile.getAbsolutePath());
//	 
//			try (OutputStream out = new FileOutputStream(fileToSaveFile)) {
//				
//				int read = 0;
//				byte[] bytes = new byte[1024];
//				while ((read = uploadedInputStream.read(bytes)) != -1) {
//					out.write(bytes, 0, read);
//				}
//				out.flush();
//				
//			} catch (IOException e) {
//	 
//				e.printStackTrace();
//			}
//	 
//		}
	
		
		
	}
    

