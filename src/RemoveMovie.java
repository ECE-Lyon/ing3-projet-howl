import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;

/**
 * Page pour la suppression d'un film
 */
public class RemoveMovie extends JFrame implements ActionListener {

    /**
     * variable où on stocke l'id du film à supprimer
     */
    static String movieToDelete = "";

    public RemoveMovie() {

        /**
         * Affichage
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
        ButtonGroup movieGroup = new ButtonGroup();
        /**>*/

        /**
         * Récupération des infos des films dans la BDD
         */
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select movie_name, image, movie_id from MOVIES";
            ResultSet rs = stmt.executeQuery(sql);

            /**
             * Affichage des films
             */
            while(rs.next()){
                JRadioButton movie = new JRadioButton(rs.getString(1));
                movie.setName(rs.getString(3));
                add(new NewImagePanel(rs.getString(2)));
                movieGroup.add(movie);

                /**
                 * Récupération de l'id du film sélectionné
                 */
                movie.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movieToDelete = movie.getName();
                    }
                });


                this.add(movie);//Ajout du bouton radio du film à la page

            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);

        }

        /**
         * Bouton supprimer. Envoie la requete à la BDD au clic
         */
        JButton submit = new JButton("supprimer");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "DELETE FROM MOVIES WHERE movie_id = "+ movieToDelete;
                    Connection con = DriverManager.getConnection("jdbc:h2:./default");
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Movie deleted");
                    new AdminPage();
                    dispose();


                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);

                }
            }
        });
        /**>*/

        /**
         * Affichage
         */
        getContentPane().add(submit);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        /**>*/

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

