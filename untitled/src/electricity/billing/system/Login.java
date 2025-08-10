package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText;
    JPasswordField passText;
    Choice loginChoice;
    JButton loginButton,cancelButton,signupButton;
    Login(){
        super("Login");
        getContentPane().setBackground(Color.lightGray);
        JLabel username = new JLabel("UserName");
        username.setBounds(300,60, 100,20);
        add(username);
        userText = new JTextField();
        userText.setBounds(400,60, 100,20);
        add(userText);

        JLabel  password= new JLabel("Password");
        password.setBounds(300,100, 100,20);
        add(password);
        passText = new JPasswordField();
        passText.setBounds(400,100, 100,20);
        add(passText);

        JLabel logging= new JLabel("Logging in As");
        logging.setBounds(300,140, 100,20);
        add(logging);
        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,100,20);
        add(loginChoice);

        loginButton =new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton =new JButton("Cancel");
        cancelButton.setBounds(450,180,100,20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton =new JButton("Sign Up");
        signupButton.setBounds(380,210,100,20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileTwo = new ImageIcon(profileTwo);
        JLabel profileLabel = new JLabel(fprofileTwo);

        profileLabel.setBounds(5,5,250,250 );
        add(profileLabel);
        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signupButton){
            setVisible(false);
            new Signup();
        } else if (e.getSource()==loginButton) {
            String susername = userText.getText();
            String spassword = new String(passText.getPassword());
            String user = loginChoice.getSelectedItem();
            try {
                database c = new database();
                String query = "select * from Signup where username = '"+susername+"' and password = '"+spassword+"' and usertype = '"+user+"' ";
                ResultSet resultSet=c.getStatement().executeQuery(query);
                if(resultSet.next()){
                    String meter =resultSet.getString("meterNo_empId");
                    setVisible(false);
                    new main_class(user,meter);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid login");
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource()==cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
