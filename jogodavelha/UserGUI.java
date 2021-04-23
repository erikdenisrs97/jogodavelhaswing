import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

public class UserGUI {

  private static final String TITLE = "Jogo da Velha";
  private static int size1 = 3;

  public static void main(String[] argv) {
    // JFrame Settings
    JFrame frame = new JFrame();
    frame.setBounds(0, 0, 300, 400);
    frame.setResizable(false);
    frame.setTitle(TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Open window centered
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setVisible(true);

    // South JPanel Settings
    JPanel southPanel = new JPanel();
    southPanel.setPreferredSize(new Dimension(290, 250));
    southPanel.setLayout(new GridLayout(size1, size1));
    frame.getContentPane().add(southPanel, BorderLayout.SOUTH);

    // JButtons size1xsize1
    JButton button[][] = new JButton[size1][size1];
    for(int i = 0; i < size1; i++) {
      for(int j = 0; j < size1; j++) {
        button[i][j] = new JButton();
        button[i][j].setPreferredSize(new Dimension(10, 10));
        southPanel.add(button[i][j]).setLocation(i, j);
      }
    }

    // Menu
    JMenuBar menu = new JMenuBar();
    JMenu info = new JMenu("Sobre");
    menu.add(info);
    frame.setJMenuBar(menu);

  }
}
