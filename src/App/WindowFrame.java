package App;

import App.Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowFrame extends JFrame {
    //the custom window container.
    //draws custom border
    //handles custom titlebar?
    //handles resize and move events of window

    private Point minimisedPosition = new Point(0,0);
    private Point windowPosition = new Point(0,0);
    private static enum WindowState {
        MINIMISED,
        FULLSCREEN,
        DEFAULT
    }

    private static enum ResizeDirection {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
    private static final int RESIZE_AREA = 5;
    private static final int TITLE_BAR_HEIGHT = Integer.parseInt(Settings.getInstance().getAppSetting("TitleBarHeight"));
    private boolean useCustomWindow = Boolean.parseBoolean(Settings.getInstance().getAppSetting("customWindow"));

    private static final MouseAdapter mouseAdapter = new MouseAdapter(){
        public void mouseDragged(MouseEvent e) {
            mouseDragged(e);
        }
        public void mousePressed(MouseEvent e) {
            //press event, activates when mouse is pressed
            Point point = e.getPoint();
            System.out.println("Mouse pressed at position: " + point);
        }
        public void mouseReleased(MouseEvent e) {
            //release event, activates when mouse is released
            Point point = e.getPoint();
            System.out.println("Mouse released at position: " + point);
        }
    };

    public WindowFrame() {
        setTitle("My First GUI");
        setBackground(Color.GREEN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(Settings.getInstance().getAppSetting("width")),
                Integer.parseInt(Settings.getInstance().getAppSetting("height")));

        setCustomBorder(useCustomWindow);

        addMouseListener(mouseAdapter);
        addMouseMotionListener(new MouseAdapter() {

        });




        //this.setUndecorated(true);
    }

    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        System.out.println("FRAME Mouse dragged at position: " + point);
    }
    public void setCustomBorder(boolean customBorder){
        //i know can just do setUndecorated(customBorder)
        if (customBorder){
            setUndecorated(true);
            //draw custom border
        } else {
            setUndecorated(false);
        }
    }
}
