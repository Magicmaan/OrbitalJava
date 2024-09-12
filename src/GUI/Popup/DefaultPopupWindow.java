package GUI.Popup;
import javax.swing.*;
import java.awt.*;


abstract class DefaultPopupWindow extends JFrame {
    private JPanel panel;
    private JLabel label;
    private JButton button;
    public DefaultPopupWindow() {
        panel = new JPanel();
        label = new JLabel("This is a default popup window.");
        button = new JButton("Close");
        button.setAction(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        panel.add(label);
        panel.add(button);

        setVisible(true);
    }


}
