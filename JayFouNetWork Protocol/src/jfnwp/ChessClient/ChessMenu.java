package jfnwp.ChessClient;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import jfnwp.Interfaces.Color;
import jfnwp.chessImplementation.ChessBoard;

public class ChessMenu {
	protected static JLayeredPane layeredPane = new JLayeredPane();
    protected static JButton score = new JButton("Score");
    protected static JButton onePlayer = new JButton("One Player");
    protected static JButton twoPlayer = new JButton("Two Player");
    protected static JButton retour = new JButton("retour");
    protected static JButton retour1 = new JButton("retour");
    protected static JButton retour2 = new JButton("retour");
    protected static JButton joueur;
    /****** DEMANDER LE NOM ******/
    protected static JTextField champs = new JTextField();
    protected static JTextField champs2 = new JTextField();
    public static JLabel texte, texte2;
    static JButton valider = new JButton("valider");
    static JButton valider2 = new JButton("valider");
    /******* MENU BARRE  *********/
    private JMenuBar menuBar = new JMenuBar();
    private JMenu jeuMenu = new JMenu("Jeu");
    private JMenu options = new JMenu("Option");
    private JMenuItem annuler = new JMenuItem("Annuler");
    private JMenuItem quitter = new JMenuItem("Quitter");
    private JMenuItem save = new JMenuItem("Sauvegarder");
    private JMenuItem charger = new JMenuItem("Charger la dernière partie");
    private UserInteractions inter;
    /**********************************/
    protected static JFrame fenetre;
    protected String joueur1= "", joueur2= "";

    public ChessMenu() {

        fenetre = new JFrame("Chess");
        fenetre.setSize(610, 680);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.pack();

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if( ! inter.chessBoardPrecedent.equals(inter.chessBoard) ) {
                    inter.setChessBoard(inter.chessBoardPrecedent);
                    if(inter.modeDeuxJoueurs){
                        if( inter.tour == Color.Black ){
                            inter.tour = Color.White;
                        } else {
                            inter.tour = Color.Black;
                        }
                    }
                    inter.chessBoard.refresh(inter.layerPane);
                }
            }
        });
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.jeuMenu.add(quitter);
        this.jeuMenu.add(annuler);

        this.menuBar.add(jeuMenu);
        this.menuBar.add(options);
        //-------------------------

        fenetre.setJMenuBar(menuBar);
        fenetre.setVisible(true);

        afficher_menu();

        twoPlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    afficher_deuxjoueurs();                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ChessMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                valider2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        if ( ! champs.getText().isEmpty() && ! champs2.getText().isEmpty()) {
                            joueur1 = champs.getText();
                            joueur2 = champs2.getText();
                        
                            fenetre.setTitle("Chess - " + joueur1 + " Vs " + joueur2);
                            inter = new UserInteractions();

                            layeredPane.setPreferredSize(new Dimension(610, 680));
                            layeredPane.addMouseListener(inter);

                            ChessBoard chessboard = new ChessBoard();

                            inter.setChessBoard(chessboard);
                            inter.setLayerPane(layeredPane);
                            inter.setGraph(layeredPane.getGraphics());
                            inter.setModedeuxJoueurs(true);

                            chessboard.refresh(layeredPane);
                        }
                    }
                });
            }
        });
        
        onePlayer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    afficher_unJoueur();                
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ChessMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                valider2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        if ( ! champs.getText().isEmpty()) {
                            joueur1 = champs.getText();
                            joueur2 = champs2.getText();
                        
                            fenetre.setTitle("Chess - " + joueur1 + " Vs Ordi");
                            inter = new UserInteractions();

                            layeredPane.setPreferredSize(new Dimension(610, 680));
                            layeredPane.addMouseListener(inter);

                            ChessBoard chessboard = new ChessBoard();

                            inter.setChessBoard(chessboard);
                            inter.setLayerPane(layeredPane);
                            inter.setGraph(layeredPane.getGraphics());
                            inter.setModedeuxJoueurs(false);

                            chessboard.refresh(layeredPane);
                        }
                    }
                });
            }
        });
    }

    public static void afficher_menu() {
        layeredPane.removeAll();
        
        fenetre.setTitle("Chess");
        layeredPane.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        GridLayout grid = new GridLayout(4, 1, 50, 50);
        layeredPane.setLayout(grid);

        layeredPane.add(onePlayer);
        layeredPane.add(twoPlayer);

        fenetre.setContentPane(layeredPane);
        fenetre.setVisible(true);
    }

    public static void retour_menu() {
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                afficher_menu();
            }
        });
    }

    public static void retour_joueur2() {
        retour2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    afficher_deuxjoueurs();
                } catch (FileNotFoundException e) {
                    e.getMessage();
                }
            }
        });
    }
    
    public static void afficher_deuxjoueurs() throws FileNotFoundException {
        layeredPane.removeAll();
        fenetre.setTitle("Chess - Options");

        layeredPane.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        GridLayout grid = new GridLayout(10, 1, 10, 10);
        layeredPane.setLayout(grid);
       
        texte = new JLabel("Pseudo du premier joueur :");
        texte2 = new JLabel("Pseudo du deuxième joueur :");
        
        layeredPane.add(texte);
        layeredPane.add(champs);
        layeredPane.add(texte2);
        layeredPane.add(champs2);
        layeredPane.add(valider2);
        layeredPane.add(retour);

        fenetre.setContentPane(layeredPane);
        fenetre.setVisible(true);
        retour_menu();
    }
    
    public static void afficher_unJoueur() throws FileNotFoundException {
        layeredPane.removeAll();
        fenetre.setTitle("Chess - Options");

        layeredPane.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        GridLayout grid = new GridLayout(10, 1, 10, 10);
        layeredPane.setLayout(grid);
       
        texte = new JLabel("Pseudo :");
        
        layeredPane.add(texte);
        layeredPane.add(champs);
        layeredPane.add(valider2);
        layeredPane.add(retour);

        fenetre.setContentPane(layeredPane);
        fenetre.setVisible(true);
        retour_menu();
    }
}
