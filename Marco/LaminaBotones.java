package practicas;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LaminaBotones extends JPanel{

	public LaminaBotones(String titulo, String [] opciones) {
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), titulo));
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));//objeto y disposicion
		
		grupo=new ButtonGroup();
		
		for (int i=0; i<opciones.length;i++) {
			
			JRadioButton boton= new JRadioButton(opciones[i]);
			
			boton.setActionCommand(opciones[i]);//establece accion de comando
			
			add(boton);
			
			grupo.add(boton);
			
			boton.setSelected(i==0);
		}
		
	}
	
	public String get_seleccion() {
		//Metodo para obtener que boton esta seleccionado, la primera almacena el radio button seleccionado
		//Y la segunda devuelve el string de la accionc ButtonModel miboton=grupo.getSelection();
		//String s=miboton.getActionCommand();(metodos en largo)
		return grupo.getSelection().getActionCommand();
		
	}
	
	private ButtonGroup grupo;
}
