import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.*;

public class Frogger extends JFrame {

    public Frogger() throws IOException {
        initUI();
    }
    
    private void initUI() throws IOException {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Frogger");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = null;
            try {
                ex = new Frogger();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ex.setVisible(true);
        });
    }
}
