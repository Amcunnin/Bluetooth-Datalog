package edu.clarkson.cunningham.edisontechnologies;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class dataWrite {
	
	private void makeFolder(){
		File theDir = new File("C://Users/ulab/Desktop/DataLogs");
		if(!theDir.exists()){
			System.out.println("Creating Directory: DataLogs");
			try{
				theDir.mkdir();
			}
			catch(SecurityException e){
				System.out.println("Directory Creation Unsuccesful");
			}
		}
	}
	
	public void initFile(){
		
		makeFolder();
		
		Date date = new Date();
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd__hh_mm_ss");
			String filename = "log_"+format.format(date);
			
			
			File file = new File("C://Users/ulab/Desktop/DataLogs/"+filename+".txt");
			if(!file.exists()){
				file.createNewFile();
				System.out.println("\nFile Created: "+filename);
			}
			
		}
		catch(Exception e){
			System.out.println("file not created");
		}
	}
	
}
