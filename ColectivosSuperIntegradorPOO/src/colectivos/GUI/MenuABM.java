package colectivos.GUI;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import colectivos.aplicacion.Controlador;
import colectivos.modelo.Linea;
import colectivos.modelo.Parada;
import colectivos.modelo.Tramo;
import colectivos.util.Time;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class MenuABM extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idOrigenTramo;
	private JTextField tiempoTramo;
	private JTextField tipoTramo;
	private Controlador controlador;
	private ResourceBundle rb;
	/*
	private JPanel panelParadas;
	private JPanel panelLineas;
	private JPanel panelTramos;
	private JLabel lbIdP;
	private JButton eliminarLinea;
	private JButton actualizarLinea;
	private JButton agregarLinea;
	*/
	private JTextField idDestinoTramo;
	private JTextField idLinea;
	private JTextField inicio;
	private JTextField paradasLinea;
	private JTextField fin;
	private JTextField frecuencia;
	private JTextField direccionParada;
	private JTextField idParada;
	private JTextField lineasParada;
	private JTextField mensajeStatus;
	private JComboBox<String> comboBoxParadas;
	private JComboBox<String> comboBoxLineas;
	private JComboBox<String> comboBoxTramoOrigen;
	private JComboBox<String> comboBoxTramoDestino;
	public MenuABM() {
		
			
		
	}
	public void iniciar() {
		rb = controlador.getResourceBundle();
		setResizable(false);
		getContentPane().setBackground(new Color(26, 26, 26));
		getContentPane().setLayout(null);
		setTitle("Admin");
		setSize(540, 456);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 192, 192));
		setJMenuBar(menuBar);
		
		
		JMenu menuVisualizar = new JMenu(rb.getString("MenuPrincipal_view"));
		menuBar.add(menuVisualizar);
		ImageIcon imagenParada = new ImageIcon(getClass().getResource("/imagenes/icono_ubicacion.png"));
		JMenuItem menuItemParadas = new JMenuItem(rb.getString("MenuPrincipal_data_stops"));
		menuVisualizar.add(menuItemParadas);
		menuItemParadas.setIcon(imagenParada);

		ImageIcon imagenColectivo = new ImageIcon(getClass().getResource("/imagenes/icono_colectivo.png"));
		JMenuItem menuItemLineas = new JMenuItem(rb.getString("MenuPrincipal_data_lines"));
		menuVisualizar.add(menuItemLineas);
		menuItemLineas.setIcon(imagenColectivo);

		ImageIcon imagenFlecha = new ImageIcon(getClass().getResource("/imagenes/icono_flecha.png"));
		JMenuItem menuItemTramos = new JMenuItem(rb.getString("MenuPrincipal_data_sections"));
		menuVisualizar.add(menuItemTramos);
		menuItemTramos.setIcon(imagenFlecha);
		
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

		
		
		JMenu menuBarABM = new JMenu("ABM");
		menuBar.add(menuBarABM);
		
		JMenuItem menuItemABMParadas = new JMenuItem(rb.getString("MenuPrincipal_data_stops"));
		menuItemABMParadas.setIcon(new ImageIcon(getClass().getResource("/imagenes/icono_ubicacion.png")));
		menuBarABM.add(menuItemABMParadas);
		
		JMenuItem menuItemABMLineas = new JMenuItem(rb.getString("MenuPrincipal_data_lines"));
		menuItemABMLineas.setIcon(new ImageIcon(getClass().getResource("/imagenes/icono_colectivo.png")));
		menuBarABM.add(menuItemABMLineas);
		
		JMenuItem menuItemABMTramos = new JMenuItem(rb.getString("MenuPrincipal_data_sections"));
		menuItemABMTramos.setIcon(new ImageIcon(getClass().getResource("/imagenes/icono_flecha.png")));
		menuBarABM.add(menuItemABMTramos);
		
		ImageIcon imagenBorrar = new ImageIcon(getClass().getResource("/imagenes/icono_borrar.png"));
		ImageIcon imagenActualizar = new ImageIcon(getClass().getResource("/imagenes/icono_guardar.png"));
		ImageIcon imagenAgregar = new ImageIcon(getClass().getResource("/imagenes/icono_agregar.png"));
		
		 JPanel panelParadas;
			panelParadas = new JPanel();
			panelParadas.setBackground(new Color(77, 77, 77));
			panelParadas.setBounds(10, 11, 504, 317);
			getContentPane().add(panelParadas);
			panelParadas.setLayout(null);
			panelParadas.setVisible(false);
			
			ImageIcon imagenLupa = new ImageIcon(getClass().getResource("/imagenes/icono_lupa.png"));
			JButton botonBuscarParada = new JButton("");
			botonBuscarParada.setBackground(new Color(255, 255, 255));
			botonBuscarParada.setBounds(449, 267, 45, 23);
			panelParadas.add(botonBuscarParada);
			botonBuscarParada.setIcon(imagenLupa);
			
			JButton eliminarParada = new JButton();
			eliminarParada.setIcon(imagenBorrar);
			eliminarParada.setBackground(new Color(255, 255, 255));
			eliminarParada.setBounds(439, 11, 55, 23);
			panelParadas.add(eliminarParada);
			
			JButton actualizarParada = new JButton();
			actualizarParada.setHorizontalAlignment(SwingConstants.LEFT);
			actualizarParada.setIcon(imagenActualizar);
			actualizarParada.setBackground(new Color(255, 255, 255));
			actualizarParada.setBounds(374, 11, 55, 23);
			panelParadas.add(actualizarParada);
			
			JButton agregarParada = new JButton();
			agregarParada.setHorizontalAlignment(SwingConstants.LEFT);
			agregarParada.setIcon(imagenAgregar);
			agregarParada.setBackground(new Color(255, 255, 255));
			agregarParada.setBounds(309, 11, 55, 23);
			panelParadas.add(agregarParada);
			
			JLabel l1 = new JLabel(rb.getString("MenuABM_ID"));
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			l1.setBounds(10, 61, 55, 20);
			panelParadas.add(l1);
			
			JLabel l2 = new JLabel(rb.getString("MenuABM_Direccion"));
			l2.setHorizontalAlignment(SwingConstants.LEFT);
			l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			l2.setBounds(10, 134, 224, 21);
			panelParadas.add(l2);
			
			JLabel l3 = new JLabel(rb.getString("MenuPrincipal_data_lines"));
			l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			l3.setBounds(10, 208, 172, 19);
			panelParadas.add(l3);
			
			JLabel Titulo = DefaultComponentFactory.getInstance().createTitle(rb.getString("MenuPrincipal_data_stops"));
			Titulo.setHorizontalAlignment(SwingConstants.CENTER);
			Titulo.setFont(new Font("Tahoma", Font.BOLD, 17));
			Titulo.setBackground(new Color(0, 0, 0));
			Titulo.setForeground(new Color(255, 255, 255));
			Titulo.setBounds(10, 11, 172, 39);
			panelParadas.add(Titulo);
			Titulo.setOpaque(true);
			
			direccionParada = new JTextField();
			direccionParada.setFont(new Font("Tahoma", Font.PLAIN, 14));
			direccionParada.setBounds(10, 166, 224, 31);
			panelParadas.add(direccionParada);
			direccionParada.setColumns(10);
			
			idParada = new JTextField();
			idParada.setFont(new Font("Tahoma", Font.BOLD, 14));
			idParada.setBounds(10, 92, 55, 31);
			panelParadas.add(idParada);
			idParada.setColumns(10);
			
			lineasParada = new JTextField();
			lineasParada.setFont(new Font("Tahoma", Font.BOLD, 12));
			lineasParada.setBounds(10, 238, 224, 31);
			panelParadas.add(lineasParada);
			lineasParada.setColumns(10);
			
			comboBoxParadas = new JComboBox<String>();
			comboBoxParadas.setBounds(384, 267, 55, 23);
			panelParadas.add(comboBoxParadas);
			
			JPanel panelMensaje = new JPanel();
			panelMensaje.setBackground(new Color(77, 77, 77));
			panelMensaje.setBounds(10, 331, 504, 52);
			getContentPane().add(panelMensaje);
			panelMensaje.setLayout(null);
			
			mensajeStatus = new JTextField();
			mensajeStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
			mensajeStatus.setBounds(10, 11, 484, 30);
			panelMensaje.add(mensajeStatus);
			mensajeStatus.setColumns(10);
			mensajeStatus.setEditable(false);
			mensajeStatus.setHorizontalAlignment(JTextField.CENTER);
			
			
			agregarParada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					controlador.insertarParada(idParada.getText(), direccionParada.getText());
					actualizarComboBoxParadas();
					controlador.actualizarComboBoxMenuPrincipal();
				}
			});	
			
			actualizarParada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					controlador.actualizarParada(idParada.getText(), direccionParada.getText());
					actualizarComboBoxParadas();
					controlador.actualizarComboBoxMenuPrincipal();
				}
			});	
			
			eliminarParada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					controlador.borrarParada(idParada.getText());
					actualizarComboBoxParadas();
					controlador.actualizarComboBoxMenuPrincipal();
				}
			});	
			
			botonBuscarParada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Parada p = controlador.buscarParada(((String) comboBoxParadas.getSelectedItem()));
					if (p==null) {
						mensajeABM(rb.getString("Exception_ParadaInexistente"));
						return;
					}
					idParada.setText(""+p.getId());
					direccionParada.setText(p.getDireccion());
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i<p.getLineas().size();i++) {
						sb.append(p.getLineas().get(i).getId() + ";");
					}
					sb.setLength(sb.length()-1);
					lineasParada.setText(sb.toString());
				}
			});	
		
		//================================================================================================
		JPanel panelLineas;
		panelLineas = new JPanel();
		panelLineas.setBackground(new Color(77, 77, 77));
		panelLineas.setBounds(10, 11, 504, 317);
		getContentPane().add(panelLineas);
		panelLineas.setLayout(null);
		panelLineas.setVisible(false);
		
		JButton botonBuscarLineas = new JButton("");
		botonBuscarLineas.setBackground(new Color(255, 255, 255));
		botonBuscarLineas.setBounds(449, 267, 45, 23);
		panelLineas.add(botonBuscarLineas);
		botonBuscarLineas.setIcon(imagenLupa);
		
		
		JButton eliminarLinea;
		eliminarLinea = new JButton();
		eliminarLinea.setIcon(imagenBorrar);
		eliminarLinea.setBackground(new Color(255, 255, 255));
		eliminarLinea.setBounds(439, 11, 55, 23);
		panelLineas.add(eliminarLinea);
		
		JButton actualizarLinea;
		actualizarLinea = new JButton();
		actualizarLinea.setIcon(imagenActualizar);
		actualizarLinea.setBackground(new Color(255, 255, 255));
		actualizarLinea.setBounds(374, 11, 55, 23);
		panelLineas.add(actualizarLinea);
		
		JButton agregarLinea;
		agregarLinea = new JButton();
		agregarLinea.setIcon(imagenAgregar);
		agregarLinea.setBackground(new Color(255, 255, 255));
		agregarLinea.setBounds(309, 11, 55, 23);
		panelLineas.add(agregarLinea);
		
		JLabel lbIdP;
		lbIdP = new JLabel(rb.getString("MenuABM_ID"));
		lbIdP.setHorizontalAlignment(SwingConstants.CENTER);
		lbIdP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbIdP.setBounds(10, 61, 67, 20);
		panelLineas.add(lbIdP);
		
		JLabel lbLin = new JLabel(rb.getString("MenuABM_Inicio"));
		lbLin.setHorizontalAlignment(SwingConstants.CENTER);
		lbLin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLin.setBounds(10, 138, 67, 21);
		panelLineas.add(lbLin);
		
		JLabel lbParadas = new JLabel(rb.getString("MenuPrincipal_data_stops"));
		lbParadas.setHorizontalAlignment(SwingConstants.CENTER);
		lbParadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbParadas.setBounds(10, 208, 281, 19);
		panelLineas.add(lbParadas);
		
		JLabel TituloLineas = DefaultComponentFactory.getInstance().createTitle(rb.getString("MenuPrincipal_data_lines"));
		TituloLineas.setHorizontalAlignment(SwingConstants.CENTER);
		TituloLineas.setFont(new Font("Tahoma", Font.BOLD, 17));
		TituloLineas.setBackground(new Color(0, 0, 0));
		TituloLineas.setForeground(new Color(255, 255, 255));
		TituloLineas.setBounds(10, 11, 172, 39);
		panelLineas.add(TituloLineas);
		TituloLineas.setOpaque(true);
		
		idLinea = new JTextField();
		idLinea.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLinea.setBounds(10, 92, 67, 23);
		panelLineas.add(idLinea);
		idLinea.setColumns(10);
		
		inicio = new JTextField();
		inicio.setBounds(10, 166, 67, 20);
		panelLineas.add(inicio);
		inicio.setColumns(10);
		
		paradasLinea = new JTextField();
		paradasLinea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		paradasLinea.setBounds(10, 240, 281, 31);
		panelLineas.add(paradasLinea);
		paradasLinea.setColumns(10);
		
		fin = new JTextField();
		fin.setBounds(87, 166, 67, 20);
		panelLineas.add(fin);
		fin.setColumns(10);
		
		frecuencia = new JTextField();
		frecuencia.setBounds(164, 166, 67, 20);
		panelLineas.add(frecuencia);
		frecuencia.setColumns(10);
		
		JLabel lbfin = new JLabel(rb.getString("MenuABM_Fin"));
		lbfin.setHorizontalAlignment(SwingConstants.CENTER);
		lbfin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbfin.setBounds(87, 141, 67, 14);
		panelLineas.add(lbfin);
		
		JLabel lbfrecuencia = new JLabel(rb.getString("MenuABM_Frecuencia"));
		lbfrecuencia.setHorizontalAlignment(SwingConstants.CENTER);
		lbfrecuencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbfrecuencia.setBounds(164, 141, 67, 14);
		panelLineas.add(lbfrecuencia);
		
		comboBoxLineas = new JComboBox<String>();
		comboBoxLineas.setBounds(394, 267, 45, 23);
		panelLineas.add(comboBoxLineas);
		setTitle("Admin");
		setVisible(true);
		ImageIcon logo = new ImageIcon("colectivos_logo.png");
		setIconImage(logo.getImage());
		
		botonBuscarLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Linea l = controlador.buscarLinea(((String) comboBoxParadas.getSelectedItem()));
				if (l==null) {
					mensajeABM(rb.getString("Exception_LineaInexistente"));
					return;
				}
				idLinea.setText(l.getId());
				inicio.setText(Time.toTime(l.getComienza()));
				fin.setText(Time.toTime(l.getFinaliza()));
				frecuencia.setText("" + l.getFrecuencia());
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i<l.getParadas().size();i++) {
					sb.append(l.getParadas().get(i).getId()+";");
				}
				sb.setLength(sb.length()-1);
				paradasLinea.setText(sb.toString());
			}
		});
		
		agregarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
				controlador.insertarLinea(idLinea.getText(),Time.toMins(inicio.getText()),Time.toMins(fin.getText()),Integer.parseInt(frecuencia.getText()),paradasLinea.getText());
				actualizarComboBoxLineas();
				}
				catch(NumberFormatException ex) {
					controlador.mensajeABM(rb.getString("Exception_ErrorEntradaDatos"));
				}
				}	
		});
		
		eliminarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.borrarLinea(idLinea.getText());
				actualizarComboBoxLineas();
			}
		});
		
		
		
		actualizarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
				controlador.actualizarLinea(idLinea.getText(),Time.toMins(inicio.getText()),Time.toMins(fin.getText()),Integer.parseInt(frecuencia.getText()),paradasLinea.getText());
				actualizarComboBoxLineas();
				}
				catch(NumberFormatException ex) {
					controlador.mensajeABM(rb.getString("Exception_ErrorEntradaDatos"));
				}
			}
		});
		
		
		
		//==================================================================
		JPanel panelTramos;
		panelTramos = new JPanel();
		panelTramos.setBackground(new Color(77, 77, 77));
		panelTramos.setBounds(10, 11, 504, 317);
		getContentPane().add(panelTramos);
		panelTramos.setLayout(null);
		panelTramos.setVisible(false);
		
		JButton botonBuscarTramos = new JButton();
		botonBuscarTramos.setBackground(new Color(255, 255, 255));
		botonBuscarTramos.setBounds(449, 267, 45, 23);
		panelTramos.add(botonBuscarTramos);
		botonBuscarTramos.setIcon(imagenLupa);
		
		idOrigenTramo = new JTextField();
		idOrigenTramo.setFont(new Font("Tahoma", Font.BOLD, 15));
		idOrigenTramo.setBounds(10, 92, 67, 31);
		panelTramos.add(idOrigenTramo);
		idOrigenTramo.setColumns(10);
		
		tiempoTramo = new JTextField();
		tiempoTramo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tiempoTramo.setBounds(10, 166, 213, 31);
		panelTramos.add(tiempoTramo);
		tiempoTramo.setColumns(10);
		
		tipoTramo = new JTextField();
		tipoTramo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tipoTramo.setBounds(10, 238, 213, 31);
		panelTramos.add(tipoTramo);
		tipoTramo.setColumns(10);
		
		
		JButton eliminarTramo = new JButton();
		eliminarTramo.setIcon(imagenBorrar);
		eliminarTramo.setBackground(new Color(255, 255, 255));
		eliminarTramo.setBounds(439, 11, 55, 23);
		panelTramos.add(eliminarTramo);
		
		JButton actualizarTramo = new JButton();
		actualizarTramo.setIcon(imagenActualizar);
		actualizarTramo.setBackground(new Color(255, 255, 255));
		actualizarTramo.setBounds(374, 11, 55, 23);
		panelTramos.add(actualizarTramo);
		
		JButton agregarTramo = new JButton();
		agregarTramo.setIcon(imagenAgregar);
		agregarTramo.setBackground(new Color(255, 255, 255));
		agregarTramo.setBounds(309, 11, 55, 23);
		panelTramos.add(agregarTramo);
		agregarTramo.addActionListener(null);
		
		
		JLabel lblIdOrigen = new JLabel(rb.getString("MenuABM_ID_ORIGEN"));
		lblIdOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdOrigen.setBounds(10, 61, 67, 20);
		panelTramos.add(lblIdOrigen);
		
		JLabel lbTiempoT = new JLabel(rb.getString("MenuABM_Tiempo"));
		lbTiempoT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbTiempoT.setBounds(10, 134, 67, 21);
		panelTramos.add(lbTiempoT);
		
		JLabel lblTipoT = new JLabel(rb.getString("MenuABM_Tipo"));
		lblTipoT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoT.setBounds(10, 208, 67, 19);
		panelTramos.add(lblTipoT);
		
		JLabel TituloTramos = DefaultComponentFactory.getInstance().createTitle(rb.getString("MenuPrincipal_data_sections"));
		TituloTramos.setHorizontalAlignment(SwingConstants.CENTER);
		TituloTramos.setFont(new Font("Tahoma", Font.BOLD, 17));
		TituloTramos.setBackground(new Color(0, 0, 0));
		TituloTramos.setForeground(new Color(255, 255, 255));
		TituloTramos.setBounds(10, 11, 172, 39);
		panelTramos.add(TituloTramos);
		TituloTramos.setOpaque(true);
		
		idDestinoTramo = new JTextField();
		idDestinoTramo.setFont(new Font("Tahoma", Font.BOLD, 15));
		idDestinoTramo.setColumns(10);
		idDestinoTramo.setBounds(115, 92, 67, 31);
		panelTramos.add(idDestinoTramo);
		ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/colectivos_logo.png"));
		setIconImage(imagen.getImage());
		JLabel lblIdDestino = new JLabel(rb.getString("MenuABM_ID_DESTINO"));
		lblIdDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdDestino.setBounds(115, 61, 67, 20);
		panelTramos.add(lblIdDestino);
		
		comboBoxTramoOrigen = new JComboBox<String>();
		comboBoxTramoOrigen.setBounds(323, 268, 53, 22);
		panelTramos.add(comboBoxTramoOrigen);
		
		comboBoxTramoDestino = new JComboBox<String>();
		comboBoxTramoDestino.setBounds(386, 268, 53, 22);
		panelTramos.add(comboBoxTramoDestino);
		
		agregarTramo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.insertarTramo(idOrigenTramo.getText(), idDestinoTramo.getText(), tiempoTramo.getText(), tipoTramo.getText());
				actualizarComboBoxTramos();
			}
		});
		
		eliminarTramo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controlador.borrarTramo(idOrigenTramo.getText(), idDestinoTramo.getText());
				actualizarComboBoxTramos();
			}
		});
		
		actualizarTramo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
				controlador.actualizarTramo(idOrigenTramo.getText(), idDestinoTramo.getText(),Integer.parseInt(tiempoTramo.getText()) , Integer.parseInt(tipoTramo.getText()));
				actualizarComboBoxTramos();
				}
				catch(NumberFormatException ex) {
					controlador.mensajeABM(rb.getString("Exception_ErrorEntradaDatos"));
				}
			}
		});	
		
		botonBuscarTramos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			Tramo t = controlador.buscarTramo(((String) comboBoxTramoOrigen.getSelectedItem()),((String) comboBoxTramoDestino.getSelectedItem()));
			if (t==null) {
				mensajeABM(rb.getString("Exception_TramoInexistente"));
				return;
			}
			idOrigenTramo.setText("" +t.getInicio().getId());
			idDestinoTramo.setText(""+t.getFin().getId());
			tiempoTramo.setText("" +t.getTiempo());
			tipoTramo.setText(""+t.getTipo());
			}
			
		});	
		
		
		
		menuItemABMParadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				panelTramos.setVisible(false);
				panelLineas.setVisible(false);
				panelParadas.setVisible(true);
			}
		});	
		menuItemABMLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				panelParadas.setVisible(false);
				panelTramos.setVisible(false);
				panelLineas.setVisible(true);
			}
		});	
		menuItemABMTramos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				panelLineas.setVisible(false);
				panelParadas.setVisible(false);
				panelTramos.setVisible(true);
			}
		});
		
		actualizarComboBoxParadas();
		actualizarComboBoxLineas();
		actualizarComboBoxTramos();
		setVisible(true);
				
	}
	
	public void mensajeABM(String s) {
		mensajeStatus.setEditable(true);
		mensajeStatus.setText(s);
		mensajeStatus.setEditable(false);
	}
	
	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}
	
	public void actualizarComboBoxParadas() {
		List<Parada> paradas = controlador.getParadas();
		comboBoxParadas.addItem(" - ");
		for (int i = 0; i < paradas.size(); i++) {
			comboBoxParadas.addItem(paradas.get(i).getId() + "");
		}
	}
	
	
	public void actualizarComboBoxTramos() {
		List<Parada> paradas = controlador.getParadas();
		comboBoxTramoOrigen.addItem(" - ");
		comboBoxTramoDestino.addItem(" - ");
		for (int i = 0; i < paradas.size(); i++) {
			comboBoxTramoOrigen.addItem(paradas.get(i).getId() + "");
			comboBoxTramoDestino.addItem(paradas.get(i).getId() + "");
		}
	
	}
	
	public void actualizarComboBoxLineas() {
		List<Linea> lineas = controlador.getLineas();
		comboBoxLineas.addItem(" - ");
		for (int i = 0; i < lineas.size(); i++) {
			comboBoxLineas.addItem(lineas.get(i).getId() + "");
		}
	
	}
}
