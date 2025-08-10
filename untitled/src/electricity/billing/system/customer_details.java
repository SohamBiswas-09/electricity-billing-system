package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.ExportException;
import java.sql.ResultSet;

public class customer_details extends JFrame implements ActionListener {
    Choice searchmeterCho,searchnameCho;
    JTable table;
    JButton search,print,close;
    customer_details(){
        super("Customer Details");
        getContentPane().setBackground(new Color(192,186,255));
        setLayout(null);
        setSize(700,500);
        setLocation(400,200);

        JLabel searchMeter = new JLabel("Search by Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchmeterCho = new Choice();
        searchmeterCho.setBounds(170,20,150,20);
        add(searchmeterCho);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer");
            while (resultSet.next()){
                searchmeterCho.add(resultSet.getString("meter_no"));
            }
        }catch (Exception E){ E.printStackTrace();}
        JLabel searchName = new JLabel("Search by Name");
        searchName.setBounds(400,20,100,20);
        add(searchName);

        searchnameCho = new Choice();
        searchnameCho.setBounds(520,20,150,20);
        add(searchnameCho);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer");
            while (resultSet.next()){
                searchnameCho.add(resultSet.getString("name"));
            }
        }catch (Exception E){ E.printStackTrace();}
        table = new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,400);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.setBackground(Color.white);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.setBackground(Color.white);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBounds(600,70,80,20);
        close.setBackground(Color.white);
        close.addActionListener(this);
        add(close);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String query_search = "Select * from new_customer where meter_no = '"+searchmeterCho.getSelectedItem()+"' and name = '"+searchnameCho.getSelectedItem()+"'";
            try {
                database c = new database();
                ResultSet resultSet = c.getStatement().executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==print) {
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new customer_details();
    }
}
