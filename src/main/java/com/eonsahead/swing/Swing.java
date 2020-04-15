package swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This opens a window and displays a circle moving up and down in the window.
 * The user can change the color of the circle and the background.
 * @author pet00
 */

public class Swing extends JFrame implements ActionListener {

    //Initializing variables used throughout the class
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 600;
    private final String FRAME_TITLE = "Swing";
    private final int NUMBER_OF_COLORS = 10;
    private final String BG_COLOR = "Background Color";
    private final String FG_COLOR = "Foreground Color";

    private final List<Color> bgPalette = new ArrayList<>();
    private final List<Color> fgPalette = new ArrayList<>();
    private final SwingPanel panel;

    
    /*
    This constructor will construct the frame, generate random colors
    for the circle and the background, then assign those colors to values
    in the color menu. When I create this program myself, I will modify
    this code so that these colors are not random, but assigned. 
    */
    public Swing() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(FRAME_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = this.getContentPane();
        this.panel = new SwingPanel();
        pane.add(panel);

        Random rng = new Random();
        
        //Randomizing background colors
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            int red = 64 + rng.nextInt(128);
            int green = 64 + rng.nextInt(128);
            int blue = 64 + rng.nextInt(128);
            Color color = new Color(red, green, blue);
            bgPalette.add(color);
        } // for
        this.panel.setBackground(bgPalette.get(0));

        //Randomizing foreground colors
        for (int i = 0; i < NUMBER_OF_COLORS; i++) {
            int red = 32 + rng.nextInt(224);
            int green = 32 + rng.nextInt(224);
            int blue = 32 + rng.nextInt(224);
            Color color = new Color(red, green, blue);
            fgPalette.add(color);
        } // for
        this.panel.setColor(fgPalette.get(0));
        
        //Creating the bar with the drop-down menus
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        
        JMenu bgColorMenu = new JMenu(BG_COLOR);
        menuBar.add(bgColorMenu);

        
        //Initializing background color menu
        for (int i = 1; i < NUMBER_OF_COLORS+1; i++) {
            String label = BG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            bgColorMenu.add(item);
        } // for

        JMenu fgColorMenu = new JMenu(FG_COLOR);
        menuBar.add(fgColorMenu);

        //Initializing foreground color menu
        for (int i = 1; i < NUMBER_OF_COLORS+1; i++) {
            String label = FG_COLOR + " " + i;
            JMenuItem item = new JMenuItem(label);
            item.addActionListener(this);
            item.setActionCommand(label);
            fgColorMenu.add(item);
        } // for

        this.setVisible(true);
    } // Swing()

    
    //changes the color when you click on the button
    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if (actionCommand.indexOf(BG_COLOR) >= 0) {
            int i = BG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setBackground(bgPalette.get(index));
        } // if
        else if (actionCommand.indexOf(FG_COLOR) >= 0) {
            int i = FG_COLOR.length();
            String suffix = actionCommand.substring(i).trim();
            int index = Integer.parseInt(suffix);
            this.panel.setColor(fgPalette.get(index));
        } // if
    } // actionPerformed( ActionEvent )

    public static void main(String[] args) {
        Swing swing = new Swing();
    } // main( String [] )

} // Swing
