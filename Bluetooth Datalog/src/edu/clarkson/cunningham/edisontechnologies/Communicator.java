package edu.clarkson.cunningham.edisontechnologies;

import gnu.io.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;



public class Communicator implements SerialPortEventListener {
	
	GUI window = null;
	dataWrite log = new dataWrite();
	
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    //input stream for receiving data
    private InputStream input = null;
    
	//for containing the ports that will be found
    private Enumeration ports = null;
    
    //map the port names to CommPortIdentifiers
    private HashMap portMap = new HashMap();
    
  //the timeout value for connecting with the port
    final static int TIMEOUT = 2000;
    
    String logText = "";
    
   public Communicator(GUI window){
    	this.window = window;
    }
    
    //search for all the serial ports
    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();
            System.out.println(curPort.getName());

            //get only serial ports
           if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
            	window.cboxPorts.addItem(curPort.getName());
                portMap.put(curPort.getName(), curPort);
            }
        }
        
    }
    
    public void connect()
    {
        String selectedPort = (String)window.cboxPorts.getSelectedItem();
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("E.T. Data Logger", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;

            //logging
            window.txtLog.setForeground(Color.black);
             logText = selectedPort + " opened successfully.";
            window.txtLog.append(logText + "\n");
            
            
 
        }
        catch (PortInUseException e)
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";
            window.txtLog.setForeground(Color.RED);
            window.txtLog.append(logText + "\n");
        }
        catch (Exception e)
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
            window.txtLog.append(logText + "\n");
            window.txtLog.setForeground(Color.RED);
        }
    }

    public void disconnect()
    {
        //close the serial port
        try
        {
            serialPort.removeEventListener();
            serialPort.close();
            input.close();

            logText = "Disconnected.";
            window.txtLog.append(logText + "\n");
            
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getName() + "(" + e.toString() + ")";
            window.txtLog.setForeground(Color.red);
            window.txtLog.append(logText + "\n");
        }
    }
	
    public boolean initIOStream()
    {
        boolean successful = false;

        try {
            //
            input = serialPort.getInputStream();
            
            successful = true;
            log.initFile();
            return successful;
        }
        catch (IOException e) {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            window.txtLog.setForeground(Color.red);
            window.txtLog.append(logText + "\n");
            return successful;
        }
    }
    
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
            window.txtLog.setForeground(Color.red);
            window.txtLog.append(logText + "\n");
        }
    }
    
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        { String[] info = {"N/A","N/A","N/A","N/A","N/A"};
            try
            {
            	 BufferedReader portReader = new BufferedReader (
            			 new InputStreamReader(serialPort.getInputStream()));
            	 String inputLine = portReader.readLine();
            	 System.out.println(inputLine);
            	info = inputLine.split("\\s+");
            	info[4]=info[4].substring(0,info[4].length()-1);
            	window.info11.setText(info[0]);
            	window.info22.setText(info[1]);
            	window.info33.setText(info[2]);
            	window.info44.setText(info[3]);
            	window.info55.setText(info[4]);
            	//String inData = Arrays.toString(info);
            	String inData = info[0]+"\t"+info[1]+"\t\t"+info[2]+"\t\t"+info[3]+"\t\t"+info[4];
            	log.addData(inData);
            }
            catch (Exception e)
            {
            	window.info11.setText("N/A");
            	window.info22.setText("N/A");
            	window.info33.setText("N/A");
            	window.info44.setText("N/A");
            	window.info55.setText("N/A");
            	
                logText = "Failed to read data. (" + e.toString() + ")";
                window.txtLog.setForeground(Color.red);
                window.txtLog.append(logText + "\n");
            }
        }
    }
  

}
