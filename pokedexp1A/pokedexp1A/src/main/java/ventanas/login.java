/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import DB_pokemon.ConexionDB;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Darlin
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();

        setTitle("Login");
        setLocationRelativeTo(null); //mostrar ventana en el centro 
    }

    public void validarUsuario(){  
        PreparedStatement ps= null;
        ResultSet rs= null;
        Connection conexion=null;
        int result=0;
        //convertir a texto
        String pass= String.valueOf(TextPassword.getPassword());
        String usuario=TUsuario.getText();
        
        try
        {
            conexion=ConexionDB.conexion();
            String sql="select * from usuarios where usuario='"+usuario+"' and contrasena='"+pass+"';";
            ps=conexion.prepareStatement(sql);
            
            rs= ps.executeQuery();
    
        // ver si se encuentra el ususario
        if(rs.next()){//recorrer todos los registros
            result=1;
            if(result==1){
                //abrir el sistema
                VentanaPokedex form=new VentanaPokedex();
                form.setVisible(true);
                this.dispose();
                VentanaPokedex.nombreusuario.setText(TUsuario.getText());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error de Acceso, el usuario no esta registrado");
            Limpiar();
        }
      
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
            System.out.println(e);
        }   
    }
    private void Limpiar(){
        TUsuario.setText("");
        TextPassword.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        TUsuario = new javax.swing.JTextField();
        TextPassword = new javax.swing.JPasswordField();
        BIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\13237\\OneDrive\\Desktop\\imagenesfinalprogra\\pikachu.gif")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 170, 110));

        TUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(TUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 310, 30));

        TextPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(TextPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 310, 30));

        BIngresar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BIngresar.setText("Ingresar");
        BIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(BIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 100, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\13237\\OneDrive\\Desktop\\imagenesfinalprogra\\fondo.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIngresarActionPerformed
        // TODO add your handling code here:
        validarUsuario();
    }//GEN-LAST:event_BIngresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BIngresar;
    public javax.swing.JTextField TUsuario;
    public javax.swing.JPasswordField TextPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
