package ptestf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserHome extends JFrame {

	
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField amountEuro;
    private JTextField amountRon;
    private JLabel lblEuro;
    private JLabel lblNumber;
    private JLabel lblRon;
    private JLabel lblInfo;
    private JButton adaugaEuro;
    private JButton retrageEuro;
    private JButton adaugaRon;
    private JButton lichideazaContul;
    private JButton retrageRon;
    private JButton clientInfo;
    
    public UserHome(String userName) {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
//labels        
        
        lblEuro = new JLabel("Euro");
        lblEuro.setBackground(Color.BLACK);
        lblEuro.setForeground(Color.BLACK);
        lblEuro.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblEuro.setBounds(50, 66, 193, 52);
        contentPane.add(lblEuro);
        
        lblRon = new JLabel("Ron");
        lblRon.setBackground(Color.BLACK);
        lblRon.setForeground(Color.BLACK);
        lblRon.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblRon.setBounds(50, 166, 193, 52);
        contentPane.add(lblRon);
        
        lblNumber = new JLabel("");
        lblNumber.setBackground(Color.RED);
        lblNumber.setForeground(Color.RED);
        lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblNumber.setBounds(150, 266, 393, 52);
        contentPane.add(lblNumber);
        
        lblInfo = new JLabel("");
        lblInfo .setBackground(Color.BLACK);
        lblInfo .setForeground(Color.BLACK);
        lblInfo .setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblInfo .setBounds(150, 366, 393, 52);
        contentPane.add(lblInfo );
        
        amountEuro  = new JTextField();
        amountEuro.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            	//set input to numeric or 'delete'
               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == 8) {
            	   amountEuro.setEditable(true);
            	   lblNumber.setText("");
               } else {
            	   amountEuro.setEditable(false);
            	   lblNumber.setText("* Enter only numeric digits(0-9)");
               }
            }
         });
        amountEuro.setFont(new Font("Tahoma", Font.PLAIN, 32));
        amountEuro.setBounds(150, 70, 281, 68);
        contentPane.add(amountEuro);
        amountEuro.setColumns(10);
        
        amountRon  = new JTextField();
        amountRon.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            	//set input to numeric or 'delete'
               if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == 8) {
            	   amountRon.setEditable(true);
            	   lblNumber.setText("");
               } else {
            	   amountRon.setEditable(false);
            	   lblNumber.setText("* Enter only numeric digits(0-9)");
               }
            }
         });
        amountRon.setFont(new Font("Tahoma", Font.PLAIN, 32));
        amountRon.setBounds(150, 170, 281, 68);
        contentPane.add(amountRon);
        amountRon.setColumns(10);
        
        
///buttons  
        
        
        
//euro   

        adaugaEuro = new JButton("Adauga");
        adaugaEuro.setFont(new Font("Tahoma", Font.PLAIN, 26));
        adaugaEuro.setBounds(450, 70, 262, 73);
        
        retrageEuro = new JButton("Retrage");
        retrageEuro.setFont(new Font("Tahoma", Font.PLAIN, 26));
        retrageEuro.setBounds(720, 70, 262, 73);
        
        
        
//ron    
        adaugaRon = new JButton("Adauga");
        adaugaRon.setFont(new Font("Tahoma", Font.PLAIN, 26));
        adaugaRon.setBounds(450, 170, 262, 73);
        
        retrageRon = new JButton("Retrage");
        retrageRon.setFont(new Font("Tahoma", Font.PLAIN, 26));
        retrageRon.setBounds(720, 170, 262, 73);
        
//operatii    
        
        lichideazaContul = new JButton("Lichideaza");
        lichideazaContul.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lichideazaContul.setBounds(720, 370, 262, 73);
        
        clientInfo = new JButton("Interogare");
        clientInfo.setFont(new Font("Tahoma", Font.PLAIN, 26));
        clientInfo.setBounds(720, 270, 262, 73);
        
        
        adaugaRon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblInfo.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");   
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
            	    int ronTemp = rs.getInt("sold_ron");
            	    
            	    // check if the client is being tracked
            	    PreparedStatement stt2 = (PreparedStatement) connection
                            .prepareStatement("Select * from fisc where username=?");
                    stt2.setString(1, userName);
            	    ResultSet rs2 = stt2.executeQuery() ;
            	    
        	        String stringTemp = amountRon.getText();
    	        	if(stringTemp != null && !stringTemp.trim().isEmpty()) {
    	        	
    	        		int ron = Integer.parseInt(amountRon.getText());	
    	        		
    	        		if(rs2.isBeforeFirst()) {
                	    	Fisc.alertFisc(userName, true, false, ron);
                	    }
    	        				
            			    PreparedStatement st = (PreparedStatement) connection
                                    .prepareStatement("update client set sold_ron=? where username=?");
                            st.setInt(1, ronTemp+ron);
                            st.setString(2, userName);
            		        st.executeUpdate();
            		        connection.close();
    	        }
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        adaugaEuro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblInfo.setText("");
                try {
                	
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
     
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
        	        int euroTemp = rs.getInt("sold_euro");
        	        
        	     // check if the client is being tracked
        	        PreparedStatement stt2 = (PreparedStatement) connection
                            .prepareStatement("Select * from fisc where username=?");
                    stt2.setString(1, userName);
            	    ResultSet rs2 = stt2.executeQuery() ;
            	        	        
        	        String stringTemp = amountEuro.getText();
    	        	if(stringTemp != null && !stringTemp.trim().isEmpty()) {
    	        	
    	        		int euro = Integer.parseInt(amountEuro.getText());	
    	        		
    	        		if(rs2.isBeforeFirst()) {
                	    	Fisc.alertFisc(userName, false, false, euro);
                	    }
    	        		
        			    PreparedStatement st = (PreparedStatement) connection
                                .prepareStatement("update client set sold_euro=? where username=?");
                        st.setInt(1, euroTemp+euro);
                        st.setString(2, userName);
        		        st.executeUpdate();
        		        connection.close();
    	        }
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        retrageEuro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblInfo.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
     
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
        	        int euroTemp = rs.getInt("sold_euro");
        	        
        	     // check if the client is being tracked
        	        PreparedStatement stt2 = (PreparedStatement) connection
                            .prepareStatement("Select * from fisc where username=?");
                    stt2.setString(1, userName);
            	    ResultSet rs2 = stt2.executeQuery() ;
        	        
        	        String stringTemp = amountEuro.getText();
    	        	if(stringTemp != null && !stringTemp.trim().isEmpty()) {
    	        	
    	        		int euro = Integer.parseInt(amountEuro.getText());	
    	        		if( euroTemp - euro > 0) {
    	        			
    	        			if(rs2.isBeforeFirst()) {
                    	    	Fisc.alertFisc(userName, false, true, euro);
                    	    }
    	        			
            			    PreparedStatement st = (PreparedStatement) connection
                                    .prepareStatement("update client set sold_euro=? where username=?");
                            st.setInt(1, euroTemp-euro);
                            st.setString(2, userName);
            		        st.executeUpdate();
            		        connection.close();
    	        		}
    	        		else {
    	        			lblNumber.setText("* Fonduri insuficiente");
    	        		}
    	        }
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        retrageRon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblInfo.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
     
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
        	        int ronTemp = rs.getInt("sold_ron");
        	        
        	     // check if the client is being tracked
        	        PreparedStatement stt2 = (PreparedStatement) connection
                            .prepareStatement("Select * from fisc where username=?");
                    stt2.setString(1, userName);
            	    ResultSet rs2 = stt2.executeQuery() ;
        	        
        	        String stringTemp = amountRon.getText();
    	        	if(stringTemp != null && !stringTemp.trim().isEmpty()) {
    	        	
    	        		int ron = Integer.parseInt(amountRon.getText());	
    	        		
    	        		if( ronTemp - ron > 0) {
    	        			
    	        			if(rs2.isBeforeFirst()) {
                    	    	Fisc.alertFisc(userName, true, true, ron);
                    	    }
            			    PreparedStatement st = (PreparedStatement) connection
                                    .prepareStatement("update client set sold_ron=? where username=?");
                            st.setInt(1, ronTemp-ron);
                            st.setString(2, userName);
            		        st.executeUpdate();
            		        connection.close();
    	        		}
    	        		else {
    	        			lblNumber.setText("* Fonduri insuficiente");
    	        		}
    	        }
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        clientInfo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblNumber.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
     
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
        	        int euroTemp = rs.getInt("sold_euro");
        	        int ronTemp = rs.getInt("sold_ron");
        	        lblInfo.setText("euro:		" + euroTemp + " \n" + 
        	        				"ron:		" + ronTemp );
        	     
            }
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
            }
        });
        
        
        
        lichideazaContul.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	
            	lblInfo.setText("");
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql:"
                    		+ "//localhost:3306/"+ "banca","root", "");
     
                    PreparedStatement stt = (PreparedStatement) connection
                            .prepareStatement("Select * from client where username=?");
                    stt.setString(1, userName);
            	    ResultSet rs = stt.executeQuery() ;
            	    rs.next();
        	        int euroTemp = rs.getInt("sold_euro");
        	        int ronTemp = rs.getInt("sold_ron");
        	        
        	        if(euroTemp > 0 && ronTemp > 0) {
        	        	int a = JOptionPane.showConfirmDialog(lichideazaContul, "Confirmare lichidare cont");
                        if (a == JOptionPane.YES_OPTION) {
                        	PreparedStatement st = (PreparedStatement) connection
                                    .prepareStatement("delete from client where username=?");
                            st.setString(1, userName);
            			    st.executeUpdate();
            			    dispose();
                            
                            UserLogin obj = new UserLogin();
                            obj.setTitle("Bine ati venit");
                            obj.setVisible(true);
            			    
                        }
                    }
        	        else {
        	        	lblNumber.setText("* Fonduri insuficiente");
        	        }
        	    }  
                catch(SQLException eSql) {
                	eSql.printStackTrace();
                }
        }
           
        });
        
        contentPane.add(adaugaRon);
        contentPane.add(adaugaEuro);
        contentPane.add(retrageEuro);
        contentPane.add(retrageRon);
        contentPane.add(clientInfo);
        contentPane.add(lichideazaContul);

    }
}
