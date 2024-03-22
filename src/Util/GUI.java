/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
        

/**
 *
 * @author harle
 */
public class GUI {
    private PSNUsers psnUsers;

    private JFrame frame;
    private JTextField txtName;
    private JTextArea infoArea;
    private JTextField txtGame;
    private JTextField txtTrofeo;
    private JComboBox<Trophy> boxTrofeo;
    

    public GUI() {
        psnUsers = new PSNUsers();
        initialize();
        frame.setLocationRelativeTo(null);
    }

    private void initialize() {
        frame = new JFrame("PSN Users");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameLabel.setBounds(20, 20, 80, 25);
        frame.add(usernameLabel);

        txtName = new JTextField();
        txtName.setBounds(100, 20, 150, 25);
        frame.add(txtName);

        JLabel gameLabel = new JLabel("Juego:");
        gameLabel.setBounds(260, 20, 80, 25);
        frame.add(gameLabel);

        txtGame = new JTextField();
        txtGame.setBounds(340, 20, 150, 25);
        frame.add(txtGame);

        JLabel trophyLabel = new JLabel("Trofeo:");
        trophyLabel.setBounds(20, 50, 80, 25);
        frame.add(trophyLabel);

        txtTrofeo = new JTextField();
        txtTrofeo.setBounds(100, 50, 150, 25);
        frame.add(txtTrofeo);

        JLabel trophyTypeLabel = new JLabel("Tipo Trofeo:");
        trophyTypeLabel.setBounds(260, 50, 100, 25);
        frame.add(trophyTypeLabel);

        boxTrofeo = new JComboBox<>(Trophy.values());
        boxTrofeo.setBounds(370, 50, 120, 25);
        frame.add(boxTrofeo);

        JButton addButton = new JButton("Añadir Usuario");
        addButton.setBounds(20, 80, 120, 25);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtName.getText();
                try {
                    if (psnUsers.addUser(username)) {
                        infoArea.setText("Usuario agregado Exitosamente");
                    } else {
                        infoArea.setText("Usuario ya Existe");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(addButton);

        JButton deactivateButton = new JButton("Desactivar Usuario");
        deactivateButton.setBounds(150, 80, 150, 25);
        deactivateButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String username = txtName.getText();
                try {
                    if (psnUsers.deactivateUser(username)) {
                        infoArea.setText("Usuario Desactivado Exitosamente");
                    } else {
                        infoArea.setText("No hay Usuario");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(deactivateButton);

        JButton addTrophieButton = new JButton("Añadir Trofeo");
        addTrophieButton.setBounds(310, 80, 120, 25);
        addTrophieButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String username = txtName.getText();
                String game = txtGame.getText();
                String trophyName = txtTrofeo.getText();
                Trophy trophyType = (Trophy) boxTrofeo.getSelectedItem();
                try {
                    if (psnUsers.addTrophieTo(username, game, trophyName, trophyType)) {
                        infoArea.setText("Trofeo añadido con Exito");
                    } else {
                        infoArea.setText("No existe el Usuario");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(addTrophieButton);

        JButton getInfoButton = new JButton("Mostrar Info");
        getInfoButton.setBounds(440, 80, 120, 25);
        getInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtName.getText();
                try {
                    String userInfo = psnUsers.playerInfo(username);
                    infoArea.setText(userInfo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.add(getInfoButton);

        infoArea = new JTextArea();
        infoArea.setBounds(20, 120, 540, 220);
        frame.add(infoArea);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
