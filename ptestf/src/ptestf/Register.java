package ptestf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Register extends JFrame{

	private static final long serialVersionUID = 1L;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JPanel contentPane;
    
    public Register() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 797);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); 
        
//user name        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 66, 193, 52);
        contentPane.add(lblUsername);

        JTextField userName  = new JTextField();
        userName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        userName.setBounds(481, 70, 281, 68);
        contentPane.add(userName);
        userName.setColumns(10);

//password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 170, 193, 52);
        contentPane.add(lblPassword);
        
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 170, 281, 68);
        contentPane.add(passwordField);
        
        
//first name
        
        JLabel lblFirstname = new JLabel("First Name");
        lblFirstname.setBackground(Color.BLACK);
        lblFirstname.setForeground(Color.BLACK);
        lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblFirstname.setBounds(250, 266, 193, 52);
        contentPane.add(lblFirstname);

        JTextField firstName = new JTextField();
        firstName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstName.setBounds(481, 270, 281, 68);
        contentPane.add(firstName);
        firstName.setColumns(10);
        
//last name
        
        
        JLabel lblLastname = new JLabel("Last Name");
        lblLastname.setBackground(Color.BLACK);
        lblLastname.setForeground(Color.BLACK);
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblLastname.setBounds(250, 366, 193, 52);
        contentPane.add(lblLastname);

        JTextField lastName = new JTextField();
        lastName.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastName.setBounds(481, 370, 281, 68);
        contentPane.add(lastName);
        lastName.setColumns(10);
        
//cnp
        
        
        JLabel lblcnp = new JLabel("CNP");
        lblcnp.setBackground(Color.BLACK);
        lblcnp.setForeground(Color.BLACK);
        lblcnp.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblcnp.setBounds(250, 466, 193, 52);
        contentPane.add(lblcnp);

        JTextField cnp = new JTextField();
        cnp.setFont(new Font("Tahoma", Font.PLAIN, 32));
        cnp.setBounds(481, 470, 281, 68);
        contentPane.add(cnp);
        cnp.setColumns(10);
  

        btnNewButton = new JButton("Register");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(445, 592, 162, 73);
        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String userN = userName.getText();
                String firstN = firstName.getText();
                String lastN = lastName.getText();
                String cnpN = cnp.getText();
                String password=String.valueOf(passwordField.getPassword());
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/banca",
                        "root", "");

                    //check if record already exist
                    PreparedStatement st = (PreparedStatement) connection
                        .prepareStatement("Select username from client where username=?");

                    st.setString(1, userN);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                    	 JOptionPane.showMessageDialog(btnNewButton, "Username already exist ");
                    } else {
                    	
                    	//insert into database
                    	String query = "INSERT INTO client(username,password,"
                    			+ "first_name,last_name,cnp,sold_euro,sold_ron)" 
                    			+ "VALUES(?,?,?,?,?,?,?)";
                    			
                    			  PreparedStatement preparedStmt = connection.prepareStatement(query);
                    		      preparedStmt.setString (1, userN);
                    		      preparedStmt.setString (2, password);
                    		      preparedStmt.setString   (3, firstN);
                    		      preparedStmt.setString   (4, lastN);
                    		      preparedStmt.setString   (5, cnpN);
                    		      preparedStmt.setInt(6, 0);
                    		      preparedStmt.setInt    (7, 0);
                    		      preparedStmt.execute();
                    		      dispose();
                    		      UserLogin ah = new UserLogin();
                                  ah.setTitle("Login");
                                  ah.setVisible(true);
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        contentPane.add(btnNewButton);
    }
}
