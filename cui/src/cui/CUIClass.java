package cui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Scanner;
import software5.sunguanghao.Run;
import software5.exercise6.Register;
import software5.exercise6.User;
import software5.exercise6.Login;
import java.sql.*;

public class CUIClass {
    public static String url = "jdbc:mysql://localhost:3306/java_test?&useSSL=false&serverTimezone=UTC";
    public static String username = "root";
    public static String password = "Sun371502!";
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        new Gclass();
    }
}
class Gclass extends JFrame{
    private static final long serialVersionUID=1L;
    JLabel username, password, info;
    JTextField userText;
    JPasswordField passText;
    JButton register,login,exit;
    Gclass(){
        this.setBounds(400,200,700,400);
        this.setResizable(true);
        this.setTitle("login/register");
        this.setLayout(null);
        init2();
        //this.pack();
        this.setVisible(true);
    }
    void init2() {
        {
            JPanel mainCardPanel = new JPanel(new CardLayout());
            JPanel compositePage = new JPanel(new GridLayout(2, 2));
            this.setLayout(new BorderLayout());
            JPanel p1 = new JPanel();
            JPanel p2 = new JPanel();
            this.add(p1, BorderLayout.NORTH);
            this.add(p2, BorderLayout.CENTER);

            username = new JLabel("username");
            userText = new JTextField(12);
            p1.add(username);
            p1.add(userText);

            p2.setLayout(new BorderLayout());
            JPanel p3 = new JPanel();
            JPanel p4 = new JPanel();
            p2.add(p3, BorderLayout.NORTH);
            p2.add(p4, BorderLayout.CENTER);

            password = new JLabel("password");
            passText = new JPasswordField(12);
            p3.add(password);
            p3.add(passText);

            register = new JButton("register");
            login = new JButton("login");
            exit=new JButton("exit");
            p4.add(register);
            p4.add(login);
            p4.add(exit);

            register.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = userText.getText().trim();
                    String password = new String(passText.getPassword());
                    String dt = LocalDate.now().toString();

                    User u = new User(username, password, dt);
                    boolean b = new Register().register(u);
                    if (b) {
                        System.out.println("register successful");
                        info.setText("register successful");
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            System.out.println("Successful location");

                            try (Connection conn = DriverManager.getConnection(CUIClass.url, CUIClass.username, CUIClass.password);
                                 PreparedStatement pstmt = conn.prepareStatement(
                                         "INSERT INTO Regist(username, password, regist_date) VALUES (?, ?, ?)")) {
                                pstmt.setString(1, username);
                                pstmt.setString(2, password);
                                pstmt.setString(3, dt);
                                int affectedRows = pstmt.executeUpdate();
                            }
                        } catch (ClassNotFoundException sql_e) {
                            System.err.println("MySQL 驱动未找到！");
                            sql_e.printStackTrace();
                        } catch (SQLException sql_e) {
                            System.err.println("数据库错误：" + sql_e.getMessage());
                            JOptionPane.showMessageDialog(null, "数据库错误：" + sql_e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("register failed");
                    }
                }


            });
            login.addActionListener((ActionEvent e) -> {

                String username = userText.getText().trim();
                String password = new String(passText.getPassword());
                String dt = LocalDate.now().toString();
                User u = new User(username, password, dt);
                Login l = new Login();
                boolean i = l.login(u);
                if (i) {
                    info.setText("login success");
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        System.out.println("Successful location");

                        Connection conn = DriverManager.getConnection(CUIClass.url, CUIClass.username, CUIClass.password);
                        PreparedStatement pstmt = conn.prepareStatement(
                                 "INSERT INTO Login(username, password, login_date) VALUES (?, ?, ?)");
                        pstmt.setString(1, username);
                        pstmt.setString(2, password);
                        pstmt.setString(3, dt);
                        int affectedRows = pstmt.executeUpdate();
                        pstmt=conn.prepareStatement("SELECT * FROM Regist WHERE username = ?");
                        pstmt.setString(1, username);
                        ResultSet rs = pstmt.executeQuery();
                        while (rs.next()) {
                            String re_username=rs.getString("username");

                            String re_dt=rs.getString("regist_date");
                            System.out.println("用户注册信息："+" "+re_username+" "+" "+re_dt);
                        }
                        pstmt=conn.prepareStatement("SELECT * FROM Login WHERE username = ?");
                        pstmt.setString(1, username);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            String re_username=rs.getString("username");

                            String re_dt=rs.getString("login_date");
                            System.out.println("用户登录信息："+" "+re_username+" "+re_dt);
                        }

                    } catch (ClassNotFoundException sql_e) {
                        System.err.println("MySQL 驱动未找到！");
                        sql_e.printStackTrace();
                    } catch (SQLException sql_e) {
                        System.err.println("数据库错误：" + sql_e.getMessage());
                        JOptionPane.showMessageDialog(null, "数据库错误：" + sql_e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                    }

                    new Run().run();
                } else {
                    info.setText("login failed");
                }
            });
            exit.addActionListener((ActionEvent e)->{
                System.exit(0);
            });

            info = new JLabel("Gui页面");
            p2.add(info, BorderLayout.SOUTH);

        }
    }
}
