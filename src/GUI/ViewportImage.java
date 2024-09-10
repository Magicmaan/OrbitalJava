package GUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.View;

public class ViewportImage extends JLabel {
    ImageIcon imageIcon = new ImageIcon("src/resources/default_canvas.png");
    //Image image;

    private static final MouseAdapter mouse = new MouseAdapter(){
        public void mouseClicked(MouseEvent e) {
            //whole click event, activates after press and release
            Point point = e.getPoint();
            System.out.println("Mouse clicked at position: " + point);
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

    public ViewportImage(){
        this.setIcon(imageIcon);
        this.addMouseListener(mouse);


    }

    public Image scaled(float scale){
        Image image = this.imageIcon.getImage();
        if (scale <= 0) {
            scale = 0.01f;
        }
        return image.getScaledInstance((int) (image.getWidth(null) * scale),
                (int) (image.getHeight(null) * scale), Image.SCALE_FAST);
    }

}
