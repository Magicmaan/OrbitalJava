package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.View;

import GUI.ViewportImage;

public class Viewport extends JViewport {

    private ViewportImage viewportImage;
    private int scale = 4;
    private Point position = new Point(500,100);


    public Viewport(){
        this.position = position;
        this.viewportImage = new ViewportImage();

        this.updateView();
    }

    public void setScale(int scale){
        this.scale = scale;
        this.updateView();
    }
    public void setImage(Image image){
        //this.image = image;
        this.updateView();
    }
    public void setPosition(Point position){
        this.position = position;
        this.updateView();
    }


    private void updateView(){
        //Image scaled_image = this.image.getScaledInstance(this.image.getWidth(null) * this.scale, this.image.getHeight(null) * this.scale, Image.SCALE_FAST);
        //JLabel label = new JLabel(new ImageIcon(scaled_image));

        Image scaledImage = this.viewportImage.scaled(this.scale);


        this.add(new JLabel(new ImageIcon(scaledImage)));
        this.viewportImage.setLocation(this.position);

        this.setView(this.getComponents()[0]);
        this.setViewSize(new Dimension(128,128));
        this.setViewPosition(new Point(25,100));

        this.revalidate();
        this.repaint();

    }
}
