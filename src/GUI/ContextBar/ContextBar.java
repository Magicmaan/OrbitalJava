package GUI.ContextBar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import GUI.Popup.*;

public class ContextBar extends JMenuBar {
    private static ArrayList<JMenu> rootMenus;
    private static JMenu fileMenu;
    private static JMenu editMenu;
    private static JMenu viewMenu;
    private static JMenu helpMenu;
    public ContextBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(800, 30));
        setBackground(new Color(0x777777));

        rootMenus = new ArrayList<JMenu>();

        setupMenu();
        addRootMenu("File");
        addRootMenu("Edit");
        addRootMenu("View");
        addRootMenu("Help");

        validateMenus();

    }
    void addRootMenu(String name) {
        JMenu menu = new JMenu(name);
        menu.setName(name);
        rootMenus.add(menu);
    }
    public JMenu getRootMenu(String name) {
        for (JMenu menu : rootMenus) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    private void validateMenus() {
        //clear menus and refresh from arrays
        removeAll();

        for (JMenu menu : rootMenus) {
            add(menu);
        }
    }

    private void setupMenu() {
        addRootMenu("File");
        addRootMenu("Edit");
        addRootMenu("View");
        addRootMenu("Help");

        JMenu fileMenu = getRootMenu("File");
        addMenuItem(fileMenu, "New", this::NewFileAction);
        //addMenuItem(fileMenu, "Open");
        validateMenus();
    }
    private static void addMenuItem(JMenu menu, String name, ActionListener action) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(action);

        menu.add(item);
    }
    private static void addMenuItem(JMenu menu, String name, ImageIcon icon) {
        JMenuItem item = new JMenuItem(name, icon);

        menu.add(item);
    }
    private static void addMenuButton(JMenu menu, String name, String icon, String action) {
        JMenuItem item = new JMenuItem(name);
        item.setIcon(new ImageIcon(icon));
        menu.add(item);
    }



    public void NewFileAction(ActionEvent e) {
        System.out.println("New File Action");
        NewFile popup = new NewFile();
    }
}
