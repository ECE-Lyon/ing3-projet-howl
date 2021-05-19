import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Page pour ajouter un film
 */
public class AddMovieForm extends JFrame{
    private JPanel MainPanel;
    private JTextField nameField;
    private JTextField genreField;
    private JTextField durationField;
    private JTextField dateField;
    private JButton submitButton;
    private JTextField seatsField;
    private JTextField priceField;
    private JTextField imageField;

    public AddMovieForm() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(MainPanel);

        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);

        /**
         * Bouton sumbit : déclenche l'insertion dans la BDD
         */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //On stock les infos que du film dans un objet Movies
                Movies movie = new Movies(nameField.getText(),genreField.getText(),durationField.getText(),
                        dateField.getText(),Integer.parseInt(seatsField.getText()),Integer.parseInt(priceField.getText()), imageField.getText());

                /**
                 * Connexion et requete à la BDD
                 */
                try{
                    String query = "INSERT INTO MOVIES(movie_name, movie_genre, movie_duration, movie_releasedate, movie_numberofseats, movie_price, image) VALUES (?,?,?,?,?,?,?)";
                    Connection con = DriverManager.getConnection("jdbc:h2:./default");
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, movie.getName());
                    pst.setString(2, movie.getGenre());
                    pst.setString(3, movie.getDuration());
                    pst.setString(4, movie.getReleaseDate());
                    pst.setInt(5, movie.getNumberOfSeats());
                    pst.setInt(6, movie.getPrice());
                    pst.setString(7, movie.getImage());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Movie added");

                    //Redirection vers la page d'accueil des admins
                    new AdminPage();
                    dispose();


                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);

                }

            }
        });

        /**
         * ajout du chemin d'une image au clic sur le champ correspondant
         */
        imageField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                System.out.println(filename);
                imageField.setText(filename.substring(46));
            }
        });
    }
}
