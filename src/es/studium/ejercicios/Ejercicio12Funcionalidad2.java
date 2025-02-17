package es.studium.ejercicios;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

// Clase principal que implementa un juego de Ahorcado
public class Ejercicio12Funcionalidad2 {
	// Variables del juego
	String[] palabras = {
			"libro", "perro", "casa", "arbol", "mesa", "silla", "camino", "futuro", "juego", "raton",
			"lampara", "espejo", "reloj", "puerta", "ventana", "ciudad", "sol", "estrella", "planeta", "calle",
			"fruta", "zapato", "color", "cielo", "madera", "metal", "nube", "flor", "rio", "papel",
			"luz", "torre", "puente", "camisa", "coche", "avion", "tren", "cuchara", "gato", "bolsa",
			"amigo", "niño", "lago", "sombra", "piedra", "mar", "libertad", "campo", "montaña", "playa"
	};
	Random random= new Random();

	int posicionRandom=0;
	boolean acierto = false; // Indica si se acertó la letra
	String nombre="";
	String palabraSecreta = ""; // Palabra que debe adivinarse
	int intentos = 10; // Número de intentos restantes
	String palabra = ""; // Representación de la palabra actual (con guiones bajos)
	String letra = ""; // Letra que el usuario intenta adivinar

	// Componentes de la interfaz gráfica
	Frame ventanaPalabra = new Frame("Indicar Usuario"); // Ventana para ingresar el nombre
	Frame ventana = new Frame("Ahorcado"); // Ventana principal del juego
	Frame ventanaGanado= new Frame("VICTORIA"); // Ventana principal del juego

	// Paneles para organizar los elementos de la interfaz
	Panel p1 = new Panel(); // Panel para la palabra
	Panel p2 = new Panel(); // Panel para los intentos y las letras probadas
	Panel p3 = new Panel(); // Panel para la interacción del jugador
	Panel pGanado = new Panel(); // Panel para la interacción del jugador

	// Etiquetas para mostrar información
	Label label1 = new Label("Adivina la palabra secreta:");
	Label label2 = new Label("Intentos restantes: " + intentos + " de 10");
	Label label3 = new Label("Letras probadas:");
	Label label4 = new Label("Probar con la letra...");
	Label label5 = new Label("Buenas, dime tu nombre: ");
	Label labelGanado = new Label();

	// Botones para acciones
	Button botonPalabra = new Button("Guardar"); // Botón para guardar la palabra secreta
	Button botonJugar = new Button("Jugar"); // Botón para intentar adivinar
	Button botonResolver= new Button("Rendirse");
	Button nuevaPart= new Button("Jugar de Nuevo");

	// Campos de texto
	TextField txt1 = new TextField(15); // Campo para mostrar la palabra actual
	TextField txt2 = new TextField(15); // Campo de texto no utilizado en este fragmento
	TextField txt3 = new TextField(3); // Campo para ingresar la letra
	TextField txt4 = new TextField(30); // Campo para mostrar las letras probadas
	TextField txt5 = new TextField(15); // Campo para ingresar el nombre

	MenuBar barra= new MenuBar();
	Menu barra1= new Menu("Menu");
	MenuItem nuevaPartida= new MenuItem("Nueva Partida");
	// Constructor de la clase
	public Ejercicio12Funcionalidad2() {
		// Configuración inicial de la ventana para ingresar la palabra secreta
		ventanaPalabra.setLayout(new FlowLayout());
		ventanaPalabra.setSize(250, 125);
		ventanaPalabra.setResizable(false);
		ventanaPalabra.setLocationRelativeTo(null);
		ventanaPalabra.setVisible(true);

		// Añadir componentes a la ventana de palabra secreta
		ventanaPalabra.add(label5);
		ventanaPalabra.add(txt5);
		ventanaPalabra.add(botonPalabra);

		txt1.setEditable(false);
		// Cierra la ventana cuando se pulsa el botón de cerrar
		ventanaPalabra.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ventanaPalabra.dispose();
			}
		});

		// Acción al pulsar el botón "Guardar"
		botonPalabra.addActionListener(new ActionListener() {
			@Override
			// Limitar que ingrese solo 1 caracter en el TextField de LETRA
			public void actionPerformed(ActionEvent e) {
				ventanaPalabra.dispose();
				  txt3.addKeyListener(new KeyAdapter() {
			            @Override
			            public void keyTyped(KeyEvent e) {
			                if (txt3.getText().length() >= 1) {
			                    e.consume();  // Impide que se ingrese más de un carácter
			                }
			            }
			        });
				  
				  
				  // Hace que al pulsar Enter simule un click en boton
					txt3.addKeyListener(new KeyAdapter() {
			            @Override
			            public void keyPressed(KeyEvent e) {
			                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // Detecta Enter
			                    botonJugar.dispatchEvent(new ActionEvent(botonJugar, ActionEvent.ACTION_PERFORMED, "click"));
			                }
			            }
			        });
				// Configurar el botón "Jugar" con la acción correspondiente
				botonJugar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							if (intentos > 1) {
								
								letra = txt3.getText(); // Obtener la letra ingresada
								letra=letra.toUpperCase();
								palabraSecreta=palabraSecreta.toUpperCase();

								// Verificar si la letra está en la palabra secreta
								for (int i = 0; i < palabraSecreta.length(); i++) {
									acierto = letra.equals(String.valueOf(palabraSecreta.charAt(i)));
									System.out.println(acierto); // Imprimir si hubo acierto
									if (acierto) {
										// Sustituir el caracter de palabra _ por la letra correcta
										String parteUno="";
										String parteDos="";
										//METODO PACO	
										parteUno=palabra.substring(0, i*2);
										parteDos=palabra.substring(i*2+1,palabra.length());

										palabra=parteUno+letra+parteDos;

										// Metodo PABLO  	palabra=palabra.substring(0, i*2)+letra+palabra.substring(i*2+1,palabra.length());


										txt1.setText(palabra);
										
										txt3.setText("");
									}
								}

								// Comprobar si la palabra contiene la letra ingresada
								if (!palabraSecreta.contains(letra)) {
									intentos -= 1; // Reducir los intentos restantes
									label2.setText("Intentos restantes: " + intentos + " de 10");
									p2.add(label2); // Actualizar el texto en el panel
									System.out.println("funciona"); // Mensaje de depuración
									txt4.setText(txt4.getText()+letra+" ");
									txt3.setText("");

								}
								//FRAME PERDIDO
							} else {
								Frame perdido = new Frame();

								Label perdLabel = new Label(nombre+" has perdido la resputa era: "+palabraSecreta);
								perdido.setLayout(new FlowLayout());
								perdido.setSize(300, 100);
								perdido.setLocationRelativeTo(null);
								
								perdido.add(perdLabel);
								perdLabel.setBackground(Color.gray);
								perdido.add(nuevaPart);
								
								nuevaPart.addActionListener(new ActionListener()
								{
									
									@Override
									public void actionPerformed(ActionEvent e)
									{
										intentos=10;
										label2.setText("Intentos restantes: " + intentos + " de 10");
										
										
										posicionRandom=random.nextInt(50);
										palabraSecreta=palabras[posicionRandom];
										
										palabra="";
										for (int i = 0; i < palabraSecreta.length(); i++) {
											palabra += "_ ";
										}
										
										txt1.setText(palabra);
										txt4.setText("");
										txt3.setText("");
										perdido.dispose();
									}
								});
								
								perdido.setVisible(true);
								
								

								perdido.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosing(WindowEvent e) {
										perdido.dispose();
									}
								});
							}
						} catch (NumberFormatException ex) {
							txt3.setText("Escribe un número"); // Mensaje de error si no se ingresa un número válido
						}
						
						/*if (palabra.equals(palabraSecreta)) {
							System.out.println("Acierto");
						}*/
						// Esto comprueba si has acabado de DESCUBRIR LA PALABRA
						if (txt1.getText().replace(" ", "").equals(palabraSecreta)) {
							
							ventana.setLayout(new FlowLayout());
							ventanaGanado.setSize(320,100);
							ventanaGanado.setLocationRelativeTo(null);
							ventanaGanado.setResizable(false);
							
							labelGanado.setText("Enhorabuena has completado la palabra: "+ palabra);
							pGanado.add(labelGanado);
							pGanado.add(nuevaPart);
							ventanaGanado.add(pGanado);
							
							ventanaGanado.setVisible(true);
							
							
							System.out.println("GANADO");
						}
						System.out.println(txt1.getText().replace(" ", "")+" "+ palabraSecreta);
					}
					
					
				});

				// Configuración inicial de la ventana principal del juego
				p1.setLayout(new FlowLayout(FlowLayout.LEFT));
				nombre= txt5.getText(); // Obtener la palabra secreta ingresada
				System.out.println(palabraSecreta); // Imprimir la palabra secreta (depuración)
				posicionRandom=random.nextInt(50);

				palabraSecreta=palabras[posicionRandom];
				//palabra del Array

				// Inicializar la palabra con guiones bajos
				for (int i = 0; i < palabraSecreta.length(); i++) {
					if (i!=palabraSecreta.length()-1) {
					palabra += "_ ";}
					else {
						palabra+="_";
					}
				}
				

				System.out.println(palabra); // Imprimir la palabra inicial (depuración)
				txt1.setText(palabra);
				txt1.setBackground(Color.gray);
				p1.add(label1);
				p1.add(txt1);

				ventana.setLayout(new GridLayout(3, 1)); // Configurar diseño de la ventana
				ventana.setSize(500, 250);
				ventana.setResizable(false);
				ventana.setLocationRelativeTo(null);

				barra1.add(nuevaPartida);
				barra.add(barra1);
				ventana.setMenuBar(barra);
				
				
				nuevaPartida.addActionListener(new ActionListener()
				{
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						intentos=10;
						label2.setText("Intentos restantes: " + intentos + " de 10");
						
						
						posicionRandom=random.nextInt(50);
						palabraSecreta=palabras[posicionRandom];
						
						palabra="";
						for (int i = 0; i < palabraSecreta.length(); i++) {
							palabra += "_ ";
						}
						
						txt1.setText(palabra);
						txt4.setText("");
						
						
					}
				});
				
				
				
				
				
				ventana.add(p1); // Añadir panel de palabra a la ventana principal

				// Configurar el panel para letras probadas e intentos
				txt2.setBackground(Color.gray);
				Label lblEspacio = new Label("     ");
				label4.setBackground(Color.gray);
				p2.add(label4);
				p2.add(txt3);
				p2.add(lblEspacio);
				p2.add(label2);
				ventana.add(p2);

				// Configurar el panel para letras probadas
				// Letras probadas de ejemplo
				p3.add(label3);
				p3.add(txt4);
				p3.add(botonJugar);
				p3.add(botonResolver);
				ventana.add(p3);
				
				txt4.setEditable(false);

				ventana.setVisible(true); // Hacer visible la ventana principal
			}
		});

		botonResolver.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				txt1.setText(palabraSecreta);

			}
		});
		// Cerrar la ventana JUEGO cuando se pulsa el botón de cerrar

		// Cerrar la ventana principal cuando se pulsa el botón de cerrar
		ventana.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ventana.dispose();
			}
		});
		ventana.addWindowListener(new WindowAdapter()
		{
		});
	}

	// Método principal para ejecutar el programa
	public static void main(String[] args) {
		new Ejercicio12Funcionalidad2();
	}
}
