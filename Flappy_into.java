import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.io.File;
import java.util.*;


import javax.imageio.ImageIO;

public class Flappy_into  implements ActionListener, MouseListener, KeyListener {
    public static Flappy_into flappy_birdy;
    JFrame jFrame;

    public int WIDTH = 600, HEIGHT = 600;

    public ArrayList<Rectangle> columns;

    public Rectangle bird;

    public Random random;

    public int ticks, ymotion;
    public int score,highScore;

    public boolean started, gameover = false;
    ImagePanel img_panel;
    String coe;
    BufferedImage backgr;
    BufferedImage frontgr;
    BufferedImage birdy;
    BufferedImage column_pic;

    Flappy_into() {
        jFrame = new JFrame();

        Timer timer = new Timer(20, this);

        random = new Random();

        try{
            /*
            jFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read
            (new File("/home/giks/Desktop/Java_pr/flappy_game_java/images/bg.png")))));
            */
            backgr = ImageIO.read(new 
            File("/home/giks/Desktop/Java_pr/flappy_game_java/images/bg.png"));
            frontgr = ImageIO.read(new 
            File("/home/giks/Desktop/Java_pr/flappy_game_java/images/fg.png"));
            birdy = ImageIO.read(new 
            File("/home/giks/Desktop/Java_pr/flappy_game_java/images/bird.png"));
            column_pic = ImageIO.read(new 
            File("/home/giks/Desktop/Java_pr/flappy_game_java/images/pipeSouth.png"));
            
        } catch(Exception e){e.printStackTrace();}
               
        //jFrame.setSize(backgr.getWidth(), backgr.getHeight());
        jFrame.setSize(WIDTH, HEIGHT);

        img_panel = new ImagePanel(backgr, jFrame.getWidth(), jFrame.getHeight());

        //img_panel.setLayout(new FlowLayout());

        jFrame.add(img_panel);//, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
        jFrame.setTitle("FLAPPY BIRD");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        
        timer.start();
 
        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, birdy.getWidth(), birdy.getHeight());
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        
    }

    public void addColumn(boolean start) {

        int space = 240;
        int width = 80;
        int height = 50 + random.nextInt(240);

        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 250,
             HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size()-1) * 250,
             0, width, HEIGHT - space - height));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 500, 
            HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 
            0, width, HEIGHT - space - height));
        }
    }

    public void paintColumn(Graphics g, Rectangle rectangle) {
        g.drawImage((Image)column_pic ,rectangle.x, rectangle.y, 
        rectangle.width, rectangle.height, jFrame);
    }

    public void repaint(Graphics g) {

       //g.drawImage((Image)backgr, 0, HEIGHT, WIDTH, 0, jFrame);
       // g.fillRect(0, 0, WIDTH, HEIGHT);

        g.drawImage((Image)frontgr,0, HEIGHT - 120, WIDTH, 120 , jFrame);
        //g.fillRect(0, HEIGHT - 120, WIDTH, 120);

        //g.setColor(Color.GREEN);
        //g.fillRect(0, HEIGHT - 120, WIDTH, 20);

        g.drawImage((Image)birdy, bird.x, bird.y, jFrame);
        //g.setColor(Color.RED);
        //g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {

            paintColumn(g, column);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",1,60));
        if(!started && !gameover){
            g.drawString("CLICK TO START", 10, HEIGHT/2-10);
        }
        if(gameover==true){
            g.drawString("GAME OVER!!",45, HEIGHT/2-10);
        }
        if(started && !gameover){
            g.drawString(String.valueOf(score), WIDTH/2, 50);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",6,40));
        if(!gameover && !started){

            g.drawString("HighScore: "+String.valueOf(highScore), WIDTH/2, 50);
        }
    }

    public void jump() {
        if (!started) {
            started = true;
        }
        if (!gameover) {
            if(ymotion>0){
            ymotion = 0;
            }
        }
        ymotion -= 7;
        
        if(gameover){  
            started=false;

            gameover=false;
            score=0;
            ymotion=0;
 
           

            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, birdy.getWidth(), birdy.getHeight());
            columns.clear();

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);
        }
    }
    
    public void load_images() {
        //JFrame frame = new JFrame();
        //JLabel label=new JLabel();

        try{
            /*
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read
            (new File("/home/giks/Desktop/Java_pr/flappy_game_java/images/bg.png")))));
            */
            backgr = ImageIO.read(new 
            File("/home/giks/Desktop/Java_pr/flappy_game_java/images/bg.png"));
            
        } catch(Exception e){e.printStackTrace();}
        
        //label.setIcon(new ImageIcon(backgr));
        //frame.setSize(backgr.getWidth(), backgr.getHeight());
        //frame.getContentPane().add(label,BorderLayout.CENTER);
        /*
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setTitle("FLAPPY BIRD");   
        frame.setVisible(true);
        */
    }

       
    @Override
    public void mouseClicked(MouseEvent e) {
        flappy_birdy.jump();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
        flappy_birdy.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        int speed = 3;

        ticks++;
        if(started) {    
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column1;
                column1 = columns.get(i);
                column1.x -= speed;
            }
            //gravity
            if (ticks % 2 == 0 && ymotion < 9) {
                ymotion += 1.5;
            }
    
            bird.y += ymotion;

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column2;
                column2 = columns.get(i);
                if (column2.y == 0) {
                    if (column2.x + column2.width <= 0) {
                        columns.remove(i);
                        addColumn(false);
                    }
                }
                if (column2.y != 0) {
                    if (column2.x + column2.width < 0 && columns.size()>10) {
                        columns.remove(i);
                        addColumn(false);
                    }
                }

                int ground = HEIGHT - 120;
                if (bird.y + bird.height + ymotion >= ground) {
                    bird.y = ground - bird.height;
                } else if (bird.y <= 0) {
                    gameover = true;
                
                }

            }
        
            for (Rectangle columns3 : columns) {
                if(columns3.intersects(bird)){
                    gameover=true;
                    if(bird.x<= columns3.x)
                    {
                        bird.x=columns3.x-bird.width;
                    }else {

                        if(columns3.y!=0){
                            bird.y=columns3.y-bird.height;
                        }
                        else if(bird.y<columns3.height){
                        bird.y=columns3.height; 
                        }   
                    }
                }
                if(columns3.y==0 && bird.x+bird.width/2>columns3.x+columns3.width/2-2 
                && bird.x+bird.width/2<columns3.x+columns3.width/2+2){
                    score++;
                }            
            }
        }
        if(gameover && score>highScore)
        {
            highScore=score;
        }else{
            
        }
        //System.out.println(columns.size());

        img_panel.repaint();
    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

        public static void main(String[] args) { 
        flappy_birdy = new Flappy_into();

        /*
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
        */    
    }
}