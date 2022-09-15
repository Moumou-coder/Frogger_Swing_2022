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
import java.util.List;

public class Board extends JPanel implements ActionListener {

    static final int B_WIDTH = 500;
    static final int B_HEIGHT = 550;
    static final int DOT_SIZE = 50;
    private final int DELAY = 50;
    private Frog frog;
    private Coin coin;
    private Insect insect;
    private int fixedElemCounter;
    private int coinCounter;
    private int insectCounter;
    private int bushesCounter;
    private int carCounter;
    private int treeTrunkCounter;
    private int speed;
    private List<Track> trackList;
    private List<GameElement> fixedElementList;
    private String[] trackArray = {"Berm", "HighWay", "HighWay", "River", "River", "centralBerm", "HighWay", "HighWay", "HighWay", "HighWay", "Berm"};

    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private int count;
    private Image backgroundImage;

    private int score;
    private int void_x = -1 * B_WIDTH;
    private int void_y = -1 * B_HEIGHT;


    public Board() throws IOException {

//        backgroundImage = ImageIO.read(new File("./assets/backgroundWithoutRiver.jpg"));
        backgroundImage = ImageIO.read(new File("./assets/backgroundWithRiver.jpg"));
        initBoard();

    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }

    private void initGame() {

        score = 0;

        fixedElemCounter = 3;

        treeTrunkCounter = 1;
        carCounter = 1;
        bushesCounter = 1;

        trackList = new ArrayList<>();
        frog = new Frog();

        fixedElementList = new ArrayList<>();

        for (int i = 0; i < fixedElemCounter; i++) {
            coin = new Coin();
            fixedElementList.add(coin);

            insect = new Insect();
            fixedElementList.add(insect);
        }


        for (int i = 1; i < trackArray.length -1; i++) {
            Track track;

            if(trackArray[i] == "centralBerm") track = new Berm(bushesCounter, 50 * i);
            else if (trackArray[i] == "HighWay") track = new HighWay("right", carCounter, 50 * i);
            else track = new River("left", treeTrunkCounter, 50 * i);

            trackList.add(track);
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.drawImage(backgroundImage, 0, 0, this);

        if (inGame) {

            trackList.forEach(t -> t.getTrackContent().forEach(e -> g.drawImage(e.getIconImage(), e.getPos_x(), e.getPos_y(), this)));
            g.drawImage(frog.getIconImage(), frog.getPos_x(), frog.getPos_y(), this);
            fixedElementList.forEach(f -> g.drawImage(f.getIconImage(), f.getPos_x(), f.getPos_y(), this));
            Toolkit.getDefaultToolkit().sync();

        } else gameOver(g);

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

        for (Track trackObject : trackList) {
            if (trackObject.getClass() == HighWay.class) {
                for (GameElement elem : trackObject.getTrackContent()) {
                    if ((frog.getPos_x() >= elem.getPos_x() && frog.getPos_x() <= elem.getPos_x() + 90) && (frog.getPos_y() >= elem.getPos_y() && frog.getPos_y() <= elem.getPos_y() + 60)) {
//                        inGame = false;
                    }
                }
            }

            if (trackObject.getClass() == River.class) {
                for (GameElement elem : trackObject.getTrackContent()) {
                    if ((frog.getPos_x() <= elem.getPos_x() || frog.getPos_x() >= elem.getPos_x() + 100) && (frog.getPos_y() >= elem.getPos_y() && frog.getPos_y() <= elem.getPos_y() + 60)) {
//                        inGame = false;
                    }
                }
            }

            if (trackObject.getClass() == Berm.class) {
                for (GameElement elem : trackObject.getTrackContent()) {
                    if ((frog.getPos_x() >= elem.getPos_x() && frog.getPos_x() <= elem.getPos_x() + 50) && (frog.getPos_y() >= elem.getPos_y() && frog.getPos_y() <= elem.getPos_y() + 60)) {
                        if (leftDirection) frog.setPos_x(frog.getPos_x() + DOT_SIZE);
                        if (rightDirection) frog.setPos_x(frog.getPos_x() - DOT_SIZE);
                        if (upDirection) frog.setPos_y(frog.getPos_y() + DOT_SIZE);
                        if (downDirection) frog.setPos_y(frog.getPos_y() - DOT_SIZE);
                    }
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
            frog.setPos_x(frog.getPos_x() - DOT_SIZE);
        }

        if (rightDirection) {
            frog.setPos_x(frog.getPos_x() + DOT_SIZE);
        }

        if (upDirection) {
            frog.setPos_y(frog.getPos_y() - DOT_SIZE);
        }

        if (downDirection) {
            frog.setPos_y(frog.getPos_y() + DOT_SIZE);
        }
    }

    private void moveImage() {
        //Track speed
        for (Track trackObject : trackList) {
            for (GameElement elem : trackObject.getTrackContent()) {
                if (trackObject.getDirection() == "right")
                    elem.setPos_x(elem.getPos_x() - elem.getSpeed());
                else elem.setPos_x(elem.getPos_x() + elem.getSpeed());
            }
        }
    }

    private void changeBehaviorCar() {
        for (Track trackObject : trackList) {
            if (trackObject.getClass() == HighWay.class) {
                for (GameElement elem : trackObject.getTrackContent()) {
                    if(elem.getClass() == Car.class && ((Car) elem).getColor() == "Red"){
                        if(frog.getPos_y() > elem.getPos_y() + 25 && elem.getPos_y() + DOT_SIZE != 500 && elem.getPos_y() + DOT_SIZE != 150){
                            elem.setPos_y(elem.getPos_y() + DOT_SIZE);
                        }
                        else if (frog.getPos_y() < elem.getPos_y() + 25 && elem.getPos_y() - DOT_SIZE != 250 && elem.getPos_y() - DOT_SIZE != 0){
                            elem.setPos_y(elem.getPos_y() - DOT_SIZE);
                        }
                        else {
                            elem.getPos_y();
                        }
                    }
                    if(elem.getClass() == Car.class && ((Car) elem).getColor() == "Blue"){
                        speed = frog.getPos_y() == elem.getPos_y() +25 ? 1 : 5;
                        elem.setSpeed(speed);
                    }
                }
            }
        }
    }


    private void checkCollision() {

        if (frog.getPos_y() >= B_HEIGHT) {
            inGame = false;
        }

        if (frog.getPos_y() < 0) {
            inGame = false;
        }

        if (frog.getPos_x() >= B_WIDTH) {
            inGame = false;
        }

        if (frog.getPos_x() < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }


        for (Track trackObject : trackList) {
            for (GameElement element : trackObject.getTrackContent()) {
                if (element.getPos_x() <= 0) element.setPos_x(B_WIDTH);
                else if (element.getPos_x() >= B_WIDTH) element.setPos_x(0);

                if (element.getClass() == Car.class && (element.getPos_x() <= 0 || element.getPos_x() >= B_WIDTH)) {
                    ((HighWay) trackObject).changeCarColor((Car) element);
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkFixedGameElementCollision();
            checkCollision();
            moveImage();
            count += DELAY;
            if(count%1000 == 0){
                changeBehaviorCar();
            }
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
