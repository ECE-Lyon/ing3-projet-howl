import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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

        this.getContentPane().add(MainPanel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
            price *=numberOfTickets;
            System.out.println(price);
            //price = Integer.parseInt(rs.getString(1));
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);


        }

        priceLabel.setText("Total: "+price+" €");
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
                    new MovieList(userId);
                    dispose();




                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);

                }




            }
        });
        setSize(500,500);
        setLocationRelativeTo(null);
        //pack();
        setVisible(true);
    }
}
