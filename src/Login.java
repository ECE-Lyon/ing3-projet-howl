import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Page de connexion
 */
public class Login extends JFrame{

    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton submitButton;
    private JLabel errorLabel;
    private JButton guestButton;
    Users users = new Users();

    public Login(){
        /**
         * Connexion à la base de données
         */
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select * from MEMBERS";
            ResultSet rs = stmt.executeQuery(sql);

            /**
             * Récupération des membres dans une MAP
             */
            while(rs.next()){
                users.addUser(rs.getString(1),rs.getString(2));
            }
            /***>*/

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);

        }
        /***>*/

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //arrêter le programme à la fermeture de la fenêtre

        this.getContentPane().add(MainPanel);//ajout des paneaux au contenu de la fenêtre

        //paramètre d'affichage
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
                        //Si l'utilisateur existe, on le renvoie sur la page MovieList
                        JOptionPane.showMessageDialog(null, "Connexion succeed");
                        new MovieList(username);
                        dispose();

                    }else {
                        errorLabel.setText("le mot de passe est incorrect");
                    }
                }else {
                    errorLabel.setText("L'identifiant est incorrect");
                }


            }
        });

        /**
         * Connexion pour les guests
         */
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MovieList("guest");
                dispose();
            }
        });
    }
}
