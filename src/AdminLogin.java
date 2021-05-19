import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminLogin extends JFrame{
    private JPanel MainPanel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton submitButton;
    private JLabel errorLabel;

    Users users = new Users();

    public AdminLogin() {
        /**
         * Connexion à la BDD
         */
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select * from ADMINS";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Récupération des admins dans une map
                users.addUser(rs.getString(1),rs.getString(2));
            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        /**>*/

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.getContentPane().add(MainPanel);

        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);

        /**
         * Test de connexion au clic du bouton submit
         */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText();

                if(users.usersList.containsKey(username)){
                    if(users.usersList.get(username).equals(password)){
                        //Redirection vers la page AdminPage si l'admin existe
                        JOptionPane.showMessageDialog(null, "Connexion succeed");
                        new AdminPage();
                        dispose();

                    }else {
                        errorLabel.setText("le mot de passe est incorrect");
                        System.out.println("le mot de passe est incorrect");
                    }
                }else {
                    errorLabel.setText("L'identifiant est incorrect");
                    System.out.println("L'identifiant est incorrect");

                }

            }
        });

    }
}
