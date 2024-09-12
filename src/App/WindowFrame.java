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

    private boolean useCustomWindow = true;
    private Point minimisedPosition = new Point(0,0);
    private Point windowPosition = new Point(0,0);


    private static enum WindowState {
        MINIMISED,
        FULLSCREEN,
        DEFAULT
    }
    private Point mousePressPosition = new Point(0,0);
    private Point mousePosition = new Point(0,0);
    private Point lastmousePosition = new Point(0,0);
    private ResizeDirection resizeState = null;
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


    private final MouseAdapter mouseAdapter = new MouseAdapter(){
        public void mousePressed(MouseEvent e) {
            mousePressPosition = e.getPoint();
            mousePosition = mousePressPosition;
            WindowFrame.this.mousePressed(e);
        }
        public void mouseDragged(MouseEvent e) {
            lastmousePosition = mousePosition;
            mousePosition = e.getPoint();
            WindowFrame.this.mouseDragged(e);
        }

        public void mouseReleased(MouseEvent e) {
            resizeState = null;
            WindowFrame.this.mouseReleased(e);
        }

    };



    public WindowFrame() {
        super();
        setTitle("My First GUI");
        setBackground(Color.GREEN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Integer.parseInt(Settings.getInstance().getAppSetting("width")),
                Integer.parseInt(Settings.getInstance().getAppSetting("height")));

        setCustomBorder(useCustomWindow);
        setMinimumSize(new Dimension(200,200));
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);




        //this.setUndecorated(true);
    }

    public void mousePressed(MouseEvent e) {
        updateResizeState(e);

    }

    public void mouseDragged(MouseEvent e) {
        if (resizeState != null) {
            ResizeWindow();
        } else {
            MoveWindow(e);
        }
    }
    private void mouseReleased(MouseEvent e) {
        resizeState = null;
    }

    private void MoveWindow(MouseEvent e) {
        Point location = getLocation();
        int x = location.x + mousePosition.x - mousePressPosition.x;
        int y = location.y + mousePosition.y - mousePressPosition.y;
        setLocation(x, y);
    }
    private void ResizeWindow() {
        Rectangle rect = getBounds();
        Dimension minSize = getMinimumSize();
        Point offset = new Point(mousePressPosition.x - mousePosition.x, mousePressPosition.y - mousePosition.y);
        switch (resizeState) {
            case TOP:
                int newHeight = rect.height + offset.y;
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y - offset.y, rect.width, newHeight);
                    setBounds(rect);
                }
                break;
            case BOTTOM:
                newHeight = mousePosition.y;
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y, rect.width, newHeight);
                    setBounds(rect);
                }

                break;
            case LEFT:
                int newWidth = rect.width + offset.x;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x - offset.x, rect.y, newWidth, rect.height);
                    setBounds(rect);
                }
                break;
            case RIGHT:
                newWidth = mousePosition.x;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x, rect.y, newWidth, rect.height);
                    setBounds(rect);
                }

                break;
            case TOP_LEFT:
                newWidth = rect.width + offset.x;
                newHeight = rect.height + offset.y;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x - offset.x, rect.y, newWidth, rect.height);
                }
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y - offset.y, rect.width, newHeight);
                }
                setBounds(rect);
                break;
            case TOP_RIGHT:
                newWidth = mousePosition.x;
                newHeight = rect.height + offset.y;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x, rect.y, newWidth, rect.height);
                }
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y - offset.y, rect.width, newHeight);
                }
                setBounds(rect);
                break;
            case BOTTOM_LEFT:
                newWidth = rect.width + offset.x;
                newHeight = mousePosition.y;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x - offset.x, rect.y, newWidth, rect.height);
                }
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y, rect.width, newHeight);
                }
                setBounds(rect);
                break;
            case BOTTOM_RIGHT:
                newWidth = mousePosition.x;
                newHeight = mousePosition.y;
                if (newWidth > minSize.width) {
                    rect.setBounds(rect.x, rect.y, newWidth, rect.height);
                }
                if (newHeight > minSize.height) {
                    rect.setBounds(rect.x, rect.y, rect.width, newHeight);
                }
                setBounds(rect);
                break;
        }

        revalidate();
        repaint();
    }

    //check mouse position to see if within resize area
    //sets resizeState, to be used elsewhere
    private void updateResizeState(MouseEvent e) {
        int w = getWidth();
        int h = getHeight();

        Rectangle rect = getBounds();

        resizeState = null;
        //right and left sides
        if (mousePosition.x >= 0 && mousePosition.x <=  RESIZE_AREA) {
            resizeState = ResizeDirection.LEFT;
        } else if (mousePosition.x <= w && mousePosition.x >= w - RESIZE_AREA) {
            resizeState = ResizeDirection.RIGHT;
        }
        //top side
        if (mousePosition.y >= 0 && mousePosition.y <= RESIZE_AREA) {
            //if already right or left, set to top right or top left
            if (resizeState == ResizeDirection.RIGHT) {
                resizeState = ResizeDirection.TOP_RIGHT;
            } else if (resizeState == ResizeDirection.LEFT) {
                resizeState = ResizeDirection.TOP_LEFT;
            } else {
                //top
                resizeState = ResizeDirection.TOP;
            }
        //bottom side
        } else if (mousePosition.y <= h && mousePosition.y >= h - RESIZE_AREA) {
            //if already right or left, set to bottom right or bottom left
            if (resizeState == ResizeDirection.RIGHT) {
                resizeState = ResizeDirection.BOTTOM_RIGHT;
            } else if (resizeState == ResizeDirection.LEFT) {
                resizeState = ResizeDirection.BOTTOM_LEFT;
            } else {
                //bottom
                resizeState = ResizeDirection.BOTTOM;
            }
        }
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
