package practicas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MarcoDialogos extends JFrame{
	
	public MarcoDialogos(){
		
		setTitle("Prueba de Dialogos");
		
		setBounds(500,200,600,450);
		
		JPanel cuadricula=new JPanel();
		
		cuadricula.setLayout(new GridLayout(2,3));
		
		String primero []={"Mensaje", "Confirmar","Opción","Entrada"};
		
		laminatipo=new LaminaBotones("Tipo",primero);
		
		
		laminamensaje=new LaminaBotones("Tipo de mensaje",new String [] {
				"ERROR_MESSAGE",
				"INFORMATION_MESSAGE",
				"WARNING_MESSAGE",
				"QUESTION_MESSAGE",
				"PLAIN_MESSAGE"
		});
		
		mensaje=new LaminaBotones("Mensaje",new String [] {
				"Cadena",
				"Icono",
				"Componente",
				"Otros",
				"Object[]"
		});
		
		confirmar=new LaminaBotones("Confirmar",new String [] {
				"DEFAULT_OPTION",
				"YES_NO_OPTION",
				"YES_NO_CANCEL_OPTION",
				"OK	_CANCEL_OPTION"
		});
		
		opcion=new LaminaBotones("Opción",new String [] {
				"String[]",
				"Icon[]",
				"Object[]"
		});
		
		entrada=new LaminaBotones("Entrada",new String [] {
				"Campo de texto",
				"Combo"
		});
		setLayout(new BorderLayout());
		
		cuadricula.add(laminatipo);
		
		cuadricula.add(laminamensaje);
		
		cuadricula.add(mensaje);
		
		cuadricula.add(confirmar);
		
		cuadricula.add(opcion);
		
		cuadricula.add(entrada);
		
		add(cuadricula);
		
		//Lamina 2
		
		JPanel laminamostrar= new JPanel();
		
		JButton mostrar= new JButton("Mostrar");
		
		mostrar.addActionListener(new AccionMostar());
		
		laminamostrar.add(mostrar);
		
		add(laminamostrar,BorderLayout.SOUTH);
		
		add(cuadricula,BorderLayout.CENTER);
	}
	//Proporciona el mensaje de la 3 lamina
	public Object get_mensaje() {
		
		String s=mensaje.get_seleccion();
		
		if(s.equals("Cadena")) 
			
			return cadena;
		
		else if (s.equals("Icono"))
			
			return Icono;
		
		else if (s.equals("Otros"))
			
			return Objecto;
		
		else if (s.equals("Componente"))
			
			return Componente;
		
		else if (s.equals("Object[]")) 
			
			return new Object[]{cadena,Icono,Componente,Objecto};
		
		 else
			
			return null;
		
	}
	//Metodo que cambia el icono del mensaje y Metodo que sirve para dar opciones a ventanas confirmar y opcion
	public int get_type(LaminaBotones lamina) {
		
		String s= lamina.get_seleccion();
		
		if(s.equals("ERROR_MESSAGE") || s.equals("YES_NO_OPTION"))
			
			return 0;
			
		if(s.equals("INFORMATION_MESSAGE")  || s.equals("YES_NO_CANCEL_OPTION"))
			
			return 1;
			
		if(s.equals("WARNING_MESSAGE") || s.equals("OK	_CANCEL_OPTION"))
			
			return 2;
				
		if(s.equals("QUESTION_MESSAGE"))
			
			return 3;
					
		if(s.equals("PLAIN_MESSAGE") || s.equals("DEFAULT_OPTION"))
			
			return -1;
		
		else
			
			return 0;	
	}
	//metodo que te da las opciones a la lamina opcion
	public Object[] get_opciones(LaminaBotones lamina) {
		
		String s= lamina.get_seleccion();
		
		if(s.equals("String[]"))
			
			return  new Object[]{"Amarillo","Azul","Rojo"};
		
		else if(s.equals("Icon[]")) 
			
			return new Object[] {new ImageIcon("src/practicas/azul.gif"),new ImageIcon("src/practicas/rojo.gif"),
					new ImageIcon("src/practicas/amarillo.gif")};
			
		else if(s.equals("Object[]")) 
			
			return new Object[] {cadena,Icono,Objecto};
			
		else 
			
			return null;
	}
	//clase interna que gestiona eventos
	private class AccionMostar implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			if(laminatipo.get_seleccion().equals("Mensaje")) {
				
				JOptionPane.showMessageDialog(MarcoDialogos.this,get_mensaje(),"Titulo",get_type(laminamensaje));
				
			} else if(laminatipo.get_seleccion().equals("Confirmar")) {
				
				JOptionPane.showConfirmDialog(MarcoDialogos.this,get_mensaje(),"Titulo",get_type(confirmar)
						,get_type(laminamensaje));
				
			}else if(laminatipo.get_seleccion().equals("Entrada")) {
				
					if(entrada.get_seleccion().equals("Campo de texto")) {
						JOptionPane.showInputDialog(MarcoDialogos.this,get_mensaje(),"Titulo",
								get_type(laminamensaje));
					}
						
					else {
						
						JOptionPane.showInputDialog(MarcoDialogos.this, get_mensaje(), "Titulo"
								, get_type(laminamensaje), null,new String[] {"Amarillo","Azul","Rojo"}, 
									"Amarillo");
						
					}
			}else if(laminatipo.get_seleccion().equals("Opción")) {
				
				JOptionPane.showOptionDialog(MarcoDialogos.this, get_mensaje(), "Titulo", get_type(confirmar)
						, get_type(laminamensaje), null,get_opciones(opcion), null);
				
			}
			
		}
		
	}
	private LaminaBotones laminatipo,laminamensaje,mensaje,confirmar,opcion,entrada;
	
	private String cadena="Mensaje";
	
	private Icon Icono=new ImageIcon("src/practicas/azul.gif");
	
	private Object Objecto=new Date();
	
	private Component Componente= new LaminaEjemplo();

}

class LaminaEjemplo extends JPanel{
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D )g;
		
		Rectangle2D rectangulo= new Rectangle2D.Double(0,0,getWidth(),getHeight());
		
		g2.setPaint(Color.YELLOW);
		
		g2.fill(rectangulo);
		
	}
}