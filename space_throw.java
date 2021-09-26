import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.*;
import java.awt.image.BufferedImage;
public class space_throw extends JFrame implements KeyListener, ActionListener {//opens program
	public static Image image_rat1;
	public static Image image_whale1;
	public static Image image_giraffe1;
	public static Image image_walrus1;
	public static Image image_ant1;
	public static Image image_human1;
	public static Image image_bg1;
	public static String choice;
	public static boolean rattrue=false;
	public static boolean whaletrue=false;
	public static boolean giraffetrue=false;
	public static boolean walrustrue=false;
	public static boolean anttrue=false;
	public static boolean humantrue=false;
	
	public static Dimension size
    = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = (int)size.getWidth();
	public static int height = (int)size.getHeight();
    public test (){//constructor for JPanel
    	String input = choices();
    	choice=input;
        add(new JP());
    }//close Jpanel Constructor

    public static void main(String[] args) throws IOException{
        test w=new test();
        w.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setVisible(true);
        w.addKeyListener(w);//allows for ket pressing to move objects
        Color space = new Color (65,74,76);
        URL url_rat = new URL("http://pngimg.com/uploads/rat_mouse/rat_mouse_PNG23553.png");
        URL url_whale = new URL("https://www.kindpng.com/picc/m/113-1134562_blue-whale-png-transparent-png.png");
        URL url_giraffe = new URL("https://toppng.com/uploads/preview/giraffe-11523219074adoaiojycv.png");
        URL url_walrus = new URL("https://pngimg.com/uploads/walrus/walrus_PNG3.png");
        URL url_ant = new URL("https://e7.pngegg.com/pngimages/70/410/png-clipart-ants-ants.png");
        URL url_human = new URL("https://cdn.imgbin.com/11/6/24/imgbin-stick-man-stick-figure-happy-stick-man-eADGmejyPxTZfNqVuhHxGti3k.jpg");
        URL url_bg = new URL("https://images.immediate.co.uk/production/volatile/sites/7/2018/02/Earth-from-space-1-64e9a7c.jpg?quality=90&resize=620,413");
        Image image_rat = ImageIO.read(url_rat);
        Image image_whale = ImageIO.read(url_whale);
        Image image_giraffe = ImageIO.read(url_giraffe);
        Image image_walrus = ImageIO.read(url_walrus);
        Image image_ant = ImageIO.read(url_ant);
        Image image_human = ImageIO.read(url_human);
        Image image_bg = ImageIO.read(url_bg);
        BufferedImage buffered_rat = toBufferedImage(image_rat);
        BufferedImage buffered_whale = toBufferedImage(image_whale);
        BufferedImage buffered_giraffe = toBufferedImage(image_giraffe);
        BufferedImage buffered_walrus = toBufferedImage(image_walrus);
        BufferedImage buffered_ant = toBufferedImage(image_ant);
        BufferedImage buffered_human = toBufferedImage(image_human);
        BufferedImage buffered_bg = toBufferedImage(image_bg);
        image_rat1 = scaleImage(buffered_rat,300,300,space);
        image_whale1 = scaleImage(buffered_whale,300,300,space);
        image_giraffe1 = scaleImage(buffered_giraffe,300,300,space);
        image_walrus1 = scaleImage(buffered_walrus,300,300,space);
        image_ant1 = scaleImage(buffered_ant,300,300,space);
        image_human1 = scaleImage(buffered_human,300,300,space);
        image_bg1 = scaleImage(buffered_bg,1300,1200,space);
        //String input = choices();
        HashMap<String, Double> map = new HashMap<>();
        map.put("Rat",0.31978262);
        map.put("Whale",136077.711);
        map.put("Giraffe",861.8255);
        map.put("Walrus",997.9032 );
        map.put("Ant", 0.0000025);
        map.put("Human", 62.0);
        
        double forceNeeded = 0;
        if(choice.equals("Rat")){
            forceNeeded = force(map.get("Rat"));
        }
        else if(choice.equals("Whale")){
            forceNeeded = force(map.get("Whale"));
        }
         else if(choice.equals("Giraffe")){
            forceNeeded = force(map.get("Giraffe"));
        }
         else if(choice.equals("Walrus")){
            forceNeeded = force(map.get("Walrus"));
        }
         else if(choice.equals("Ant")){
            forceNeeded = force(map.get("Ant"));
        }
         else if(choice.equals("Human")){
            forceNeeded = force(map.get("Human"));
        }
         else{
             while (Character.isAlphabetic(choice.charAt(0))){
                 System.out.println("It seems you either didnt choose any of the above, or made a typo. Either restart the program or enter a custom weight (in lb).");
                 Scanner scan = new Scanner(System.in);
                 choice = scan.nextLine();
             }
             forceNeeded = force((Integer.parseInt(choice))/2.205);
         }
        choice=choice;
        System.out.println(String.format("You'd need %04.2f N of force to throw that into space.", forceNeeded));
        System.out.println("To put that into perspective the average human can exert 450N of force.");
        System.out.println(String.format("You'd need %04.2f humans working together to throw that into space.", (forceNeeded/450)));
    }
    public static String choices(){
        System.out.println("What object would throw into space? Here are our options. We'll tell you how hard you have to throw it (ignoring air resistance)."
                + "\nRat\nWhale\nGiraffe\nWalrus\nAnt\nHuman\nCustom (input a random weight (it'll be in lb))");
        System.out.println("Please input either Rat, Whale, Giraffe, Walrus, Ant, Human, or a random number. (Case sensitive)");
        System.out.println("Putting in anything else will break the program. Please dont do that. Thanks.");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    public static double force(double mass) {
        double amnt = 0.0;
        double g = 9.8;//Earth's acceleration due to gravity
        double atm = 10000000.0; //Length of Earth's atmosphere
        double Fg = mass*g;//Force due to gravity
        double pot = Fg*atm-2;
        amnt = pot/2;

        return amnt;
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    public static BufferedImage scaleImage(BufferedImage img, int width, int height,
            Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) {
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
           // g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    public class JP extends JPanel{//start JPanel CLass

        public JP(){
            Container c = getContentPane();
            setOpaque(false);//allows for setting a color background in JPanel
            Color space = new Color (65,74,76);
            c.setBackground(space);
        }//end JP

        public void paint(Graphics g) {//opens paint method
            super.paint(g);//allows for painting and
            g.drawImage(image_bg1,0,0,null);
            if(choice.equals("Rat"))
            	rattrue=true;
            if(choice.equals("Whale"))
            	whaletrue=true;
            if(choice.equals("Giraffe"))
            	giraffetrue=true;
            if(choice.equals("Walrus"))
            	walrustrue=true;
            if(choice.equals("Ant"))
            	anttrue=true;
            if(choice.equals("Human"))
            	humantrue=true;
            if(rattrue) {
            	g.drawImage(image_rat1,900,50,null);
            }
            if(whaletrue) {
            	g.drawImage(image_whale1,900,50,null);
            }
            if(giraffetrue) {
            	g.drawImage(image_giraffe1,900,50,null);
            }
            if(walrustrue) {
            	g.drawImage(image_walrus1,900,50,null);
            }
            if(anttrue) {
            	g.drawImage(image_ant1,900,50,null);
            }
            if(humantrue) {
            	g.drawImage(image_human1,900,50,null);
            }
            
            repaint();
        }//close paint method

    }//close JPanel Class
    public void actionPerformed(ActionEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    }
}