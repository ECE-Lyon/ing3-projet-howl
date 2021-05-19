import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Page pour l'enregistrement
 */
public class Register extends JFrame{
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel BottomPanel;
    private JLabel Title;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton registerButton;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JRadioButton regularRadio;
    private JRadioButton seniorRadio;
    private JRadioButton childrenRadio;

    public Register(){
        /**
         * Affichage
         */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ButtonGroup group = new ButtonGroup();
        group.add(regularRadio);
        group.add(seniorRadio);
        group.add(childrenRadio);

        this.getContentPane().add(MainPanel);

        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        /**>*/

        /**
         * Enregistrement
         */
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = "";
                if(regularRadio.isSelected()){
                    type = "regular";
                }else if(seniorRadio.isSelected()){
                    type = "senior";
                }else if(childrenRadio.isSelected()){
                    type = "children";
                }
                String username = usernameField.getText();
                String password = passwordField.getText();

                //Récupération des infos pour la registration dans un objet Members
                Members member = new Members(username,password,type);

                /**
                 * Ajout du nouveau membre dans la BDD
                 */
                try{
                    String query = "INSERT INTO MEMBERS(MEMBER_USERNAME, MEMBER_PASSWORD, MEMBER_TYPE) VALUES (?,?,?)";
                    Connection con = DriverManager.getConnection("jdbc:h2:./default");
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.setString(3, type);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registration succeed");
                    new MovieList(username);
                    dispose();


                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);

                }
                /**>*/

            }

        });

    }
}
