package edu.clarkson.cunningham.edisontechnologies;

import javax.swing.*;

public class GUI extends javax.swing.JFrame {
	
Communicator communicator = new Communicator(this);

	public GUI() {
		initComponents();
		communicator.searchForPorts();
	}
	
	
	private void initComponents(){
		jScrollPane1 = new javax.swing.JScrollPane();
        cboxPorts = new javax.swing.JComboBox();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();
        btnDisconnect.setEnabled(false);
        txtLog = new javax.swing.JTextArea();
        JLabel info1 = new JLabel("Opmode:    ");
        info11 = new javax.swing.JLabel();
        JLabel info2 = new JLabel("Direction: ");
        info22 = new javax.swing.JLabel();
        JLabel info3 = new JLabel("Distance:  ");
        info33 = new javax.swing.JLabel();
        JLabel info4 = new JLabel("Dis. Traveled: ");
        info44 = new javax.swing.JLabel();
        JLabel info5 = new JLabel("Speed (ft/s):  ");
        info55 = new javax.swing.JLabel();

        setTitle("E.T. Data Logger");
        setLocation(400,300);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
                btnConnect.setText("Connect");
                btnConnect.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnConnectActionPerformed(evt);
                    }
                });
                
                btnDisconnect.setText("Disconnect");
                btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnDisconnectActionPerformed(evt);
                    }
                });        
        
                txtLog = new JTextArea(15,40);
                //txtLog.setColumns(30);
               txtLog.setEditable(false);
               // txtLog.setLineWrap(true);
              //  txtLog.setRows(10);
               // txtLog.setFocusable(false);
               // jScrollPane1.getViewport().setView(txtLog);
               JScrollPane scroll = new JScrollPane( txtLog );
               scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
              
                
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout
        		.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(btnConnect)
        			.addComponent(btnDisconnect)
        			.addComponent(cboxPorts))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(scroll))
        		.addGroup(layout.createSequentialGroup()
        				.addComponent(info1).addComponent(info11))
        		.addGroup(layout.createSequentialGroup()
        				.addComponent(info2).addComponent(info22))
        		.addGroup(layout.createSequentialGroup()
        				.addComponent(info3).addComponent(info33))
        		.addGroup(layout.createSequentialGroup()
        				.addComponent(info4).addComponent(info44))
        		.addGroup(layout.createSequentialGroup()
        				.addComponent(info5).addComponent(info55))
        		
        );
        
        layout.setVerticalGroup(layout
        		.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			.addComponent(btnConnect)
        			.addComponent(btnDisconnect)
        			.addComponent(cboxPorts))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			.addComponent(scroll))
        		.addGap(10)
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(info1).addComponent(info11))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(info2).addComponent(info22))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(info3).addComponent(info33))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(info4).addComponent(info44))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        				.addComponent(info5).addComponent(info55))
        	
        		
        );
        pack();
        
	}
	
   private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
        communicator.connect();
            if (communicator.initIOStream() == true)
            {
                communicator.initListener();
                btnConnect.setEnabled(false);
                btnDisconnect.setEnabled(true);
                cboxPorts.setEnabled(false);
            }
	}
            
   private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {
                communicator.disconnect();
                btnConnect.setEnabled(true);
                cboxPorts.setEnabled(true);
                btnDisconnect.setEnabled(false);
	}
	
	// variable declaration
	public javax.swing.JButton btnConnect;
    public javax.swing.JButton btnDisconnect;
    public javax.swing.JComboBox cboxPorts;
    public javax.swing.JTextArea txtLog;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel info11;
    public javax.swing.JLabel info22;
    public javax.swing.JLabel info33;
    public javax.swing.JLabel info44;
    public javax.swing.JLabel info55;
}
