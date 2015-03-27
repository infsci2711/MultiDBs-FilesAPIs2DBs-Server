package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;


@Path("Filesapis2db/")
public class Filesapis2dbRestApi {

	@Path("HelloFilesapis2db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		return Response.status(200)
				.entity("{\"msg\" : \"Hello Filesapis2db\"}").build();
	}


	@Path("SPSSUpload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException{
	 
			String uploadedFileLocation = "upload"+ File.separatorChar + fileDetail.getFileName();
	 
			// save it
			writeToFile(uploadedInputStream, uploadedFileLocation);
			
			String output = "File uploaded to : " + uploadedFileLocation;
			System.out.println(output);
			System.out.println("hello!");
			create(uploadedFileLocation);  //create table and tuples
			
			return Response.status(200).entity(output).build();
	 
		}
	
		private  void  create(String uploadedFileLocation) throws IOException{
			FileReader2 fileReader = new FileReader2(uploadedFileLocation);
			ArrayList<String> t = fileReader.readSPSSCreat();
			SpssService s = new SpssService();
			boolean flag=false;
			try {
				flag=s.createTable(t);
				 s.add(fileReader);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(flag==true){
					System.out.println("success!");
				}
			}
			
		}
	 
		// save uploaded file to new location
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

}
