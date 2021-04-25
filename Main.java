import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class Main {

  private static final String TITLE = "Jogo da Velha";
  private static int size1 = 3;
  private static final int PLAYER_1 = 1;
  private static final int PLAYER_2 = 2;
  int playerTime = 1;

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
    playPlayer();
    playComputer();
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
       southPanel.add(button[i][j]);
       button[i][j].setEnabled(false);
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

  private void logic() {
    for(int k = 0; k < size1; k++) {
      for(int l = 0; l < size1; l++) {
        JButton currentButton = button[k][l];
        currentButton.setEnabled(true);
        currentButton.addActionListener(e -> {
          if(!currentButton.getText().equals("")) return ;
          if(playerTime == PLAYER_1) {
            currentButton.setText("X");
            playerTime = PLAYER_2;
          } else {
            currentButton.setText("O");
            playerTime = PLAYER_1;
          }
          boolean endGame = verifyWin();
          if(endGame) {
            continueGame();
          }
        });
      }
    }
  }

  private void continueGame() {
    Object[] options2 = {"Sim", "Não"};
    int continueOption = JOptionPane.showOptionDialog(null, "Quer continuar jogando?", "Fim do Jogo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, null);
    if(continueOption == 1) {
      System.exit(0);
    } else {
      clean();
    }
  }

  private void playPlayer() {
    player.addActionListener(e -> {
      logic();
    });
  }

  private void playComputer() {
    computer.addActionListener(e -> {

    });
  }

  private void clean() {
    for(int m = 0; m < size1; m++){
      for(int n = 0; n < size1; n++) {
        button[m][n].setText("");
      }
    }
  }

  private boolean verifyWin() {
    String b00 = button[0][0].getText();
    String b01 = button[0][1].getText();
    String b02 = button[0][2].getText();
    String b10 = button[1][0].getText();
    String b11 = button[1][1].getText();
    String b12 = button[1][2].getText();
    String b20 = button[2][0].getText();
    String b21 = button[2][1].getText();
    String b22 = button[2][2].getText();

    // Line
    if(!b00.equals("") && b00.equals(b01) && b00.equals(b02)){
      return true;
    }
    if(!b10.equals("") && b10.equals(b11) && b10.equals(b12)){
      return true;
    }
    if(!b20.equals("") && b20.equals(b21) && b20.equals(b22)){
      return true;
    }
    // Colum
    if(!b00.equals("") && b00.equals(b10) && b00.equals(b20)){
      return true;
    }
    if(!b01.equals("")  && b01.equals(b11) && b01.equals(b21)){
      return true;
    }
    if(!b02.equals("") && b02.equals(b12) && b02.equals(b22)){
      return true;
    }
    // Diagonal
    if(!b00.equals("") && b00.equals(b11) && b00.equals(b22)){
      return true;
    }
    if(!b02.equals("")  && b02.equals(b11) && b02.equals(b20)){
      return true;
    }

    return false; 

  }

  public static void main(String[] args) {
    new Main();
  }
}
