package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class bill_details extends JFrame  {
    String meter;
    bill_details(String meter){
        super("Bill Details");
        this.meter=meter;
        getContentPane().setBackground(Color.white);
        setBounds(300,100,700,630);
        setLayout(null);
        JTable table=new JTable();
        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer where meter_no = '"+meter+"'");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception E){
            E.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,0,700,630);
        add(sp);
        setVisible(true);
    }
    public static void main(String[] args) {
        new bill_details("");
    }
}
