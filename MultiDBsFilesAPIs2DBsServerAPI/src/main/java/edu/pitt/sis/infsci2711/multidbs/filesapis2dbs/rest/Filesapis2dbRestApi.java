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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.google.gson.Gson;

import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.DataverseService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.business.SpssService;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.utils.FileReader2;
import edu.pitt.sis.infsci2711.multidbs.filesapis2dbs.viewModels.DatasourceViewModel;
import edu.pitt.sis.infsci2711.multidbs.utils.JerseyClientUtil;
import edu.pitt.sis.infsci2711.multidbs.utils.PropertiesManager;

@Path("Filesapis2db/")
public class Filesapis2dbRestApi {

	final Logger logger = LogManager.getLogger(Filesapis2dbRestApi.class.getName());
	
	@Path("HelloFilesapis2db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {
		
		
		return Response.status(200)
				.entity("{\"msg\" : \"Hello Filesapis2db\"}").build();
	}
	
	@Path("DataverseName/Hello/Hello")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response dataverseDownloadHello(){
		return Response.status(200).entity("{\"msg\" : \"Hello DataverseDownload\"}").build();
		
	}
	
	@Path("DataverseName/{name}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response dataverseDownload(@PathParam("name") final String name){
		boolean result = false;
		DataverseService dataverSerivce = new DataverseService();
		int spss_id = dataverSerivce.dataverseByName(name);
		try {
			DatasourceViewModel datasourceViewModel = new DatasourceViewModel("MySQL", 
					PropertiesManager.getInstance().getStringProperty("ip"), 
					PropertiesManager.getInstance().getIntProperty("database"), 
					"dataverse", "dataverse", name.toLowerCase(), name.toLowerCase(), name.toLowerCase());
			
			logger.info(String.format("IP: %s, Port: %s, DBType: %s, Username: %s, Password: %s, DBName: %s", 
					datasourceViewModel.getIpAddress(), datasourceViewModel.getPort(),
					datasourceViewModel.getDbType(),datasourceViewModel.getUsername(), datasourceViewModel.getPassword(),
					datasourceViewModel.getDbName()));
			
			Response result2 = JerseyClientUtil.doPut(PropertiesManager.getInstance().getStringProperty("metastore.rest.base"), 
					PropertiesManager.getInstance().getStringProperty("metastore.rest.addDatasource"), datasourceViewModel);
		}
		catch (Exception e) {
			logger.error("Request to metastore failed:", e);
		}
		
		
		if(result == true){
			return Response.status(200).entity("{\"msg\" : \"Download Success\"}").build();
		}else{
			return Response.status(200).entity("{\"msg\" : \"Download Fail\"}").build();
		}
	}

	
//	@Path("DataverseName/{name}/{datatype}/{dataverseName}")
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response dataverseDownloadRevise(@PathParam("name") final String name,@PathParam("datatype") final String datatype, @PathParam("dataverseName") final String dataverseName){
//		boolean result = false;
//		DataverseService dataverSerivce = new DataverseService();
//		ArrayList< String> filesArray=new ArrayList<String>();
//		filesArray = dataverSerivce.dataverseByNameRevise(name, dataverseName);
//		try {
//			//result = dataverSerivce.dataverseById(spss_id, name + ".sav");
//			for(int i=0;i<filesArray.size();i++){
//			result=true;
//			//result = dataverSerivce.dataverseById((int)filesArray.get(i), name,datatype,i);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			if(datatype.equals("sav")){
////			DatasourceViewModel datasourceViewModel = new DatasourceViewModel("MySQL", 
////					PropertiesManager.getInstance().getStringProperty("ip"), 
////					PropertiesManager.getInstance().getIntProperty("database"), 
////					"dataverse", "dataverse", name.toLowerCase(), name.toLowerCase(), name.toLowerCase());
//			
//				DatasourceViewModel datasourceViewModel = new DatasourceViewModel("MySQL", 
//						PropertiesManager.getInstance().getStringProperty("ip"), 
//						3306, 
//						"dataverse", "dataverse", name.toLowerCase(), name.toLowerCase(), name.toLowerCase());
//				
//				
//				logger.info(String.format("IP: %s, Port: %s, DBType: %s, Username: %s, Password: %s, DBName: %s", 
//					datasourceViewModel.getIpAddress(), datasourceViewModel.getPort(),
//					datasourceViewModel.getDbType(),datasourceViewModel.getUsername(), datasourceViewModel.getPassword(),
//					datasourceViewModel.getDbName()));
//			
//			Response result2 = JerseyClientUtil.doPut(PropertiesManager.getInstance().getStringProperty("metastore.rest.base"), 
//					PropertiesManager.getInstance().getStringProperty("metastore.rest.addDatasource"), datasourceViewModel);
//		
//			}
//		}
//			
//		catch (Exception e) {
//			logger.error("Request to metastore failed:", e);
//		}
		
//		if(result == true){
//			return Response.status(200).entity("{\"msg\" : \"Download Success\"}").build();
//		}else{
//			return Response.status(200).entity("{\"msg\" : \"Download Fail\"}").build();
//		}
		
//		return Response.ok(filesArray).build();
//	}
	
	@Path("DataverseName/{name}/{dataverseName}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public  Response dataverseFilesRevise(@PathParam("name") final String name, @PathParam("dataverseName") final String dataverseName){
		boolean result = false;
		
		DataverseService dataverSerivce = new DataverseService();
		ArrayList< String> filesArray=new ArrayList<String>();
		filesArray = dataverSerivce.dataverseByNameRevise(name, dataverseName,"");
		 String[] s=new String[filesArray.size()];
		 for(int i=0;i<filesArray.size();i++){
			 s[i]=filesArray.get(i);
		 }
		// GenericEntity<ArrayList<String>> list = new GenericEntity<ArrayList<String>>(filesArray) {};
	//	System.out.println(s.toString());
		 
		 Gson gson = new Gson();
		 String json = gson.toJson(filesArray);
		 
		 return Response.status(200).entity(json).build();
		// return Response.status(200).entity(list).build();
		}
	
	
	@Path("DataverseName/{name}/{dataverseName}/{datasetName}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public  Response dataverseFilesRevise(@PathParam("name") final String name,@PathParam("datasetName") final String datasetName, @PathParam("dataverseName") final String dataverseName){
		boolean result = false;
		
		DataverseService dataverSerivce = new DataverseService();
		ArrayList< String> filesArray=new ArrayList<String>();
		filesArray = dataverSerivce.dataverseByNameRevise(name, dataverseName,datasetName);
		 String[] s=new String[filesArray.size()];
		 for(int i=0;i<filesArray.size();i++){
			 s[i]=filesArray.get(i);
		 }
		// GenericEntity<ArrayList<String>> list = new GenericEntity<ArrayList<String>>(filesArray) {};
	//	System.out.println(s.toString());
		 
		 Gson gson = new Gson();
		 String json = gson.toJson(filesArray);
		 
		 return Response.status(200).entity(json).build();
		// return Response.status(200).entity(list).build();
		}
		
	@Path("DownLoad/{ids}/{name}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public  Response dataverseDownloadRevise(@PathParam("name") final String name,@PathParam("ids") final String ids) throws IOException{
		boolean result=false;
		int id=Integer.parseInt(ids);
		System.out.println(id);
		DataverseService dataverSerivce = new DataverseService();
		result = dataverSerivce.dataverseById(id, name);
		if(result == true){
			return Response.status(200).entity("{\"msg\" : \"Download Success\"}").build();
		}else{
			return Response.status(200).entity("{\"msg\" : \"Download Fail\"}").build();
		}
	
	}
	
	
	
	@Path("SPSSUpload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException{
	 
			logger.info("In SPSSUpload");
		
			String output = "";

			try {
			
				String uploadedFileLocation = "upload"+ File.separatorChar + fileDetail.getFileName();
				String fileName = fileDetail.getFileName().replace(".sav", "");
				// save it
				writeToFile(uploadedInputStream, uploadedFileLocation);
				output = "File uploaded to : " + uploadedFileLocation;
				System.out.println(output);
				create(uploadedFileLocation);  //create table and tuples
				
				try {
					DatasourceViewModel datasourceViewModel = new DatasourceViewModel("MySQL", 
							PropertiesManager.getInstance().getStringProperty("ip"), 
							PropertiesManager.getInstance().getIntProperty("database"), 
							"dataverse", "dataverse", fileName.toLowerCase(), fileName.toLowerCase(), fileName.toLowerCase());
					
					logger.info(String.format("IP: %s, Port: %s, DBType: %s, Username: %s, Password: %s, DBName: %s", 
							datasourceViewModel.getIpAddress(), datasourceViewModel.getPort(),
							datasourceViewModel.getDbType(),datasourceViewModel.getUsername(), datasourceViewModel.getPassword(),
							datasourceViewModel.getDbName()));
					
					Response result2 = JerseyClientUtil.doPut(PropertiesManager.getInstance().getStringProperty("metastore.rest.base"), 
							PropertiesManager.getInstance().getStringProperty("metastore.rest.addDatasource"), datasourceViewModel);
				
				}
				catch (Exception e) {
					logger.error("Request to metastore failed:", e);
				}
			}
			catch(Exception e) {
				
				logger.error("In SPSSUpload error", e);
				
				throw e;
			}
			
			return Response.status(200).entity(output).build();
		}
	
		private  void  create(String uploadedFileLocation) throws IOException{
			FileReader2 fileReader = new FileReader2(uploadedFileLocation);
			ArrayList<String> t = fileReader.readSPSSCreat();
			SpssService s = new SpssService();
			boolean flag=false;
			try {
				flag=s.create(t);
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
