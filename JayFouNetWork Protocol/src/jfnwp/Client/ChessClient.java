package jfnwp.Client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jfnwp.ChessClient.ChessBoardForDisplay;
import jfnwp.ChessClient.ChessMessage;
import jfnwp.ChessClient.UserInteractions;
import jfnwp.Interfaces.Color;

public class ChessClient extends Client {

	protected static JLayeredPane layeredPane = new JLayeredPane();
    protected static JButton score = new JButton("Score");
    protected static JButton onlineGame = new JButton("Play Chess !");
    protected static JButton cancel = new JButton("Cancel");
    protected static JButton joueur;
    /****** Ask The Name ******/
    protected static JTextField username = new JTextField();
    public static JLabel text;
    static JButton startBtn = new JButton("start");
    /******* MENU *********/
    private JMenuBar menuBar = new JMenuBar();
    private JMenu jeuMenu = new JMenu("Jeu");
    private JMenu options = new JMenu("Option");
    private JMenuItem cancel2 = new JMenuItem("Cancel");
    private JMenuItem quit = new JMenuItem("Quit");
    private UserInteractions inter;
    
    protected static JFrame fenetre;
    protected String player= "";
    private Color tourJoueur;
    
    private ChessMessage m;

    public void start()  {

    	m = new ChessMessage(super.sock);
    	player = super.name;
    	
        fenetre = new JFrame("Chess");
        fenetre.setSize(610, 680);
      
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.pack();

        cancel2.addActionListener(new ActionListener() {
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
                    ((ChessBoardForDisplay) inter.chessBoard).refresh(inter.layerPane);
                }
            }
        });
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        this.jeuMenu.add(quit);
        this.jeuMenu.add(cancel2);

        this.menuBar.add(jeuMenu);
        this.menuBar.add(options);

        fenetre.setJMenuBar(menuBar);
        fenetre.setVisible(true);

        display_menu();

        onlineGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //afficher_deuxjoueurs();                
                
                /*startBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        if (! username.getText().isEmpty()) {
                        	player = username.getText();*/
                        
                            fenetre.setTitle("Chess - " + player);
                            inter = new UserInteractions();

                            layeredPane.setPreferredSize(new Dimension(610, 680));
                            layeredPane.addMouseListener(inter);

                            ChessBoardForDisplay chessboard = new ChessBoardForDisplay();

                            inter.setChessBoard(chessboard);
                            inter.setLayerPane(layeredPane);
                            inter.setGraph(layeredPane.getGraphics());
                            inter.setModedeuxJoueurs(false);

                            chessboard.refresh(layeredPane);
                        }
                  /*  }
                });
            }*/
        });
    }

    public void display_menu() {
        layeredPane.removeAll();
        
        fenetre.setSize(615, 658);
        fenetre.setBackground(java.awt.Color.BLACK);
        fenetre.setTitle("Chess");
        
        JPanel panelGreen = new JPanel();
        panelGreen.setBounds(200, 100, 100, 100);
        panelGreen.setOpaque(false);
        panelGreen.add(onlineGame);
        layeredPane.add(panelGreen);
        
        layeredPane.add(getImage("ressources/chess_menu_bg.jpg"));
        
        fenetre.setLocationRelativeTo(null);
        layeredPane.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        
        this.onlineGame.setLocation(100, 100);

        fenetre.setContentPane(layeredPane);
        fenetre.setVisible(true);
    }

    public void retour_menu() {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	display_menu();
            }
        });
    }

    public void retour_joueur2() {
    	cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            	afficher_deuxjoueurs();
            }
        });
    }
    
    public void afficher_deuxjoueurs() {
        layeredPane.removeAll();
        fenetre.setTitle("Chess - Player");

        layeredPane.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        GridLayout grid = new GridLayout(10, 1, 10, 10);
        layeredPane.setLayout(grid);
       
        text = new JLabel("Your name ?");
        
        layeredPane.add(text);
        layeredPane.add(username);
        layeredPane.add(startBtn);
        layeredPane.add(cancel);

        fenetre.setContentPane(layeredPane);
        fenetre.setVisible(true);
        
        retour_menu();
    }
    
    private final JLabel getImage(String a) {
        URL url = getClass().getClassLoader().getResource(a);
        ImageIcon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        return label;
    }
}
