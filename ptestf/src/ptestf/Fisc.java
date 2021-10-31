package ptestf;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Fisc extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private static JPanel contentPane;
    private JList<String> jList;
    private JLabel lblNumber ;
    private DefaultListModel<String> clienti;
    private JScrollPane js55;
    private JTextField clientName ;
    private JButton adaugaClient;
    private JButton retrageClient;
    private JButton infoClient;

    
    public static void alertFisc(String userName,boolean ron,boolean retragere,int amount) {
    	
    	if(ron) {
    		if(retragere) {
    			JOptionPane.showMessageDialog(contentPane,userName+" "
    					+ "a retras "+ amount+" ron"); 
    		}
    		else {
    			JOptionPane.showMessageDialog(contentPane,userName+" "
    					+ "a depus "+ amount+" ron"); 
    		}
    	}
    	else {
    		if(retragere) {
    			JOptionPane.showMessageDialog(contentPane,userName+" "
    					+ "a retras "+ amount+" euro"); 
    		}
    		else {
    			JOptionPane.showMessageDialog(contentPane,userName+" "
    					+ "a depus "+ amount+" euro"); 
    		}
    	}
    } 

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Fisc frame = new Fisc();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Fisc() {
    	
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        lblNumber= new JLabel("");
    	lblNumber.setBackground(Color.RED);
        lblNumber.setForeground(Color.RED);
        lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblNumber.setBounds(150, 166, 593, 52);
        contentPane.add(lblNumber);
        
        clienti = new DefaultListModel<String>();
        
        try {

	       	Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
	            		+ "//localhost:3306/"+ "banca","root", "");
	    
	       	Statement stmt = connection.createStatement() ;
	        String query = "select client.username from client join fisc on client.username=fisc.username;" ;
	        ResultSet rs = stmt.executeQuery(query);
	    
		    if(rs.isBeforeFirst() ){     
			   
		    	while(rs.next()) {
		    		String userNameTemp = rs.getString("username");
		    		clienti.addElement(userNameTemp);
		    	}
		    
		    }
       }catch(SQLException eSql) {
       		eSql.printStackTrace();
       }
   	
		jList = new JList<String>(clienti);
   	 	jList.setModel(clienti);
    	jList.setBackground(Color.WHITE);
    	jList.setForeground(Color.BLACK);
    	jList.setFont(new Font("Tahoma", Font.PLAIN, 17));
    	
    	js55=new JScrollPane(jList);
    	js55.setVisible(true);
	   	js55.setBounds(50, 266, 793, 252);
	   	js55.updateUI();
	   	contentPane.add(js55);
        
        clientName  = new JTextField();
        clientName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        clientName.setBounds(150, 70, 281, 68);
        contentPane.add(clientName);
        clientName.setColumns(10);
        
        adaugaClient = new JButton("Adauga");
        adaugaClient.setFont(new Font("Tahoma", Font.PLAIN, 26));
        adaugaClient.setBounds(450, 70, 262, 73);
        
        retrageClient = new JButton("Retrage");
        retrageClient.setFont(new Font("Tahoma", Font.PLAIN, 26));
        retrageClient.setBounds(720, 70, 262, 73);
        
        infoClient = new JButton("Info");
        infoClient.setFont(new Font("Tahoma", Font.PLAIN, 26));
        infoClient.setBounds(720, 170, 262, 73);
        
        infoClient.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	 try {
                     Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                     		+ "//localhost:3306/"+ "banca","root", "");
                     
                     
                     
                     
                     if(jList.getSelectedIndex() != -1) {
                    	 
                    	String userName = jList.getSelectedValue();
 	                    PreparedStatement stt = (PreparedStatement) connection
 	                            .prepareStatement("Select * from client where username=?");
 	                    stt.setString(1, userName);
 	            	    ResultSet rs = stt.executeQuery() ;
 	            	    
 	            	    if(rs.isBeforeFirst()) {
 	            	    	
	 	            	    rs.next();
	 	            	    String userNameTemp = rs.getString("username");
	 	            	    String lastNameTemp = rs.getString("last_name");
	 	            	    String firstNameTemp = rs.getString("first_name");
	 	            	    String cnpTemp = rs.getString("cnp");
	 	            	    int ronTemp = rs.getInt("sold_ron");
	 	            	    int euroTemp = rs.getInt("sold_euro");
	 	            	    
	 	            	   JOptionPane.showMessageDialog(contentPane," username: " + userNameTemp +
	 	            			   "\n first name: "+ firstNameTemp + "\n last name: " + lastNameTemp + 
	 	            			   "\n cnp: " + cnpTemp + "\n ron: " + ronTemp + "\n euro: " + euroTemp);     
	                     }
 	            	    else {
 	            	    	DefaultListModel<String> model = (DefaultListModel<String>)jList.getModel();
 	            	    	model.remove(jList.getSelectedIndex());
 	            	    	
                     }
                     }
             }
                 catch(SQLException eSql) {
                 	eSql.printStackTrace();
                 }
            	
            	
            	
            }
        });
   
        adaugaClient.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	lblNumber.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
                    
                    String userName = clientName.getText(); 
                    
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from fisc where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    
            	    PreparedStatement stt2 = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt2.setString(1, userName);
            	    ResultSet rs2 = stt2.executeQuery() ;
            	    
            	    if(!rs.isBeforeFirst() && rs2.isBeforeFirst() ){    
            	    	
            	    	PreparedStatement st = (PreparedStatement) connection
                                .prepareStatement("insert into fisc(username) values(?)");
                        st.setString(1, userName);
                        st.execute();
                        clienti.addElement(userName);
            	    
            	    }
            	    else {
            	    	if(rs.isBeforeFirst()) {
            	    		lblNumber.setText("Utilizatorul este deja inregistrat");
            	    		
            	    	}
            	    	else {
            	    		
            	    		lblNumber.setText("Utilizatorul nu a fost gasit");
            	    	}
            	    }
        	       
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        retrageClient.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblNumber.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
                    
                    DefaultListModel<String> model = (DefaultListModel<String>)jList.getModel();
                    String userName = jList.getSelectedValue();
                    if(jList.getSelectedIndex() != -1) {
                    	model.remove(jList.getSelectedIndex());
            	    	PreparedStatement st = (PreparedStatement) connection
                                .prepareStatement("delete from fisc where username=?");
                        st.setString(1, userName);
                        st.execute();
	            	    
                    }
        	       
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }              
            }
        });

        contentPane.add(adaugaClient);
        contentPane.add(retrageClient);
        contentPane.add(infoClient);
        
    }
}
