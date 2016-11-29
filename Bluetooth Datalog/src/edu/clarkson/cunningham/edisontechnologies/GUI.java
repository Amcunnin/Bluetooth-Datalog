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
        txtLog = new javax.swing.JTextArea();

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
        
                txtLog.setColumns(30);
                txtLog.setEditable(false);
                txtLog.setLineWrap(true);
                txtLog.setRows(10);
                txtLog.setFocusable(false);
                jScrollPane1.setViewportView(txtLog);
                
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(layout
        		.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(btnConnect)
        			.addComponent(btnDisconnect)
        			.addComponent(cboxPorts))
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(txtLog))
        		
        );
        
        layout.setVerticalGroup(layout
        		.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			.addComponent(btnConnect)
        			.addComponent(btnDisconnect)
        			.addComponent(cboxPorts))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
        		.addComponent(txtLog)
        		
        );
        pack();
        
	}
	
   private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
        communicator.connect();
            if (communicator.initIOStream() == true)
            {
                communicator.initListener();
            }
	}
            
   private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {
                communicator.disconnect();
	}
	
	// variable declaration
	public javax.swing.JButton btnConnect;
    public javax.swing.JButton btnDisconnect;
    public javax.swing.JComboBox cboxPorts;
    public javax.swing.JTextArea txtLog;
    private javax.swing.JScrollPane jScrollPane1;
}
