package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {
    String meter;
    Choice searchMonthCho;
    JButton pay, back;

    pay_bill(String meter) {
        super("Bill Payment");
        this.meter = meter;
        setBounds(300, 150, 900, 600);
        setLayout(null);

        JLabel heading = new JLabel("Bill Payment");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(350, 20, 200, 30);
        add(heading);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(35, 80, 100, 20);
        add(meterNo);

        JLabel meterText = new JLabel(meter);
        meterText.setBounds(200, 80, 200, 20);
        add(meterText);

        JLabel name = new JLabel("Name");
        name.setBounds(35, 140, 100, 20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(200, 140, 200, 20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35, 200, 100, 20);
        add(month);

        searchMonthCho = new Choice();
        searchMonthCho.add("January");
        searchMonthCho.add("February");
        searchMonthCho.add("March");
        searchMonthCho.add("April");
        searchMonthCho.add("May");
        searchMonthCho.add("June");
        searchMonthCho.add("July");
        searchMonthCho.add("August");
        searchMonthCho.add("September");
        searchMonthCho.add("October");
        searchMonthCho.add("November");
        searchMonthCho.add("December");
        searchMonthCho.setBounds(200, 200, 150, 20);
        add(searchMonthCho);

        JLabel unit = new JLabel("Total Unit");
        unit.setBounds(35, 260, 100, 20);
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(200, 260, 100, 20);
        add(unitText);

        JLabel totalBill = new JLabel("Total Amount");
        totalBill.setBounds(35, 320, 100, 20);
        add(totalBill);

        JLabel totalBillText = new JLabel("");
        totalBillText.setBounds(200, 320, 100, 20);
        add(totalBillText);

        JLabel status = new JLabel("Status");
        status.setBounds(35, 380, 100, 20);
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(200, 380, 100, 20);
        statusText.setForeground(Color.RED);
        add(statusText);

        try {
            database c = new database();
            ResultSet resultSet = c.getStatement().executeQuery("Select * from new_customer where meter_no = '" + meter + "'");
            while (resultSet.next()) {
                nameText.setText(resultSet.getString("name"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }

        searchMonthCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try {
                    ResultSet resultSet = c.getStatement().executeQuery("Select * from bill where meter_no= '" + meter + "' and month ='" + searchMonthCho.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        unitText.setText(resultSet.getString("unit"));
                        totalBillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setBounds(100, 440, 100, 25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(230, 440, 100, 25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pay) {
            try {
                database c = new database();
                c.getStatement().executeUpdate("update bill set status = 'Paid' where meter_no = '" + meter + "' and month = '" + searchMonthCho.getSelectedItem() + "'");
            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
