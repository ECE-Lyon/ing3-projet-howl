import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.*;

public class RemoveMovie extends JFrame implements ActionListener {

    static String movieToDelete = "";

    public RemoveMovie() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.getContentPane().add(MainPanel);
        this.setLayout(new FlowLayout());
        //add(new NewImagePanel("movie.jpg"));


        ButtonGroup movieGroup = new ButtonGroup();


        try{
            Connection con = DriverManager.getConnection("jdbc:h2:./default");
            Statement stmt = con.createStatement();
            String sql = "select movie_name, image, movie_id from MOVIES";
            ResultSet rs = stmt.executeQuery(sql);
            //ImageIcon icon = new ImageIcon("movie.jpg");

            while(rs.next()){
                //System.out.println(rs.getString(1));
                JRadioButton movie = new JRadioButton(rs.getString(1));
                movie.setName(rs.getString(3));
                add(new NewImagePanel(rs.getString(2)));
                movieGroup.add(movie);
                movie.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        movieToDelete = movie.getName();
                        System.out.println("t'as cliqué");
                        System.out.println(movieToDelete);


                    }
                });

                this.add(movie);


            }



        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);


        }
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
        getContentPane().add(submit);

        setSize(500,500);
        setLocationRelativeTo(null);
        //pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /*public class NewImagePanel extends JPanel {

        private BufferedImage img;

        public NewImagePanel(String movie) {
            try {
                img = ImageIO.read(RemoveMovie.class.getResource("/resources/"+movie));
            } catch (IOException ex) {
                System.out.println("Could not load image");
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(50, 50);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }*/
}

