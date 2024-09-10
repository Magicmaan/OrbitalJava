package GUI.ContextBar;
import javax.swing.*;
import java.awt.*;

public class ContextBar extends JMenuBar {
    public ContextBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(800, 30));
        setBackground(new Color(0x777777));

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");

        add(file);

        addMenu(file, "New");

    }

    private static void addMenu(JMenu menu, String name) {
        JMenuItem item = new JMenuItem(name);
        menu.add(item);
    }
    private static void addMenuButton(JMenu menu, String name, String icon, String action) {
        JMenuItem item = new JMenuItem(name);
        item.setIcon(new ImageIcon(icon));
        menu.add(item);
    }
}
