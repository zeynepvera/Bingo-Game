/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aa;

/**
 *
 * @author WINCHESTER
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

/*    --------------------------------------------
      |         GAME DEVELOPED BY "Klio" :3      |
      |       University of Trieste Student      |
      |               (First Year)               |
      --------------------------------------------
*/
class Surface extends JPanel implements MouseListener, MouseMotionListener, ActionListener{

    private boolean close;
    private boolean endGame;
    private int count;

    private int[] arrayPositions;

    private Card c1; //the 6 cards for the game
    private Card c2;
    private Card c3;
    private Card c4;
    private Card c5;
    private Card c6;

    private ArrayList<Card> cards;

    private String[] partialResults;
    private boolean[] tombolaCounter;

    private TombolaNumbers numbers;
    private int chosenNumber; //the extracted number
    private int indexEliminatedNumber; //its index
    private int[] achievedResults;
    private int[] updatedNumbers;
    private int[] oldNumbers;

    private final int DELAY = 200; //put lower number if you want more speed
    private Timer timer;

    private int k;

    // things for graphics
    private int xIN;
    private int yIN;

    private JFrame frame;

    private Rectangle2D rect1;
    private Rectangle2D rect2;

    private boolean clicked1;
    private boolean clicked2;
    private boolean change1;
    private boolean change2;

    private int x1, y1;
    private int width1, height1;

    private int x2, y2;
    private int width2, height2;

    public Surface(JFrame frame){
        this.frame = frame;
        setup();
        initTimer();
        addMouseListener(this); //in order not to add every time an lister
        addMouseMotionListener(this); //same here
    }

    public void setup(){
        close = false;
        count = 90;
        arrayPositions = new int[6];
        c1 = new Card();
        c2 = new Card();
        c3 = new Card();
        c4 = new Card();
        c5 = new Card();
        c6 = new Card();
        cards = new ArrayList<>(){{
            add(c1);
            add(c2);
            add(c3);
            add(c4);
            add(c5);
            add(c6);
        }};
        partialResults = new String[6];
        tombolaCounter = new boolean[6];
        numbers = new TombolaNumbers();
        achievedResults = new int[2];
        updatedNumbers = numbers.getNumeri();
        k = 0;
        x1 = 477;
        y1 = 20;
        width1 = 130;
        height1 = 40;
        x2 = x1 + width1 + 20;
        y2 = y1;
        width2 = width1;
        height2 = height1;
        clicked1 = false;
        clicked2 = false;
        change1 = false;
        change2 = false;
        rect1 = new Rectangle2D.Float(x1, y1, width1, height1);
        rect2 = new Rectangle2D.Float(x2, y2, width2, height2);
        this.setBackground(Color.black);
    }

    public void initTimer(){
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer(){
        return timer;
    }

    public void doDrawing(Graphics g) {
        g.create();

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        graphicsStuff(g2d); //drawing all the things
        checkButtons(g2d);

        g.dispose();
    }

    public void graphicsStuff(Graphics2D g2d){
        Stroke defaultStroke;
        defaultStroke = g2d.getStroke();

        int xT1 = 480, yT1 = 145;

        GradientPaint redtolightblue = new GradientPaint(100,0 , new Color(248, 0, 68),
                xT1 , yT1 + 30, new Color(0, 212, 238, 255));

        g2d.setPaint(redtolightblue);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 45));
        g2d.drawString("TOMBOLA GAME", 83,50);

        g2d.setFont(new Font("Consolas", Font.PLAIN, 20));

        g2d.setColor(Color.white);

        g2d.drawString("EXTRACTED NUMBER : ",xT1 + 20,yT1 - 50);

        g2d.setColor(new Color(17, 255, 0));

        g2d.drawString(chosenNumber + "", xT1 + 228, yT1 - 50);

        g2d.setColor(Color.white);

        g2d.drawString("NUMBERS LEFT : " + count, xT1 + 40, yT1 - 10);

        int grwidth1 = 100, grheight1 = 3;
        GradientPaint whitetoblack = new GradientPaint(xT1 + 130,yT1 - 30 , new Color(255, 255, 255),
                xT1 + 230, yT1 - 30, new Color(0, 0, 0));
        g2d.setPaint(whitetoblack);
        g2d.fillRect(xT1 + 130, yT1 - 35, grwidth1, grheight1);
        g2d.fillRect(xT1 + 130, yT1, grwidth1, grheight1);
        GradientPaint whitetoblackinverse = new GradientPaint(xT1 + 30,yT1 - 30 , new Color(0, 0, 0),
                xT1 + 130, yT1 - 30, new Color(255, 255, 255));
        g2d.setPaint(whitetoblackinverse);
        g2d.fillRect(xT1 + 30, yT1 - 35, grwidth1, grheight1);
        g2d.fillRect(xT1 + 30, yT1, grwidth1, grheight1);

        g2d.setColor(Color.white);

        int xT2 = xT1 + 16;

        for(int i = 0; i < updatedNumbers.length; i++){
            if(i % 8 == 0) {
                xT1 = xT2;
                yT1 += 30;
            }
            if (updatedNumbers[i]!= 0){
                g2d.drawString("" + updatedNumbers[i],xT1, yT1);
                xT1 += 30;
            }
        }
        int YINF = 130, XINF = 50;

        yIN = YINF;
        xIN = XINF;

        for(int i = 0; i < cards.size(); i++){

            if(i % 2 == 0 && i != 0) yIN += 180;
            if(i % 2 == 1) xIN += 210;
            else xIN = 50;
            int x = xIN;
            int y = yIN;

            if (partialResults[i] != null && partialResults[i].equals("TOMBOLA")) {
                GradientPaint magentatoyellow = new GradientPaint(x-30, y - 50 + 115 , new Color(255, 200, 0),
                        x+30, y - 5, new Color(255, 233, 0, 255));
                g2d.setPaint(magentatoyellow);
                g2d.setFont(new Font("Consolas", Font.PLAIN, 22));
                g2d.drawString(arrayPositions[i] + "° " + partialResults[i] + "", x + 17, y + 95);
            }

            GradientPaint greentoblue = new GradientPaint(x , y - 50 + 115 , new Color(97, 97, 97, 255),
                    x , y - 5, new Color(0, 0, 0));
            g2d.setPaint(greentoblue);
            g2d.fillRect(x - 16, y - 50, 180, 115);
            g2d.setColor(Color.black);
            g2d.fillRect(x - 12, y - 54, 172, 115);

            g2d.setColor(Color.white);
            g2d.setFont(new Font("Consolas", Font.PLAIN, 18));
            g2d.drawString("CARD " + (i + 1), x + 48, y - 34);

            float[] dash = { 3f, 0f, 3f };
            BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);

            g2d.drawLine(x + 29, y - 28, x + 123, y - 28);
            g2d.drawLine(x + 29, y - 52, x + 123, y - 52);

            g2d.drawLine(x + 29, y - 28, x + 19, y - 40);
            g2d.drawLine(x + 29, y - 52, x + 19, y - 40);
            g2d.drawLine(x + 123, y - 28, x + 133, y - 40);
            g2d.drawLine(x + 123, y - 52, x + 133, y - 40);

            g2d.setStroke(bs);
            g2d.drawLine(x + 19, y - 40, x-10, y - 40);
            g2d.drawLine(x + 133, y - 40, x + 160, y - 40);
            g2d.setStroke(defaultStroke);

            g2d.setFont(new Font("Consolas", Font.PLAIN, 22));
            for (int j = 0; j < cards.get(i).getTable().length; j++) {
                x = xIN;
                for (int k = 0; k < cards.get(i).getTable()[j].length; k++) {
                    if(cards.get(i).getSignsTable()[j][k] == true){
                        g2d.setColor(new Color(0, 173, 253));
                    } else {
                        g2d.setColor(new Color(255, 0, 0));
                    }
                    g2d.drawString(cards.get(i).getTable()[j][k] + "", x, y);
                    x += 32;
                }
                y += 25;
            }
        }
    }

    public void checkButtons(Graphics2D g2d){
        g2d.setFont(new Font("Consolas", Font.BOLD, 20));
        if(change1) {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(x1, y1, width1, height1);
            g2d.setColor(Color.BLACK);
            g2d.drawString("EXIT", x1 + width1/3 + 3, y1 + height1/2 + 6);
        } else{
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x1, y1, width1, height1);
            g2d.setColor(Color.WHITE);
            g2d.drawString("EXIT", x1 + width1/3 + 3, y1 + height1/2 + 6);
        }

        if(change2) {
            g2d.setColor(Color.WHITE);
            g2d.fillRect(x2, y2, width2, height2);
            g2d.setColor(Color.BLACK);
            g2d.drawString("RESTART", x2 + width2/4 - 2, y2 + height2/2 + 6);
        } else{
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x2, y2, width2, height2);
            g2d.setColor(Color.WHITE);
            g2d.drawString("RESTART", x2 + width1/4 - 2, y2 + height2/2 + 6);
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }

    public void function(){ //core
        achievedResults = numbers.numberExtraction(updatedNumbers);
        chosenNumber = achievedResults[0];
        indexEliminatedNumber = achievedResults[1];
        for(int i = 0; i < cards.size(); i++){
            partialResults[i] = cards.get(i).check(chosenNumber);
            if(partialResults[i].equals("TOMBOLA") && tombolaCounter[i] != true) {
                tombolaCounter[i] = true;
                arrayPositions[i] = k + 1;
                k++;
            }
        }
        oldNumbers = updatedNumbers;
        updatedNumbers = update(oldNumbers,indexEliminatedNumber); //update of the numbers for the next extraction

        endGame = true; // every card is completed
        for(int i = 0; i < tombolaCounter.length && endGame; i++){
            endGame = tombolaCounter[i] == true;
        }
        if(endGame) {
            close = true;
        }
    }

    public static int[] update(int[] oldArray, int index){
        int[] newArray = new int[oldArray.length-1];
        for(int i = 0, k = 0; i < oldArray.length; i++){
            if (i == index){
                continue;
            }
            newArray[k++] = oldArray[i];
        }
        return newArray;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (!close) { //if all cards are completed the game is finished...
            function();
            if(count > 0) count--;
        }
        repaint();

        // ... but you can still press buttons :)
        if(clicked1){ //the EXIT button
            timer.stop();
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //X with custom button
        }

        if(clicked2){ //the RESTART button
            setup();
            clicked2 = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (rect1.contains(x, y)) {
            clicked1 = true;
        } else clicked1 = false;

        if (rect2.contains(x, y)) {
            clicked2 = true;
        } else clicked2 = false;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (rect1.contains(x, y)) {
            change1 = true;
        } else change1 = false;

        if (rect2.contains(x, y)) {
            change2 = true;
        } else change2 = false;

        repaint();
    }

    /////////////////////////////////////////////////////
    @Override public void mousePressed(MouseEvent e) { }
    @Override public void mouseReleased(MouseEvent e) { }
    @Override public void mouseEntered(MouseEvent e) { }
    @Override public void mouseExited(MouseEvent e) { }
    @Override public void mouseDragged(MouseEvent e) { }
    ////////////////////////////////////////////////////

}

public class Tombola extends JFrame{

    public Tombola(){
        initUI();
    }

    public void initUI(){
        Surface surface = new Surface(this);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        //setting the window
        setTitle("Tombola Game");
        setSize(800,650);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tombola gioco = new Tombola();
                gioco.setVisible(true);
            }
        });
    }
}
