import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ExamPage extends JFrame implements ActionListener {

    JLabel questionLabel;

    JRadioButton op1, op2, op3, op4;

    ButtonGroup bg;

    JButton nextBtn;
    JLabel timerLabel;

    int timeLeft = 60;

    Connection con;
    Statement st;
    ResultSet rs;
    

    int score = 0;
    int total = 0;

    String correctAnswer = "";
    

   public ExamPage() {

    setTitle("Java Online Exam");

    timerLabel = new JLabel("Time Left: 60");
    timerLabel.setBounds(600,30,150,30);
    add(timerLabel);
    questionLabel = new JLabel();
    questionLabel.setBounds(50,30,700,30);
    add(questionLabel);

        op1 = new JRadioButton();
        op1.setBounds(50,80,300,30);
        add(op1);

        op2 = new JRadioButton();
        op2.setBounds(50,120,300,30);
        add(op2);

        op3 = new JRadioButton();
        op3.setBounds(50,160,300,30);
        add(op3);

        op4 = new JRadioButton();
        op4.setBounds(50,200,300,30);
        add(op4);

        bg = new ButtonGroup();
        

        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(250,260,100,30);
        nextBtn.addActionListener(this);
        add(nextBtn);

        setSize(800,400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        loadQuestions();
        startTimer();
    }

    void loadQuestions() {

        try {

            con = DBConnection.getConnection();

            st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM questions");

            showQuestion();

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    void showQuestion() {

        try {

            if(rs.next()) {

                total++;

                questionLabel.setText(rs.getString("question"));

                op1.setText(rs.getString("option1"));
                op2.setText(rs.getString("option2"));
                op3.setText(rs.getString("option3"));
                op4.setText(rs.getString("option4"));

                correctAnswer = rs.getString("correct_answer");

            } else {

                double percentage = ((double)score / total) * 100;

                new ResultPage(score,total,percentage);

                dispose();
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
    void startTimer() {

    Thread timerThread = new Thread(() -> {

        while(timeLeft >= 0) {

            try {

                timerLabel.setText("Time Left: " + timeLeft + " sec");

                Thread.sleep(1000);

                timeLeft--;

            } catch(Exception e) {

                System.out.println(e);
            }
        }

        JOptionPane.showMessageDialog(null, "Time Over!");

        double percentage = ((double) score / total) * 100;

        new ResultPage(score, total, percentage);

        dispose();
    });

    timerThread.start();
}

    public void actionPerformed(ActionEvent e) {

        String selected = "";

        if(op1.isSelected())
            selected = op1.getText();

        else if(op2.isSelected())
            selected = op2.getText();

        else if(op3.isSelected())
            selected = op3.getText();

        else if(op4.isSelected())
            selected = op4.getText();

        if(selected.equals(correctAnswer)) {

            score++;
        }

        bg.clearSelection();

        showQuestion();
    }
}