import javax.swing.JFrame;

public class UserGUI {
  public static void main(String[] args) {
    // JFrame Settings
    JFrame frame = new JFrame();
    frame.setSize(300, 400);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setTitle("Jogo da Velha");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Open window centered

    
  }
}
