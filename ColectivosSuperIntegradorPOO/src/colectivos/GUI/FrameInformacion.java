package colectivos.GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrameInformacion extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// String titulo
	public FrameInformacion(String nuevoTitulo, String cadena) {
		
		setSize(730, 400);
		setResizable(false);
		setLocationRelativeTo(null);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/colectivos_logo.png"));
		setTitle(nuevoTitulo);
		getContentPane().setBackground(new Color(91, 91, 91));
		getContentPane().setLayout(null);
		JLabel titulo = DefaultComponentFactory.getInstance().createTitle(nuevoTitulo);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setBackground(new Color(0, 0, 0));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 0, 714, 32);
		titulo.setOpaque(true);
		getContentPane().add(titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 694, 307);
		getContentPane().add(scrollPane);

		JTextArea informacion= new JTextArea();
		informacion.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		informacion.setForeground(new Color(255, 255, 255));
		informacion.setBackground(new Color(60, 60, 60));
		informacion.setText(cadena);
		informacion.setEditable(false);
		scrollPane.setViewportView(informacion);
		setIconImage(imagen.getImage());
	}

}
