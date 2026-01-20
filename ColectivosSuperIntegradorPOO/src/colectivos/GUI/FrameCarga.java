package colectivos.GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import java.awt.Color;

public class FrameCarga extends JFrame implements Runnable {
	private JProgressBar barraDeProgreso;
	private Timer timer;
	private int progreso;

	private static final long serialVersionUID = 1L;

	public FrameCarga() throws InterruptedException {
		getContentPane().setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setSize(504, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/colectivos_logo.png"));
		setIconImage(imagen.getImage());
		
		
		barraDeProgreso = new JProgressBar();
		barraDeProgreso.setBackground(new Color(255, 255, 255));
		barraDeProgreso.setForeground(new Color(0, 128, 255));
		barraDeProgreso.setBounds(10, 413, 484, 26);
		getContentPane().add(barraDeProgreso);
		
		
		JLabel lblPantallaCarga = new JLabel(imagen);
		lblPantallaCarga.setBounds(10, 9, 484, 430);
		getContentPane().add(lblPantallaCarga);
		barraDeProgreso.setStringPainted(true);
		setVisible(true);
	}


	@Override
	public void run() {
		timer = new Timer(10, e -> {
			if (progreso == 100) {
				timer.stop();
			} else {
				progreso++;
				barraDeProgreso.setValue(progreso);
			}
		});

		timer.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setVisible(false);
	}
}
