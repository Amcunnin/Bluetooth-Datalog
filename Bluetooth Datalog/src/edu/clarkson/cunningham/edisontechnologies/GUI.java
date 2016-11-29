package edu.clarkson.cunningham.edisontechnologies;

public class GUI extends javax.swing.JFrame {
	
Communicator communicator = null;

	public GUI() {
		initComponents();
		createObjects();
		communicator.searchForPorts();
	}
	
	private void createObjects(){
		communicator = new Communicator(this);
	}
	
	private void initComponents(){
        cboxPorts = new javax.swing.JComboBox();
        btnConnect = new javax.swing.JButton();
        btnDisconnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E.T. Data Logger");
        
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

}
