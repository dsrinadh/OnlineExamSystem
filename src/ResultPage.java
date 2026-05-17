import javax.swing.*;

public class ResultPage extends JFrame {

    public ResultPage(int score, int total, double percentage) {

        setTitle("Exam Result");

        JLabel resultLabel = new JLabel("Your Score: " + score + " / " + total);
        resultLabel.setBounds(100,50,300,30);
        add(resultLabel);

        JLabel percentLabel = new JLabel("Percentage: " + percentage + "%");
        percentLabel.setBounds(100,100,300,30);
        add(percentLabel);

        if(percentage >= 50) {

            JLabel status = new JLabel("Result: PASS");
            status.setBounds(100,150,300,30);
            add(status);

        } else {

            JLabel status = new JLabel("Result: FAIL");
            status.setBounds(100,150,300,30);
            add(status);
        }

        setSize(400,300);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}