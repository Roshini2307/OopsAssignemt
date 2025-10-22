import javax.swing.*;
import java.awt.*;

public class ProgressBarExample extends JFrame {
    JProgressBar progressBar;
    
    public ProgressBarExample() {
        setTitle("File Download Progress");
        setSize(400, 150);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        add(progressBar);
        
        setVisible(true);
        
        simulateDownload();
    }
    
    public void simulateDownload() {
        int i = 0;
        while (i <= 100) {
            progressBar.setValue(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i += 5;
        }
        JOptionPane.showMessageDialog(this, "Download Complete!");
    }
    
    public static void main(String[] args) {
        new ProgressBarExample();
    }
}
