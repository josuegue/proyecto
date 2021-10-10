/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_pokemon;

import Datos.ClassPokemon;
import com.mysql.cj.xdevapi.Result;

import java.awt.List;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIOTC
 */
public class Mysql_pokemon {
    private String busqueda;
    private static final String CONSULTA="select * from pokemon where id=?;";
    private static final String CONSULTA_NOMBRE="select * from pokemon where name=?";
    private static final String CONSUTAL_FILTROS="select * from pokemon where habitat=? and color=?;";
    private static final String CONSULTA_FAVS="select * from tb_favoritos";
    private static final  ClassPokemon obj_pokemon = new ClassPokemon();
    private boolean encontrado = false;
    
    ArrayList<ClassPokemon> arrayList= new ArrayList();
//    private static final String CONSULTA_HABITAT="select habitat from pokemon group by habitat;";
    
    /*metodo que me consulta si un pokemon existe o no por el id 
    
    */
    public String consulta_pokemon(String id){
        ClassPokemon obj_pokemon = null;
        PreparedStatement stm= null;
        ResultSet result = null;
        Connection conexion=null;
        String resultado ="";
        try {
            obj_pokemon=new ClassPokemon();
            conexion=ConexionDB.conexion();
            stm=conexion.prepareStatement(CONSULTA);
            obj_pokemon.setId(id);
            stm.setString(1, obj_pokemon.getId());
            result=stm.executeQuery();
            if (result.next()){
                resultado =result.getString(2);
                
            }
            else {
                resultado ="Pokemon no encontrado";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql_pokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConexionDB.close(result, stm, conexion);
        }
        return  resultado;
        
    }
    /*metodo que me retorna una lista de la columna que yo le indique, 
    agrupando los campos iguales. 
    */
    private ArrayList busqueda_Datos(String bqda){
        String CONSULTA_HABITAT="select "+bqda+" from pokemon group by "+bqda+";";
        ArrayList array_habitat = new ArrayList();
        PreparedStatement stm= null;
        ResultSet result = null;
        Connection conexion=null;
        ClassPokemon obj_pokemon = null;
        try {
            conexion=ConexionDB.conexion();
            stm=conexion.prepareStatement(CONSULTA_HABITAT);
            result=stm.executeQuery();
            while (result.next()) {    

                String habitat=result.getString(bqda);
                array_habitat.add(habitat);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ConexionDB.close(result, stm, conexion);
            return array_habitat;
        }
    }
    
    /*metodo que despliega en pantalla todas las opciones que hay en la base de datos.
     Opciones que se veran en el comboBox*/
    public void combo_Box(JComboBox comboBox,String busquedaDato){
        ArrayList habitat = busqueda_Datos(busquedaDato);
        for(Object obj: habitat){
            comboBox.addItem(obj);
        }
    }
    /*metodo que genera el string al momento de seleccionar un item de un combobox*/
    public String genera_string_comboBox(JComboBox comboBox){
        return comboBox.getSelectedItem().toString();
    }
    
        
    public void consulta_nombtepk(String nombre){
        PreparedStatement stm= null;
        ResultSet result = null;
        Connection conexion=null;
        try {
            
            conexion=ConexionDB.conexion();
            stm=conexion.prepareStatement(CONSULTA_NOMBRE);
            obj_pokemon.setName(nombre);
            stm.setString(1, obj_pokemon.getName());
            result=stm.executeQuery();
            if (result.next()){
                encontrado=true;
                obj_pokemon.setName(result.getString(2));
                obj_pokemon.setHeight(result.getString(10));
                obj_pokemon.setWight(result.getString(11));
                obj_pokemon.setSpecie(result.getString(12));
                obj_pokemon.setColor(result.getString(13));
                obj_pokemon.setHabitat(result.getString(15));
                obj_pokemon.setBase_experiencie(result.getString(18));
            
            }
            else
            {
                encontrado=false;
                obj_pokemon.setPokemon_nodisponible("No hay datos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mysql_pokemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConexionDB.close(result, stm, conexion);
        }
    } 
        
    public void desplegar_datospk(JLabel nombre, JLabel altura, JLabel ancho, JLabel especie,
            JLabel color, JLabel habitat, JLabel experiencia){
        String sin_datos="No hay datos";
        if(encontrado){
        nombre.setText(obj_pokemon.getName());
        altura.setText(obj_pokemon.getHeight());
        ancho.setText(obj_pokemon.getWight());
        especie.setText(obj_pokemon.getSpecie());
        color.setText(obj_pokemon.getColor());
        habitat.setText(obj_pokemon.getHabitat());
        experiencia.setText(obj_pokemon.getBase_experiencie());

        }
        else {
        nombre.setText(obj_pokemon.getPokemon_nodisponible());
        altura.setText(obj_pokemon.getPokemon_nodisponible());
        ancho.setText(obj_pokemon.getPokemon_nodisponible());
        especie.setText(obj_pokemon.getPokemon_nodisponible());
        color.setText(obj_pokemon.getPokemon_nodisponible());
        habitat.setText(obj_pokemon.getPokemon_nodisponible());
        experiencia.setText(obj_pokemon.getPokemon_nodisponible());

        }      
    }
     
            //Este metodo me hace la consulta y me retorna un array list, de los
         //pokemones encontrados
//    private ArrayList<ClassPokemon> filtrado(){
//        ArrayList arrayList = new ArrayList();
//        ClassPokemon pokemon = null;
//        PreparedStatement stm = null;
//        ResultSet result = null;
//        Connection conexion = null;
//        try
//        {
//            conexion=ConexionDB.conexion();
//            stm= conexion.prepareStatement(CONSULTA_FAVS);
//            obj_pokemon. (habitat)
//            stm.setString(1, obj_pokemon.getHabitat());
//            stm.setString(2, obj_pokemon.getColor());
//            result = stm.executeQuery();
//            if (!result.isBeforeFirst()){
//                encontrado = false;
//                obj_pokemon.setPokemon_nodisponible("No hay datos");
//                arrayList.add(obj_pokemon.getPokemon_nodisponible());
//            }else{
//                while(result.next()){
//                    obj_pokemon.setId(result.getString(1));
//                     obj_pokemon.setName(result.getString(2));
//                     obj_pokemon.setHeight(result.getString(10));
//                     obj_pokemon.setWight(result.getString(11));
//                     obj_pokemon.setSpecie(result.getString(12));
//                     obj_pokemon.setColor(result.getString(13));
//                     obj_pokemon.setHabitat(result.getString(15));
//                     obj_pokemon.setBase_experiencie(result.getString(18));
//                     String id = obj_pokemon.getId();
//                     String name = obj_pokemon.getName();
//                     String height = obj_pokemon.getHeight();
//                     String wight = obj_pokemon.getWight();
//                     String specie = obj_pokemon.getSpecie();
//                     String col = obj_pokemon.getColor();
//                     String hab = obj_pokemon.getHabitat();
//                     String expe = obj_pokemon.getBase_experiencie();
//                     pokemon = new ClassPokemon(id, name, height, wight, specie, hab, col, expe);
//                     arrayList.add(pokemon);
//                     pokemon = new ClassPokemon();
//                }
//                }
//                }catch(SQLException ex){
//                    Logger.getLogger(Mysql_pokemon.class.getName()).log(Level.SEVERE, null, ex);
//                }finally{
//            ConexionDB.close(result, stm, conexion);
//
//        }
//        return arrayList;
//    }
        
    /*Este metodo me muestra la tabla, basicamente se le tiene que agregar:
 String habitat = genera_string_comboBox(combohabitat);
 String color = genera_string_comboBox(combocolor);
 Quedando asi
 mostra_tabla(habitat, color, DefaultTableModel modelo, JTable tabla)

 Por ultimo crea una variable de Tipo DefaultTableModelo, y la variable de tipo Jtable
 Que es donde se van a desplegar los datos.

 */
         
//    public void mostra_tabla(String habitat, String color, DefaultTableModel modelo, JTable tabla){
//         //mando a llamar a mi metodo que me retorna array
//         ArrayList lista = filtrado(habitat, color);
//         //Agrego las columnas de la tabla
//         
//         modelo.addColumn("ID");
//         modelo.addColumn("NOMBRE");
//         modelo.addColumn("ALTURA");
//         //con el metodo size() obtengo el tamaño de las filas de mi array
//         modelo.setRowCount(lista.size());
//         for (int i = 0; i < lista.size(); i++) {
//         //con este metodo de la siguiente linea, puedo obtener los datos de mi arraylist
//         //osea que los puedo manipular con los get de mi clase pokemon
//         ClassPokemon pk = (ClassPokemon) lista.get(i);
//
//         //El setValueAt(datoenlafila,fila,columna) asi se asignan los datos
//         modelo.setValueAt(pk.getId(), i, 0);
//         modelo.setValueAt(pk.getName(), i, 1);
//         modelo.setValueAt(pk.getHeight(), i, 2);
//         modelo.setValueAt(pk.getWight(), i, 3);
//         modelo.setValueAt(pk.getSpecie(), i, 4);
//         modelo.setValueAt(pk.getColor(), i, 5);
//         modelo.setValueAt(pk.getHabitat(), i, 6);
//         modelo.setValueAt(pk.getBase_experiencie(), i, 7);
//         }
//         //por ultimo mostramos la tabla
//         tabla.setModel(modelo);
//    
//    }
     
}
