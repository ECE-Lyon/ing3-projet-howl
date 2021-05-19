import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Page de gestion pour les admins
 */
public class AdminPage extends JFrame{
    private JPanel MainPanel;
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton homeButton;

    public AdminPage() {
        /**
         * Affichage
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(MainPanel);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        /**>*/

        /**
         * Renvoie vers la page d'ajout de film AddMovieForm
         */
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMovieForm();
                dispose();

            }
        });

        /**
         * Renvoi vers la page de suppression de film RemoveMovie
         */
        removeMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new RemoveMovie();
                new RemoveMovie();
                dispose();

            }
        });

        /**
         * Retour à la page d'accueil
         */
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });
    }

}
