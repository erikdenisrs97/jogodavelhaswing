import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class Main {

  private static final String TITLE = "Jogo da Velha";
  private static int size1 = 3;
  private static final int PLAYER_1 = 1;
  private static final int PLAYER_2 = 2;
  private String player1Name = "";
  private String player2Name = "";
  private int playerTime = 1;
  private int round = 0;
  private String winner = "";

  // Layout
  JFrame frame = new JFrame();
  JPanel southPanel = new JPanel();
  JPanel northPanel = new JPanel();
  JButton button[][] = new JButton[size1][size1];
  JMenuBar mb = new JMenuBar();
  JMenu newGame = new JMenu();
  JMenu info = new JMenu();
  JMenu exit = new JMenu();
  JMenuItem computer = new JMenuItem();
  JMenuItem player = new JMenuItem();
  JLabel whoPlays = new JLabel();

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
       southPanel.add(button[i][j]).setLocation(i, j);
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

   // North Panel Settings
   northPanel.setPreferredSize(new Dimension(290, 150));
   frame.getContentPane().add(northPanel, BorderLayout.NORTH);
   
   // JLabel Settings
   northPanel.add(whoPlays);

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

  private void playerLogic() {
    clean();
    questionNames();
    whoPlays.setText("É a vez do(a) " + player1Name + " jogar.");
    for(int k = 0; k < size1; k++) {
      for(int l = 0; l < size1; l++) {
        JButton currentButton = button[k][l];
        currentButton.setEnabled(true);
        currentButton.addActionListener(e -> {
          if(!currentButton.getText().equals("")) return ;
          if(playerTime == PLAYER_1) {
            currentButton.setText("X");
            playerTime = PLAYER_2;
            whoPlays.setText("É a vez do(a) " + player2Name + " jogar.");
          } else {
            currentButton.setText("O");
            whoPlays.setText("É a vez do(a) " + player1Name + " jogar.");
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

  private void computerLogic() {
    clean();
    player1Name = JOptionPane.showInputDialog(null, "Digite o seu nome: ");
    player2Name = "COMPUTADOR";
    whoPlays.setText("É a vez do(a) " + player1Name + " jogar.");
    for(int k = 0; k < size1; k++) {
      for(int l = 0; l < size1; l++) {
        JButton currentButton = button[k][l];
        currentButton.setEnabled(true);
        currentButton.addActionListener(e -> {
          if(!currentButton.getText().equals("")) return ;
          if(playerTime == PLAYER_1) {
            currentButton.setText("X");
            playerTime = PLAYER_2;
            whoPlays.setText("É a vez do(a) " + player2Name + " jogar.");
            randomPlay();
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
      playerLogic();
    });
  }

  private void playComputer() {
    computer.addActionListener(e -> {
      computerLogic();
    });
  }

  private void questionNames() {
    player1Name = JOptionPane.showInputDialog(null, "O nome do 1º Jogador: ");
    player2Name = JOptionPane.showInputDialog(null, "O nome do 2º Jogador: ");
  }

  private void clean() {
    for(int m = 0; m < size1; m++){
      for(int n = 0; n < size1; n++) {
        button[m][n].setText("");
        round = 0;
      }
    }
  }

  private void randomPlay() {
    double randDouble1 = Math.random() * 2;
    double randDouble2 = Math.random() * 2;
    int int1 = (int)Math.round(randDouble1);
    int int2 = (int)Math.round(randDouble2);
    if(button[int1][int2].getText().equals("")) {
      button[int1][int2].setText("0");
    } else {
      randomPlay();
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
      if(b00.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }

      return true;
    }
    if(!b10.equals("") && b10.equals(b11) && b10.equals(b12)){
      if(b10.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    if(!b20.equals("") && b20.equals(b21) && b20.equals(b22)){
      if(b20.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    // Colum
    if(!b00.equals("") && b00.equals(b10) && b00.equals(b20)){
      if(b00.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    if(!b01.equals("")  && b01.equals(b11) && b01.equals(b21)){
      if(b01.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    if(!b02.equals("") && b02.equals(b12) && b02.equals(b22)){
      if(b02.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    // Diagonal
    if(!b00.equals("") && b00.equals(b11) && b00.equals(b22)){
      if(b00.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }
    if(!b02.equals("")  && b02.equals(b11) && b02.equals(b20)){
      if(b02.equals("X")) {
        winner = player1Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player1Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      } else {
        winner = player2Name;
        JOptionPane.showMessageDialog(null, "O ganhador foi: " + player2Name + ".", "Fim de Jogo", JOptionPane.INFORMATION_MESSAGE);
      }
      return true;
    }

    round++;
    if(round == 9){
      JOptionPane.showMessageDialog(null, "Deu véia!", "Véia", JOptionPane.INFORMATION_MESSAGE);
      continueGame();
    }

    return false; 

  }

  public static void main(String[] args) {
    new Main();
  }
}
