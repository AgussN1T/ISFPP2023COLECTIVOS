package colectivos.GUI;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.UIManager;

import colectivos.aplicacion.Constantes;
import colectivos.aplicacion.Controlador;
import colectivos.util.Time;
import colectivos.modelo.Parada;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

public class MenuPrincipal extends JFrame {
	/**
	 * 
	 */
	//
	// barra de progreso
	private JProgressBar barraDeProgreso;
	private Timer timer;
	private int progreso;

	private Controlador controlador;
	private ResourceBundle rb;
	private static final long serialVersionUID = 1L;
	private JTextField cantLineas;
	private JTextField horario;
	private JTextArea caminoMasCortoSalida;
	private MenuABM menuABM;
	private JComboBox<String> comboBoxOrigen;
	private JComboBox<String> comboBoxDestino;
	private JButton calcularCamino;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel Titulo;
	private JMenu menuVisualizar;
	private JMenuItem menuItemParadas;
	private JMenuItem menuItemLineas;
	private JMenuItem menuItemTramos;
	private JMenu menuItemIdiomas;
	private JMenuItem menuItemEspaniol;
	private JMenuItem menuItemIngles;
	private JMenuItem menuItemItaliano;

	public MenuPrincipal() {
	}

	public void iniciar() {
		rb = controlador.getResourceBundle();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(rb.getString("MenuPrincipal_company"));
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(91, 91, 91));
		getContentPane().setLayout(null);
		setSize(850, 520);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(60, 60, 60));
		panel.setBounds(20, 65, 585, 118);
		getContentPane().add(panel);
		panel.setLayout(null);

		barraDeProgreso = new JProgressBar();
		barraDeProgreso.setBackground(Color.WHITE);
		barraDeProgreso.setForeground(Color.BLACK);
		barraDeProgreso.setBounds(20, 424, 791, 26);
		getContentPane().add(barraDeProgreso);

		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/colectivos_logo.png"));
		setIconImage(imagen.getImage());
		calcularCamino = new JButton(rb.getString("MenuPrincipal_data_calculate"));
		calcularCamino.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		calcularCamino.setForeground(UIManager.getColor("Button.foreground"));
		calcularCamino.setBackground(new Color(192, 192, 192));
		calcularCamino.setBounds(20, 80, 137, 27);
		panel.add(calcularCamino);

		this.lblNewLabel = new JLabel(rb.getString("MenuPrincipal_calculo_origin"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 16, 162, 20);
		panel.add(lblNewLabel);

		this.lblNewLabel_1 = new JLabel(rb.getString("MenuPrincipal_calculo_destination"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(192, 13, 161, 27);
		panel.add(lblNewLabel_1);

		cantLineas = new JTextField();
		cantLineas.setFont(new Font("Tahoma", Font.BOLD, 11));
		cantLineas.setBackground(new Color(216, 216, 216));
		cantLineas.setBounds(477, 44, 87, 20);
		panel.add(cantLineas);
		cantLineas.setColumns(10);

		horario = new JTextField();
		horario.setFont(new Font("Tahoma", Font.BOLD, 11));
		horario.setBackground(new Color(216, 216, 216));
		horario.setBounds(363, 44, 104, 20);
		panel.add(horario);
		horario.setColumns(10);

		this.lblNewLabel_2 = new JLabel(rb.getString("MenuPrincipal_calculo_schedule"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblNewLabel_2.setBounds(363, 16, 104, 20);
		panel.add(lblNewLabel_2);

		this.lblNewLabel_3 = new JLabel(rb.getString("MenuPrincipal_data_lines"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(477, 14, 87, 24);
		panel.add(lblNewLabel_3);

		comboBoxOrigen = new JComboBox<String>();
		comboBoxOrigen.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		comboBoxOrigen.setBounds(20, 43, 162, 22);
		panel.add(comboBoxOrigen);

		comboBoxDestino = new JComboBox<String>();
		comboBoxDestino.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 11));
		comboBoxDestino.setBounds(192, 43, 161, 22);
		panel.add(comboBoxDestino);

		this.Titulo = DefaultComponentFactory.getInstance().createTitle("COLECTIVOS");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Titulo.setOpaque(true);
		Titulo.setForeground(new Color(255, 255, 255));
		Titulo.setBackground(SystemColor.desktop);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(10, 11, 814, 43);
		getContentPane().add(Titulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 195, 791, 222);
		getContentPane().add(scrollPane);

		caminoMasCortoSalida = new JTextArea();
		scrollPane.setViewportView(caminoMasCortoSalida);
		caminoMasCortoSalida.setForeground(Color.BLACK);
		caminoMasCortoSalida.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		caminoMasCortoSalida.setBackground(new Color(60, 60, 60));

		Icon icon2 = new ImageIcon(getClass().getResource("/imagenes/colectivo-bondi.gif"));
		JLabel gifColectivo = new JLabel(icon2);
		gifColectivo.setBounds(628, 65, 183, 118);
		getContentPane().add(gifColectivo);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 192, 192));
		setJMenuBar(menuBar);

		this.menuVisualizar = new JMenu(rb.getString("MenuPrincipal_view"));
		menuBar.add(menuVisualizar);
		// ImageIcon imagenVisualizar = new ImageIcon("icono_lupa.png");
		// menuVisualizar.setIcon(imagenVisualizar);

		ImageIcon imagenParada = new ImageIcon(getClass().getResource("/imagenes/icono_ubicacion.png"));
		this.menuItemParadas = new JMenuItem(rb.getString("MenuPrincipal_data_stops"));
		menuVisualizar.add(menuItemParadas);
		menuItemParadas.setIcon(imagenParada);

		ImageIcon imagenColectivo = new ImageIcon(getClass().getResource("/imagenes/icono_colectivo.png"));
		this.menuItemLineas = new JMenuItem(rb.getString("MenuPrincipal_data_lines"));
		menuVisualizar.add(menuItemLineas);
		menuItemLineas.setIcon(imagenColectivo);

		ImageIcon imagenFlecha = new ImageIcon(getClass().getResource("/imagenes/icono_flecha.png"));
		this.menuItemTramos = new JMenuItem(rb.getString("MenuPrincipal_data_sections"));
		menuVisualizar.add(menuItemTramos);
		menuItemTramos.setIcon(imagenFlecha);

		JMenu menuADM = new JMenu("Admin");
		menuBar.add(menuADM);

		JMenuItem menuItemABM = new JMenuItem("ABM");
		ImageIcon imagenAdmin = new ImageIcon(getClass().getResource("/imagenes/icono_administrador.png"));
		menuItemABM.setIcon(imagenAdmin);
		menuADM.add(menuItemABM);

		this.menuItemIdiomas = new JMenu(rb.getString("MenuPrincipal_Idioma"));
		menuBar.add(menuItemIdiomas);

		ImageIcon imagenEspania = new ImageIcon(getClass().getResource("/imagenes/icono_espania.png"));
		this.menuItemEspaniol = new JMenuItem(rb.getString("Idioma_es"));
		menuItemEspaniol.setIcon(imagenEspania);
		menuItemIdiomas.add(menuItemEspaniol);

		this.menuItemIngles = new JMenuItem(rb.getString("Idioma_us"));
		ImageIcon imagenUS = new ImageIcon(getClass().getResource("/imagenes/icono_EstadosUnidos.png"));
		menuItemIngles.setIcon(imagenUS);
		menuItemIdiomas.add(menuItemIngles);

		this.menuItemItaliano = new JMenuItem(rb.getString("Idioma_it"));
		ImageIcon imagenItalia = new ImageIcon(getClass().getResource("/imagenes/icono_italia.png"));
		menuItemItaliano.setIcon(imagenItalia);
		menuItemIdiomas.add(menuItemItaliano);

		menuItemEspaniol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.cambiarLenguaje(Constantes.ESPANIOL);
				cambiarLenguaje();
			}
		});
		menuItemIngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.cambiarLenguaje(Constantes.INGLES);
				cambiarLenguaje();
			}
		});

		menuItemItaliano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.cambiarLenguaje(Constantes.ITALIANO);
				cambiarLenguaje();
			}
		});

		menuItemABM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuABM = new MenuABM();
				menuABM.setControlador(getControlador());
				menuABM.iniciar();
			}
		});

		actualizarComboBox();
		setVisible(true);

		menuItemParadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameInformacion pantalla = new FrameInformacion(
						rb.getString("MenuPrincipal_view") + " " + rb.getString("MenuPrincipal_data_stops"),
						controlador.listarParadas());
				pantalla.setVisible(true);
			}
		});

		menuItemLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameInformacion pantalla = new FrameInformacion(
						rb.getString("MenuPrincipal_view") + " " + rb.getString("MenuPrincipal_data_lines"),
						controlador.listarLineas());
				pantalla.setVisible(true);
			}
		});

		menuItemTramos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				FrameInformacion pantalla = new FrameInformacion(
						rb.getString("MenuPrincipal_view") + " " + rb.getString("MenuPrincipal_data_sections"),
						controlador.listarTramos());
				pantalla.setVisible(true);
			}
		});

		calcularCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Deshabilita el botón mientras se realiza el cálculo
				calcularCamino.setEnabled(false);

				// Inicializa la barra de progreso y el timer
				progreso = 0;
				barraDeProgreso.setValue(progreso);
				timer = new Timer(25, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						progreso++;
						barraDeProgreso.setValue(progreso);
						if (progreso == 100) {
							timer.stop();
							// Se muestra el resultado en pantalla
							try {
								String[] separador = ((String) comboBoxOrigen.getSelectedItem()).split("-");
								String origen = separador[0];
								separador = ((String) comboBoxDestino.getSelectedItem()).split("-");
								String destino = separador[0];
								if (origen.equals(destino)) {
									mensajeError("Las paradas de origen y destino ingresadas deben ser distintas");
									return;
								}
								// Se realiza el cálculo en segundo plano usando SwingWorker
								SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
									@Override
									protected String doInBackground() throws Exception {
										return controlador.obtenerCaminoMasCorto(origen, destino, horario.getText(),
												cantLineas.getText());
									}

									@Override
									protected void done() {
										try {
											caminoMasCortoSalida.setText(get());
										} catch (Exception ex) {
											caminoMasCortoSalida.setText(controlador.obtenerCaminoMasCorto(origen,
													destino, Time.horaActual(), Constantes.CANT_LINEAS_DEFAULT));
										} finally {
											// Habilita nuevamente el botón después de terminar el cálculo
											calcularCamino.setEnabled(true);
										}
									}
								};
								worker.execute();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				});
				timer.start();
			}
		});
	}

	public void mensajeError(String s) {
		this.caminoMasCortoSalida.setText(s);
	}

	public void mensajeABM(String s) {
		menuABM.mensajeABM(s);
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	// implementar combo box
	public void actualizarComboBox() {
		calcularCamino.setEnabled(false);
		List<Parada> paradas = controlador.getParadas();
		comboBoxOrigen.addItem(" - ");
		comboBoxDestino.addItem(" - ");
		for (int i = 0; i < paradas.size(); i++) {
			comboBoxOrigen.addItem(paradas.get(i).getId() + "-" + paradas.get(i).getDireccion());
			comboBoxDestino.addItem(paradas.get(i).getId() + "-" + paradas.get(i).getDireccion());
		}
		calcularCamino.setEnabled(true);
	}

	public void cambiarLenguaje() {
		this.rb = controlador.getRb();
		this.calcularCamino.setText(rb.getString("MenuPrincipal_data_calculate"));
		this.lblNewLabel.setText(rb.getString("MenuPrincipal_calculo_origin"));
		this.lblNewLabel_1.setText(rb.getString("MenuPrincipal_calculo_destination"));
		this.lblNewLabel_2.setText(rb.getString("MenuPrincipal_calculo_schedule"));
		this.lblNewLabel_3.setText(rb.getString("MenuPrincipal_data_lines"));
		this.menuVisualizar.setText(rb.getString("MenuPrincipal_view"));
		this.menuItemParadas.setText(rb.getString("MenuPrincipal_data_stops"));
		this.menuItemLineas.setText(rb.getString("MenuPrincipal_data_lines"));
		this.menuItemTramos.setText(rb.getString("MenuPrincipal_data_sections"));
		this.menuItemIdiomas.setText(rb.getString("MenuPrincipal_Idioma"));
		this.menuItemEspaniol.setText(rb.getString("Idioma_es"));
		this.menuItemIngles.setText(rb.getString("Idioma_us"));
		this.menuItemItaliano.setText(rb.getString("Idioma_it"));
	}

}
