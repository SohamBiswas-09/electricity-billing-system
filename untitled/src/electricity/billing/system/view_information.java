package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_information extends JFrame implements ActionListener {
    String view;
    JButton cancle;
    view_information(String view){
        super("View Information");
        this.view=view;
        getContentPane().setBackground(Color.white);
        setSize(850,550);
        setLocation(350,150);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(70,80,100,20);
        add(nameLabel);

        JLabel nameText = new JLabel("");
        nameText.setBounds(190,80,100,20);
        add(nameText);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(70,120,100,20);
        add(meterNo);

        JLabel meterNoText = new JLabel(view);
        meterNoText.setBounds(190,120,100,20);
        add(meterNoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,160,100,20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(190,160,150,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,200,100,20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(190,200,100,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,100,20);
        add(stateText);

        JLabel email = new JLabel("email");
        email.setBounds(500,120,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,120,150,20);
        add(emailText);

        JLabel phone = new JLabel("Phone Number ");
        phone.setBounds(500,160,100,20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,160,100,20);
        add(phoneText);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer where meter_no = '"+view+"'");
            if(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        } catch (Exception E){
            E.printStackTrace();
        }
        cancle = new JButton("Cancel");
        cancle.setBounds(220,270,120,25);
        cancle.setForeground(Color.white);
        cancle.setBackground(Color.black);
        cancle.addActionListener(this);
        add(cancle);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image image = imageIcon.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel image1 = new JLabel(imageIcon1);
        image1.setBounds(100,210,600,300);
        add(image1);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancle){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new view_information("");
    }
}
