import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 550;
    private final int DOT_SIZE = 50;
    private final int RAND_POS = 10;
    private final int DELAY = 140;

    private int treeTrunkSpeed;
    private int pos_x;
    private int pos_y;
    private int obj_posX;
    private int trackCounter;
    private int coinCounter;
    private int insectCounter;
    private int treeTrunkCounter;
    private ArrayList<FixedGameElement> fixedGameElementList;
    private ArrayList<MovingObject> movingObjectList;
    private ArrayList<Track> trackList;
    private HashMap<String, ImageIcon> fixedGameElementImageMap;
    private HashMap<String, ImageIcon> movingObjectImageMap;

    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    //    private Image ball;
//    private Image coin;
    private Image head;
    private Image backgroundImage;

    private int score;
    private int void_x = -1 * B_WIDTH;
    private int void_y = -1 * B_HEIGHT;

    public Board() throws IOException {

        backgroundImage = ImageIO.read(new File("./assets/backgroundWithRiver.jpg"));
        initBoard();

    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {

        fixedGameElementImageMap = new HashMap<String, ImageIcon>();
        movingObjectImageMap = new HashMap<String, ImageIcon>();

        ImageIcon iic = new ImageIcon(Coin.getPathToImage());
        //coinImage = iic.getImage();
        fixedGameElementImageMap.put("coin", iic);

        ImageIcon iii = new ImageIcon(Insect.getPathToImage());
        //insectImage = iii.getImage();
        fixedGameElementImageMap.put("insect", iii);

        ImageIcon iih = new ImageIcon("./assets/head.png");
        head = iih.getImage();

        ImageIcon iitt = new ImageIcon(TreeTrunk.getPathToImage());
        movingObjectImageMap.put("treeTrunk", iitt);
    }

    private void initGame() {

        score = 0;

        treeTrunkSpeed = 2;

        pos_x = B_WIDTH / 2;
        pos_y = B_HEIGHT / 2;
        obj_posX = getRandomCoordinate();

//        coinCounter = 3;
//        insectCounter = 2;

        treeTrunkCounter = 2;
        trackCounter = 10;

        fixedGameElementList = new ArrayList<FixedGameElement>();
        movingObjectList = new ArrayList<MovingObject>();
        trackList = new ArrayList<Track>();

        for (int i = 0; i < coinCounter; i++) {
            fixedGameElementList.add(new Coin(getRandomCoordinate(), getRandomCoordinate()));
        }

        for (int i = 0; i < insectCounter; i++) {
            fixedGameElementList.add(new Insect(getRandomCoordinate(), getRandomCoordinate()));
        }

        for (int i = 0; i < treeTrunkCounter; i++) {
            movingObjectList.add(new TreeTrunk(getRandomCoordinate(), 210, treeTrunkSpeed));
        }

//        var arrayPosition = new int[50,100, 150, 200, 250, 300,  ];
        //GAME
//        for (int i = 0; i < 55; i+) {
//            String direction = i%2==0?"right":"left";

        Track track = new CentralBerm(4, fixedGameElementList, 0 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2,  movingObjectList, 50 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2, movingObjectList, 100 + 10);
        trackList.add(track);
        track = new River("left", treeTrunkCounter / 2, movingObjectList, 150 + 10);
        trackList.add(track);
        track = new River("right", treeTrunkCounter / 2, movingObjectList, 200 + 10);
        trackList.add(track);
        track = new CentralBerm(4, fixedGameElementList, 250 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2, movingObjectList, 300 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2, movingObjectList, 350 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2, movingObjectList, 400 + 10);
        trackList.add(track);
        track = new HighWay("left", treeTrunkCounter / 2, movingObjectList, 450 + 10);
        trackList.add(track);
        track = new CentralBerm(4, fixedGameElementList, 500 + 10);
        trackList.add(track);

//            track = new HighWay(direction, 3, movingObjectList);
//            track = new CentralBerm(2, fixedGameElementList);
//


        timer = new

                Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    public Board(String fileName) throws IOException {
    }

    private void doDrawing(Graphics g) {

        g.drawImage(backgroundImage, 0, 0, this);

        if (inGame) {

            for (FixedGameElement elem : fixedGameElementList) {
                g.drawImage(fixedGameElementImageMap.get(elem.getType()).getImage(), elem.getPosX(), elem.getPosY(), this);
            }

            for (Track trackObject : trackList) {
                if (trackObject.getClass() == River.class) {
                    for (MovingObject mvObj : ((River) trackObject).getMovingObjectsList()) {
                        g.drawImage(movingObjectImageMap.get(mvObj.getType()).getImage(), mvObj.getPos_x(), trackObject.getTrackPositionY(), this);
                    }
                }
//                g.drawImage(movingObjectImageMap.get(mvObj.getType()).getImage(), mvObj.getPos_x(), mvObj.getPos_y(), this);
            }

            g.drawImage(head, pos_x, pos_y, this);

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    private void checkFixedGameElementCollision() {

        for (FixedGameElement elem : fixedGameElementList) {
            if ((pos_x == elem.getPosX()) && (pos_y == elem.getPosY())) {
                elem.setPosX(void_x);
                elem.setPosY(void_y);

                elem.triggerAction(this);

                System.out.println(coinCounter);
                System.out.println(score);
            }
        }

//        for (MovingObject mvObj : movingObjectList) {
//            if ((pos_x == mvObj.getPos_x()) && (pos_y == mvObj.getPos_y())) {
//                mvObj.setPos_x(void_x);
//                mvObj.setPos_y(void_y);
//            }
//        }

    }

    public void incScore(int valueToIncrease) {
        score += valueToIncrease;
    }

    public void decreaseCoinAmount() {
        coinCounter -= 1;
    }

    private void move() {

        if (leftDirection) {
            pos_x -= DOT_SIZE;
        }

        if (rightDirection) {
            pos_x += DOT_SIZE;
        }

        if (upDirection) {
            pos_y -= DOT_SIZE;
        }

        if (downDirection) {
            pos_y += DOT_SIZE;
        }
    }

    private void moveImage() {

        //tree speed

        for (Track trackObject : trackList) {
            if (trackObject.getClass() == River.class) {

                for (MovingObject mvObj : ((River) trackObject).getMovingObjectsList()) {
                    System.out.println(trackObject);
                    System.out.println(((River) trackObject).getMovingObjectsList());
                    if (trackObject.getDirection() == "right")
                        mvObj.setPos_x(mvObj.getPos_x() - treeTrunkSpeed);
//                    else mvObj.setPos_x(mvObj.getPos_x() + treeTrunkSpeed);
                }


            }
//                g.drawImage(movingObjectImageMap.get(mvObj.getType()).getImage(), mvObj.getPos_x(), mvObj.getPos_y(), this);
        }

        //car  speed
//        for(MovingObject mvObj: movingObjectList){
//
//                mvObj.setPos_x(mvObj.getPos_x()+treeTrunkSpeed);
//            }

    }

    private void checkCollision() {

        if (pos_y >= B_HEIGHT) {
            inGame = false;
        }

        if (pos_y < 0) {
            inGame = false;
        }

        if (pos_x >= B_WIDTH) {
            inGame = false;
        }

        if (pos_x < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private int getRandomCoordinate() {

        int r = (int) (Math.random() * RAND_POS);
        return ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkFixedGameElementCollision();
            checkCollision();
            moveImage();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
            move();
        }
    }

}
