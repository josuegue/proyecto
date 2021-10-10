/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DB_pokemon.Mysql_pokemon;
import Envio_correo.CorreoGmail;
import java.sql.SQLXML;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIOTC
 */
public class Controlador {
    Mysql_pokemon sql = new Mysql_pokemon();
    CorreoGmail envioGmail = new CorreoGmail();
    
    //se consulta el pokemon por el id
    public void consulta(String id){
        sql.consulta_pokemon(id);
    }
    
    //muestra todas las habitat de los pokemones que esta en base de datos
    public void comboBox_habitat(JComboBox comboBox){
        sql.combo_Box(comboBox, "habitat");
        
    }
    
    //muestra todos los colores de la base de datos
    public void comboBox_color(JComboBox comboBox){
        sql.combo_Box(comboBox, "color");
        
    }
    
    
    /* Este metodo sirve para desplegar los datos de los pokemones
    sql.consulta_nombtepk tiene que llevar como parametro un estring, osea el nobre del pokemon.
    y el desplegar_datospk despliega todos los datos del pokemon
    */
    public void consultar_pknombre(String nombre, JLabel nom,
            JLabel altura, JLabel ancho, JLabel especie, JLabel color, JLabel habitat, JLabel experiencia){
        sql.consulta_nombtepk(nombre);
        sql.desplegar_datospk(nom, altura, ancho, especie, color, habitat, experiencia);
    }
    
    //Este metodo esta pendiente, solo necesito crer un codigo para que me sirva, para reccorer el array list
//    public void filtrado(JComboBox comboBox1, JComboBox comboBox2, JTable tabla, DefaultTableModel modelo){
//        sql.mostra_tabla(sql.genera_string_comboBox(comboBox1), sql.genera_string_comboBox(comboBox2), modelo, tabla);
//        
//    }
    
     public void envio_correo(String destinatario, String nombre_archivo){
         /*Este metodo de enviar_Correo_reporte los unicos parametros que recibe el es correo
         destinatario y el nombre del archivo, pero el nombre debe ser asi: ejemplo.pdf, solo es 
         una supocision, pero debe de llevar cualquier nombre, solo que con terminacion .pdf
         */
         envioGmail.enviar_Correo_reporte(destinatario, nombre_archivo);
     }
}
