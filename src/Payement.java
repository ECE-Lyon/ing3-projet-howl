import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Page pour le payement
 */
public class Payement extends JFrame{
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JLabel Title;
    private JButton payButton;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel priceLabel;
    private float price;

    public Payement(Integer movieId, String userId, int numberOfTickets) {

        /**
         * Affichage
         */
        this.getContentPane().add(MainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        /**>*/

        /**
         * Récupération du prix du film dans la BDD
         */
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select movie_price from MOVIES where movie_id = "+movieId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1));
                price = Float.parseFloat(rs.getString(1));
                System.out.println(price);
            }
            //Calcul du prix final
            price *=numberOfTickets;

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        /**>*/

        /**
         * Affichage du prix
         */
        priceLabel.setText("Total: "+price+" €");

        /**
         * Enregistrement de la commande dans les réservations
         */
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "INSERT into reservations (reservation_amount, movie_id, member_id) values (?, ?, ?)";
                    Connection con = DriverManager.getConnection("jdbc:h2:./default");
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setFloat(1, price);
                    pst.setInt(2, movieId);
                    pst.setString(3, userId);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Ticket bought !");

                    /* Si l'enregistrement est réussie, renvoie vers la page de selection de film */
                    new MovieList(userId);
                    dispose();

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });

    }
}
