import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 500;
    private final int B_HEIGHT = 550;
    private final int DOT_SIZE = 50;
    private final int RAND_POS = 10;
    private final int DELAY = 50;

    private int treeTrunkSpeed;
    private int pos_x;
    private int pos_y;
    private int obj_posX;
    //    private int trackCounter;
    private int coinCounter;
    private int insectCounter;
    private int bushesCounter;
    private int carCounter;
    private int treeTrunkCounter;
    private ArrayList<FixedGameElement> fixedGameElementList;
    private ArrayList<MovingObject> movingObjectList;
    private ArrayList<Track> trackList;
    private HashMap<String, ImageIcon> fixedGameElementImageMap;
    private HashMap<String, ImageIcon> movingObjectImageMap;

    private String panelColor[] = {"Red", "Violet", "Orange", "Blue"};

    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
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
        fixedGameElementImageMap.put("coin", iic);

        ImageIcon iii = new ImageIcon(Insect.getPathToImage());
        fixedGameElementImageMap.put("insect", iii);

        ImageIcon iib = new ImageIcon(Bushes.getPathToImage());
        fixedGameElementImageMap.put("bushes", iib);

        ImageIcon iih = new ImageIcon("./assets/head.png");
        head = iih.getImage();

    }

    private void initGame() {

        score = 0;

        treeTrunkSpeed = 2;

        pos_x = B_WIDTH / 2;
        pos_y = B_HEIGHT - 25;
        obj_posX = getRandomCoordinate();

//        coinCounter = 3;
//        insectCounter = 2;

        treeTrunkCounter = 0;
//        trackCounter = 10;
        carCounter = 1;
        bushesCounter = 0;

        fixedGameElementList = new ArrayList<FixedGameElement>();
        movingObjectList = new ArrayList<MovingObject>();
        trackList = new ArrayList<Track>();

        for (int i = 0; i < coinCounter; i++) {
            fixedGameElementList.add(new Coin(getRandomCoordinate(), getRandomCoordinate()));
        }

        for (int i = 0; i < insectCounter; i++) {
            fixedGameElementList.add(new Insect(getRandomCoordinate(), getRandomCoordinate()));
        }

        for (int i = 0; i < bushesCounter; i++) {
            fixedGameElementList.add(new Bushes(getRandomCoordinate(), 0));
        }

        for (int i = 0; i < treeTrunkCounter; i++) {
            movingObjectList.add(new TreeTrunk(getRandomCoordinate(), 210, treeTrunkSpeed));
        }

        Track track = new CentralBerm(3, fixedGameElementList, 25);
        trackList.add(track);
        track = new HighWay("left", carCounter / 12, getList(), 55);
        trackList.add(track);
        track = new HighWay("right", carCounter / 12, getList(), 110);
        trackList.add(track);
        track = new River("left", treeTrunkCounter, getTrunkList(), 160);
        trackList.add(track);
        track = new River("right", treeTrunkCounter, getTrunkList(), 210);
        trackList.add(track);
        track = new CentralBerm(3, fixedGameElementList, 235);
        trackList.add(track);
        track = new HighWay("left", carCounter / 12, getList(), 300);
        trackList.add(track);
        track = new HighWay("right", carCounter / 12, getList(), 355);
        trackList.add(track);
        track = new HighWay("left", carCounter / 12, getList(), 405);
        trackList.add(track);
        track = new HighWay("right", carCounter / 12, getList(), 455);
        trackList.add(track);
        track = new CentralBerm(3, fixedGameElementList, 500);
        trackList.add(track);

        //Time settings
        timer = new
                Timer(DELAY, this);
        timer.start();
    }

    private ArrayList getList() {
        var voitureList = new ArrayList<MovingObject>();
        for (int i = 0; i < carCounter; i++) {
            voitureList.add(new Car((int) (Math.random() * 500), 50000, (int) (Math.random() * (5 - 1) + 1), panelColor[i % 4]));
        }
        return voitureList;
    }

    private ArrayList getTrunkList() {
        var trunkList = new ArrayList<MovingObject>();
        for (int i = 0; i < treeTrunkCounter; i++) {
            trunkList.add(new TreeTrunk((int) (Math.random() * 500), 50000, (int) (Math.random() * (5 - 1) + 1)));
        }
        return trunkList;
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
                if (trackObject.getClass() == HighWay.class || trackObject.getClass() == River.class) {
                    for (MovingObject mvObj : trackObject.getTrackContent()) {
                        g.drawImage(mvObj.getIconImage(), mvObj.getPos_x(), trackObject.getTrackPositionY(), this);
                    }
                } else if (trackObject.getClass() == CentralBerm.class) {
                    for (FixedGameElement elem : ((CentralBerm) trackObject).getFixedElementList()) {
                        g.drawImage(fixedGameElementImageMap.get(elem.getType()).getImage(), elem.getPosX(), trackObject.getTrackPositionY(), this);
                    }
                }
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

            }
        }

        for (Track trackObject : trackList) {
            if (trackObject.getClass() == HighWay.class) {
                for (MovingObject mvObj : ((HighWay) trackObject).getTrackContent()) {
                    if ((pos_x >= mvObj.getPos_x() && pos_x <= mvObj.getPos_x() + 90) && (pos_y >= mvObj.getPos_y() && pos_y <= mvObj.getPos_y()+60))
                        System.out.println("touched");
                }
            }
        }


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
        //Track speed
        for (Track trackObject : trackList) {
            if (trackObject.getClass() == River.class || trackObject.getClass() == HighWay.class) {
                for (MovingObject mvObj : trackObject.getTrackContent()) {
                    if (trackObject.getDirection() == "right")
                        mvObj.setPos_x(mvObj.getPos_x() - mvObj.getSpeed());
                    else mvObj.setPos_x(mvObj.getPos_x() + mvObj.getSpeed());
                }
            }
        }
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

        int rd = (int) (Math.random() * 4);

        for (Track trackObject : trackList) {
            if (trackObject.getClass() == HighWay.class || trackObject.getClass() == River.class) {
                for (MovingObject mvObj : trackObject.getTrackContent()) {
                    if (mvObj.getPos_x() <= 0) mvObj.setPos_x(B_WIDTH);
                    else if (mvObj.getPos_x() >= B_WIDTH) mvObj.setPos_x(0);
                    if (mvObj.getClass() == Car.class && (mvObj.getPos_x() <= 0 || mvObj.getPos_x() >= B_WIDTH)) {
                        ((Car) mvObj).setColor(panelColor[rd]);
                    }
                }
            }
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
