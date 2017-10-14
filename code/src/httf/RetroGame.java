package httf;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JPanel;

import httf.ui.Screen;
import httf.util.InputHandler;

public class RetroGame extends Canvas implements Runnable {
	
	public final int WIDTH = 300;
	public final int HEIGHT = 210;
	public final int SCALE = 2;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 210;
	public static final int SCALE = 4;
	
	public JFrame frame;
	public BufferedImage img;
	public int[] pixels;
	
	public InputHandler input;
	public Screen screen;
	
	public Thread thread;
	public boolean running;
	
	public RetroGame() {
		input = new InputHandler(65535);
		frame = new JFrame("Hack to the future 2017");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.addKeyListener(input);
		frame.addMouseListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseWheelListener(input);
		
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
		
		thread = new Thread(this);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getData().getDataBuffer()).getData();
		screen = new Screen(WIDTH, HEIGHT);
		
		Canvas c = this;
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(c);
		
		frame.setContentPane(panel);
		panel.addKeyListener(input);
		panel.addMouseListener(input);
		panel.addMouseMotionListener(input);
		panel.addMouseWheelListener(input);
		frame.setVisible(true);
		start();
	}
	
	@Override
	public void run() {
		while(running) {
			tick();
			render();
		}
	}
	
	private void tick() {
		if(input.keys[KeyEvent.VK_ESCAPE]) {
			System.exit(0);
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.render();
		
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				pixels[x + y * WIDTH] = screen.pixels[x][y];
				img.setRGB(x, y, pixels[x + y * WIDTH]);
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new RetroGame();
	}
	
}
