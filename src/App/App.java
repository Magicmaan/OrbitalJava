package App;

import javax.swing.*;
import java.awt.*;

import App.Settings.Settings;
import GUI.Viewport;

import GUI.ContextBar.ContextBar;


public class App {
    private static WindowFrame windowFrame;
    private static JPanel window;
    private static JPanel content_panel;
    private static App instance = null;



    private App(){
        System.out.println("App Constructor");
        createWindow();

        window.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        content_panel.setBackground(Color.BLUE);

        JButton button = new JButton("PressN");

        ContextBar contextBar = new ContextBar();
        content_panel.add(contextBar, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setPreferredSize(new Dimension(300, 500));
        content_panel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.YELLOW);
        rightPanel.setPreferredSize(new Dimension(32, 500));
        content_panel.add(rightPanel, BorderLayout.EAST);



        ImageIcon vport = new ImageIcon("src/resources/default_canvas.png");
        Image newimg = vport.getImage().getScaledInstance(500, 500,  Image.SCALE_FAST);

        JLabel viewport_image = new JLabel(new ImageIcon(newimg));
        viewport_image.setMaximumSize(new Dimension(500,500));
        viewport_image.setPreferredSize(new Dimension(500,500));

        Viewport viewport = new Viewport();
        viewport.setBackground(Color.RED);

        content_panel.add(viewport, BorderLayout.CENTER);
        windowFrame.pack();
        windowFrame.setVisible(true);
    };
    public static void main(String[] args){
        System.out.println("App Main");
        //frame = the window itself
        //panel = the content, where you put in components
        //BorderLayout = the layout manager, how the components are arranged

    }
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }
    public void shutdown(){
        windowFrame.dispose();
        System.exit(0);
    }
    private static void updateWindow(){
        window.revalidate();
        window.repaint();
    }

    public JPanel Window(){
        if (window == null){
            System.out.println("Window is null");
            return null;
        }
        return window;//the window people can use / edit
    }
    private static void createWindow(){
        Dimension window_size = new Dimension(Integer.parseInt(Settings.getInstance().getAppSetting("width")),
                                              Integer.parseInt(Settings.getInstance().getAppSetting("height")));

        windowFrame = new WindowFrame();
        windowFrame.setLocation(100,100);

        /**Create the main Window*/
        //window_frame = new JFrame("My First GUI");
        //window_frame.setBackground(Color.GREEN);
        //window_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window_frame.setSize(window_size);
        //window_frame.setUndecorated(false);

        //The Window
        window = new JPanel(new BorderLayout());
        window.setPreferredSize(window_size);
        window.setBackground(Color.RED);

        //add custom titlebar
        CustomTitleBar TitleBar = new CustomTitleBar();
        window.add(TitleBar.getTitleBar(), BorderLayout.NORTH);


        content_panel = new JPanel(new BorderLayout());
        window.add(content_panel,BorderLayout.CENTER);

        windowFrame.setContentPane(window);


    }
}


