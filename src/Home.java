import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Page d'accueil
 */

public class Home extends JFrame{
    private JPanel MainPanel;
    private JPanel LeftPanel;
    private JPanel RightPanel;
    private JButton LoginButton;
    private JLabel Title;
    private JButton RegisterButton;
    private JButton AdminButton;

    public Home() {

        this.getContentPane().add(MainPanel);
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        /**
         * Bouton pour le login : renvoi vers la page de login
         */
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        /**
         * Bouton de register : renvoi vers la page de register
         */
        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                dispose();


            }
        });

        /**
         * Bouton admin : renvoi vers la page de connexion pour les admins AdminLogin
         */
        AdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin();
                dispose();
            }
        });
    }
}
