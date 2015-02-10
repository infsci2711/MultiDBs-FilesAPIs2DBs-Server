package edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;


@Path("Filesapis2db/")
public class Filesapis2dbRestApi {
	
	@Path("HelloFilesapis2db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld(){
		return Response.status(200).entity("{\"msg\" : \"Hello Filesapis2db\"}").build();
	}

	
	//TODO You should implement this REST API. It should be able to get the file passed by Client. And use "edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader" class to parse it. 
	//FileReaderTest is working. You can build a FileReader('filepath') object and call the readSPSS() to read the SPSS file.
	@Path("SPSSUpload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response SPSSUpload() {
		
		System.out.println("111111111111");
		String output = "Success!";
		
//The following comment part is what I tried to get the file. But I failed. Now it's your work now!!
		
//		String filePath = "upload/" + contentDispositionHeader.getFileName();
//
//		// save the file to the server
//		saveFile(fileInputStream, filePath);
//		String output = "File saved to server location : " + filePath;
		
//		FormDataBodyPart filePart = form.getField("file");
//		ContentDisposition headerOfFilePart =  filePart.getContentDisposition();
//		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
//		String filePath = "upload/" + headerOfFilePart.getFileName();
//		saveFile(fileInputStream, filePath);
//		String output = "File saved to server location : " + filePath;
		
		return Response.status(200).entity(output).build();

	}
//	
//	private void saveFile(InputStream uploadedInputStream, String serverLocation) {
//		try {
//			OutputStream outpuStream = new FileOutputStream(new File(
//					serverLocation));
//			int read = 0;
//			byte[] bytes = new byte[1024];
//			outpuStream = new FileOutputStream(new File(serverLocation));
//			while ((read = uploadedInputStream.read(bytes)) != -1) {
//				outpuStream.write(bytes, 0, read);
//			}
//			outpuStream.flush();
//			outpuStream.close();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//	}

	
}
