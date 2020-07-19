package chase;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chase extends JFrame{
	static final int width = 500;
	static final int height  =500;

	public static void main(String[] artgs) {
		Chase chase = new Chase();
		chase.setTitle("Chasing AI");
		chase.setSize(width, height);
		chase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PrayPredator pp = new PrayPredator();

		chase.add(pp);
		chase.setVisible(true);
	}
}

class PrayPredator extends JPanel implements KeyListener{
	static private final int width = 490;
	static private final int height = 470;

	public int pray_x;
	public int pray_y;

	public int predator_x;
	public int predator_y;

	public PrayPredator() {
		Random rand = new Random();

		pray_x = rand.nextInt(300);
		pray_y = rand.nextInt(300);

		predator_x = rand.nextInt(180);
		predator_y = rand.nextInt(180);

		setFocusable(true);
		addKeyListener(this);
	}

	private void moveDown() { pray_y-=10; }
	private void moveUp() { pray_y += 10; }
	private void moveRight() { pray_x += 10; }
	private void moveLeft() { pray_x -= 10; }

	private void drawPredator(Graphics g) throws InterruptedException{
		g.setColor(Color.RED);
		g.fillRect(predator_x, predator_y, 10, 10);
		g.drawString("predator", predator_x-18, predator_y-5);
		if(predator_x > pray_x) {
			predator_x--;
		}else if(predator_x < pray_x){
			predator_x++;
		}
		if(predator_y > pray_y) {
			predator_y--;
		}else if(predator_y < pray_y){
			predator_y++;
		}
		Thread.sleep(5);
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT:
			if(pray_x-10 < 0) pray_x = 0;
			else if(pray_x-10 > width) pray_x = width;
			else moveLeft();
			break;
        case KeyEvent.VK_RIGHT:
        	if(pray_x+10 < 0) pray_x = 0;
        	else if(pray_x+10 > width) pray_x = width;
        	else moveRight();
        	break;
        case KeyEvent.VK_UP:
        	if(pray_y-10 < 0) pray_y = 0;
        	else if(pray_y-10 > height) pray_y = height;
        	else moveDown();
        	break;
        case KeyEvent.VK_DOWN:
        	if(pray_y+10 < 0) pray_y = 0;
        	else if(pray_y+10 > height) pray_y = height;
        	else moveUp();
        	break;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void paint(Graphics g) {
		//pray
		g.setColor(Color.BLUE);
		g.fillRect(pray_x, pray_y, 10, 10);
		g.drawString("pray", pray_x-5, pray_y-5);
		//predator
		try {
			drawPredator(g);
		} catch (InterruptedException e) {
		}
	}
}

