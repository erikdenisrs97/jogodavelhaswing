import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;

public class Main {

  private static final String TITLE = "Jogo da Velha";
  private static int size1 = 3;
  final int PLAYER_1 = 1;
  final int PLAYER_2 = 2;

  // Layout
  JFrame frame = new JFrame();
  JPanel southPanel = new JPanel();
  JButton button[][] = new JButton[size1][size1];
  JMenuBar mb = new JMenuBar();
  JMenu newGame = new JMenu();
  JMenu info = new JMenu();
  JMenu exit = new JMenu();
  JMenuItem computer = new JMenuItem();
  JMenuItem player = new JMenuItem();

  public Main() {
    initGUI();
    showInfo();
    exitGame();
  }

  private void initGUI() {
   // JFrame Settings
   frame.setBounds(0, 0, 300, 400);
   frame.setResizable(false);
   frame.setTitle(TITLE);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setLocationRelativeTo(null); // Open window centered
   frame.getContentPane().setLayout(new BorderLayout());
   // South JPanel Settings
   southPanel.setPreferredSize(new Dimension(290, 250));
   southPanel.setLayout(new GridLayout(size1, size1));
   frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
   // JButtons size1xsize1
   for(int i = 0; i < size1; i++) {
     for(int j = 0; j < size1; j++) {
       button[i][j] = new JButton();
       button[i][j].setPreferredSize(new Dimension(10, 10));
       southPanel.add(button[i][j]).setLocation(i, j);
     }
   }
   // Menu
   newGame.setText("Jogar");
   info.setText("Sobre");
   exit.setText("Sair");
   mb.add(newGame);
   mb.add(info);
   mb.add(exit);
   newGame.add(computer);
   computer.setText("Contra o computador");
   newGame.add(player);
   player.setText("Contra um jogador");

   frame.setJMenuBar(mb);
   frame.setVisible(true);
  }

  private void showInfo() {
    String message = "Esse é um jogo da velha simples 3x3 proposto pelo professor Eunelson Junior para os alunos do 5º BSI. \n\nDesenvolvedores: Erik Rodrigues e Camila Corrêa";
    info.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        JOptionPane.showMessageDialog(null, message, "Sobre o Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  private void exitGame() {
    Object[] options = {"Sim", "Não"};
    exit.addMouseListener(new MouseInputAdapter() {
      public void mousePressed(MouseEvent me) {
        int selected = JOptionPane.showOptionDialog(null, "Quer mesmo sair do jogo?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
        if(selected == 0) {
          System.exit(0);
        }
      }
    });
  }

  public static void main(String[] args) {
    new Main();
  }
}
