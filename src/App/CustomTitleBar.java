package App;
import App.Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;


public class CustomTitleBar {
    JPanel titleBar;
    JLabel icon;
    JButton minimise_button;
    JButton maximise_button;
    JButton close_button;
    public CustomTitleBar(){
        titleBar = new JPanel();
        titleBar.setBackground(Color.GREEN);
        titleBar.setPreferredSize(new Dimension(800, Integer.parseInt(Settings.getInstance().getAppSetting("titleBarHeight"))));
        titleBar.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        titleBar.setLayout(new BoxLayout(titleBar,BoxLayout.X_AXIS));

        setupGUI();

    }

    public void setupGUI(){
        if (titleBar != null){

            //APP ICON
            icon = new JLabel( new ImageIcon(Settings.getInstance().getAppSetting("icon")));
            titleBar.add(icon);

            JLabel title = new JLabel(Settings.getInstance().getAppSetting("title"));
            titleBar.add(title);
            JLabel version = new JLabel(" " + Settings.getInstance().getAppSetting("version"));
            titleBar.add(version);
            //SPACER
            titleBar.add(Box.createHorizontalGlue());

            minimise_button = new JButton(new ImageIcon("src/resources/GUI/icons/minimise.png"));
            titleBar.add(minimise_button);
            minimise_button.addActionListener(this::onAction);
            minimise_button.setBounds(0,0,10,10);


            maximise_button = new JButton(new ImageIcon("src/resources/GUI/icons/maximise.png"));
            titleBar.add(maximise_button);
            maximise_button.addActionListener(this::onAction);


            close_button = new JButton(new ImageIcon("src/resources/GUI/icons/exit.png"));
            titleBar.add(close_button);
            close_button.addActionListener(this::onAction);

            titleBar.revalidate();
            titleBar.repaint();
        } else {
            System.out.println("TitleBar is null");
        }
    }

    public void onAction(ActionEvent e){
        if (e.getSource() == minimise_button){
            System.out.println("Minimise button pressed");

        } else if (e.getSource() == maximise_button){
            System.out.println("Maximise button pressed");
        } else if (e.getSource() == close_button){
            System.out.println("Close button pressed");
            App.getInstance().shutdown();
        }
    };
    public JPanel getTitleBar(){
        return titleBar;
    }
}
