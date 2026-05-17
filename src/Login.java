import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField tf1;
    JPasswordField pf1;
    JButton b1;

    public Login() {

        setTitle("Login");

        l1 = new JLabel("Username");
        l1.setBounds(50,50,100,30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(150,50,150,30);
        add(tf1);

        l2 = new JLabel("Password");
        l2.setBounds(50,100,100,30);
        add(l2);

        pf1 = new JPasswordField();
        pf1.setBounds(150,100,150,30);
        add(pf1);

        b1 = new JButton("Login");
        b1.setBounds(120,160,100,30);
        b1.addActionListener(this);
        add(b1);

        setSize(400,300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String user = tf1.getText();
        String pass = pf1.getText();

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1,user);
            ps.setString(2,pass);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(this,"Login Successful");

                new ExamPage();

                dispose();

            } else {

                JOptionPane.showMessageDialog(this,"Invalid Username or Password");
            }

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}