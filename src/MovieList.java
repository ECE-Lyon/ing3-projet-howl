import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * Page pour selectionner le film à acheter
 */
public class MovieList extends JFrame {

    static String movieToBuy = "";//on y stockera l'id du film pour le payement

    public MovieList(String userId) {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
/**
 * Mise en place de l'affichage
 */
        this.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setLayout(new FlowLayout());
        centerPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());

        ButtonGroup movieGroup = new ButtonGroup();

        JTextField numberOfTicketsField = new JTextField("number of tickets");
        numberOfTicketsField.setMinimumSize(new Dimension(100,20));
        bottomPanel.add(numberOfTicketsField);

        JButton home = new JButton("disconnect");
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });


        JButton submit = new JButton("Buy tickets");
        bottomPanel.add(submit);
        bottomPanel.add(home);

        this.add(bottomPanel,BorderLayout.SOUTH);
/**>*/


/**
 * Récupère les films dans la BDD
 */
        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select movie_name, image, movie_id, movie_genre, movie_duration, movie_releasedate  from MOVIES";
            ResultSet rs = stmt.executeQuery(sql);


            while(rs.next()){
                /*Ajout des éléments sur la page pour chaque film*/
                JRadioButton movie = new JRadioButton(rs.getString(1));
                //JLabel description = new JLabel(rs.getString(1)+"\n"+rs.getString(4));


                movie.setName(rs.getString(3));//on enregistre l'id du film dans le nom du bouton radio

                topPanel.add(new NewImagePanel(rs.getString(2)));
               // topPanel.add(description);


                movieGroup.add(movie);
                /**
                 * Récupère l'id du film stocké dans le nom des boutons radio
                 */
                movie.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movieToBuy = movie.getName();

                    }
                });
                /**>*/
                centerPanel.add(movie);


            }
            //topPanel.setLayout(new GridLayout(3,3));



        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);


        }
/**>*/

        /**
         * Ouvre la page de payement après submit
         */
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());
                new Payement(Integer.parseInt(movieToBuy),userId,numberOfTickets);
                dispose();

            }
        });
        /**>*/

        /**
         * Affichage
         */
        this.add(topPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        /*getContentPane().add(numberOfTicketsField);
        getContentPane().add(submit);*/

        setSize(500,500);
        setLocationRelativeTo(null);
        //pack();
        setVisible(true);
    }


}
