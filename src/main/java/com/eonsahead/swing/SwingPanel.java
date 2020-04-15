package swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * I modified Leon Tabak's swingPanel class to animate a square and a circle 
 * to move diagonally across the frame and change colors rapidly in accordance
 * with 
 * @author pet00
 */

public class SwingPanel extends JPanel implements ActionListener {

    //initializing variables
    private double centerX = 0.0;
    private double centerY = 0.2;
    private double radius = 0.5;
    private double deltaY = 0.02;
    private double deltaX = 0.02;
    private Color color = Color.red;
    

    //executes swingpanel every time this timer ticks
    public SwingPanel() {
        Timer timer = new Timer(50, this);
        timer.start();
    } // SwingPanel()

    public double getCenterX() {
        return this.centerX;
    } // getCenterX()

    public void setCenterX(double x) {
        this.centerX = x;
    } // setCenterX( double )

    public double getCenterY() {
        return this.centerY;
    } // getCenterY()

    public void setCenterY(double y) {
        this.centerY = y;
    } // setCenterY( double )

    public double getRadius() {
        return this.radius;
    } // getRadius()

    public void setRadius(double r) {
        this.radius = r;
    } // setRadius( double )

    public Color getColor() {
        return this.color;
    } // getColor()

    public void setColor(Color c) {
        this.color = c;
    } // setColor( Color )

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        
        int w = this.getWidth();
        int h = this.getHeight();
        
        
        //I have two shapes, so I need to transform them both
        AffineTransform transform1 = new AffineTransform();
        AffineTransform scaling1 = new AffineTransform();
        AffineTransform transform2 = new AffineTransform();
        AffineTransform scaling2 = new AffineTransform();
        scaling1.setToScale(w / 2, h / 2);
        scaling2.setToScale(w/3,h/3);
        AffineTransform translation1 = new AffineTransform();
        AffineTransform translation2 = new AffineTransform();
        translation1.setToTranslation(1.0, 1.0);
        translation2.setToTranslation(0.5, 0.5);
        transform1.concatenate(scaling1);
        transform2.concatenate(scaling2);
        transform1.concatenate(translation1);
        transform2.concatenate(translation2);

        Random rng = new Random();
        //Draws a circle and a square 
        double d = 2 * this.radius;
        double ulx = this.centerX - this.radius;
        double uly = this.centerY - this.radius;
        Ellipse2D.Double circle = new Ellipse2D.Double(ulx, uly, d, d);
        Rectangle2D.Double rect1 = new Rectangle2D.Double(ulx+0.1,uly+0.2,d,d);
        Shape shape1 = transform1.createTransformedShape(circle);
        
        g2D.setColor(this.getColor());
        
        g2D.fill(shape1);
        Shape shape2 = transform2.createTransformedShape(rect1);
        int r = 64 * (int) (1+Math.sin(this.centerX)) + rng.nextInt(64);
        int b = 64 * (int) (1+Math.cos(this.centerX)) + rng.nextInt(64);
        int green = 64 * (int) (1+Math.sin(this.centerY)) + rng.nextInt(64);
        Color p = new Color (r,green,b);
        this.setColor(p);
        g2D.setColor(this.getColor());
        g2D.fill(shape2);
        
        r = 64 * (int) (1+Math.sin(this.centerX)) + rng.nextInt(64);
        b = 64 * (int) (1+Math.cos(this.centerX)) + rng.nextInt(64);
        green = 64 * (int) (1+Math.sin(this.centerY)) + rng.nextInt(64);
        Color q = new Color (r,green,b);
        this.setColor(q);
    } // paintComponent( Graphics )

    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // You might also like to try what happens
        // in each step of the animation
        // Move? In which direction? How much?
        // Make bigger? Or make smaller?
        // Rotate? (There's an AffineTransform for that, too.)
        // Change color?

        if (this.centerY > 0.5) {
            this.deltaY = -this.deltaY;
        } // if
        else if (this.centerY < -0.5) {
            this.deltaY = -this.deltaY;
        } // else if
        
        if (this.centerX > 0.5) {
            this.deltaX = -this.deltaX;
        }
        else if (this.centerX < -0.5) {
            this.deltaX = -this.deltaX;
        }
        
        this.centerX += this.deltaX;
        this.centerY += this.deltaY;
        this.repaint();
    } // actionPerformed( ActionEvent )

} // SwingPanel
