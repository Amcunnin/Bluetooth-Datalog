package edu.clarkson.cunningham.edisontechnologies;

import gnu.io.*;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
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
        System.out.println("Available Com Ports:");
        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
           if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
            	System.out.println(curPort.getName());
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
        {
        	window.info11.setText("N/A");
        	window.info22.setText("N/A");
        	window.info33.setText("N/A");
        	window.info44.setText("N/A");
            try
            {
                byte singleData = (byte)input.read();

                if (singleData != 10)
                {
                    logText = new String(new byte[] {singleData});
                    window.txtLog.append(logText);
                }
                else
                {
                    window.txtLog.append("\n");
                }
            }
            catch (Exception e)
            {
                logText = "Failed to read data. (" + e.toString() + ")";
                window.txtLog.setForeground(Color.red);
                window.txtLog.append(logText + "\n");
            }
        }
    }
  

}
