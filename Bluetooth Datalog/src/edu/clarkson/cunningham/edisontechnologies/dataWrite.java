package edu.clarkson.cunningham.edisontechnologies;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class dataWrite {
	static String file = "";
	
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd__hh_mm_ss");
		String filename = "log_"+format.format(date);
		file = "C://Users/ulab/Desktop/DataLogs/"+filename+".txt";
		//Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try{
			File file = new File("C://Users/ulab/Desktop/DataLogs/"+filename+".txt");
			if(!file.exists()){
				file.createNewFile();
				System.out.println("\nFile Created: "+filename+".txt");
			}
			
		}
		catch(Exception e){
			System.out.println("file not created");
		}
	}
	
	public void addData(String data){
		try{
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.newLine();
			bw.close();
	    	System.out.println("file appended");
			/*System.out.println(file);
			File fout = new File("C://Users/ulab/Desktop/DataLogs/lastlog.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(data);
			osw.write("something");
			osw.close();*/
			
		}catch(Exception e){
			System.out.println("file write exception");
		}
		
	}

}
