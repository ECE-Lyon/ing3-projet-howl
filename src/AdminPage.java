import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame{
    private JPanel MainPanel;
    private JButton addMovieButton;
    private JButton removeMovieButton;
    private JButton homeButton;

    public AdminPage() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(MainPanel);
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMovieForm();
                dispose();

            }
        });
        removeMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new RemoveMovie();
                new RemoveMovie();
                dispose();

            }
        });
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });
    }

}
