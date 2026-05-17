public class Main {

    public static void main(String[] args) {

        try {

            DBConnection.getConnection();
            System.out.println("Connected Successfully");

            Login login = new Login();
            login.setVisible(true);

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}