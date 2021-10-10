/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Envio_correo;


//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author USUARIOTC
 */
public class CorreoGmail {
    /* DIRECTORIO:
    directorio donde se encuentra el archivo, usted pongale el direcotorio donde tiene 
    guardado los archivos. 
    */
    private static final String DIRECTORIO="C:\\Users\\13237\\OneDrive\\Desktop\\pokedexp1A\\pokedexp1A\\src\\main\\java\\Reportes\\";
    private static final String CENTRO_POKEMON="centropokemon16@gmail.com";
    private static final String PASSWORD="pokemon2442$$";
    private static final String MENSAJE="¡Hola!"
            + "\nHemos visto que has realizado un reporte en la aplicacion."
            + " Para que tengas el reporte a tu dispocion, decidimos mandartelo al correo. "
            + "Asi podras tenerlo en formato (pdf).";
    private static final String REPORTE ="Reporte Pokemon";
    
    /* Metodo que envia el correo al usuario con el pdf.
    modificar el string DIRECTORIO, por el string de donde estan guardados los pdfs.
    */
    public void enviar_Correo_reporte(String destinatario, String nom_arch){
       
        /*Este string concatena el directorio y el nombre del archivo, 
        pero el archivo debe llevar terminacion lo que esta dentro del parentesis (.pdf)
        
        */
        String directorio_archivo=DIRECTORIO+nom_arch;
        Properties propiedades= new Properties();
        propiedades.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");   
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session secion = Session.getDefaultInstance(propiedades);
        secion.setDebug(true);
        
        MimeMessage mail = new MimeMessage(secion);
        BodyPart bodypart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        try {
            mail.setFrom(new InternetAddress(CENTRO_POKEMON));
            mail.addRecipients(Message.RecipientType.TO,destinatario);
            mail.setSubject(REPORTE);
            
            bodypart.setText(MENSAJE);
            
            multipart.addBodyPart(bodypart);
            
            bodypart = new MimeBodyPart();
            DataSource source = new FileDataSource(directorio_archivo);
            bodypart.setDataHandler(new DataHandler(source));
            bodypart.setFileName(directorio_archivo);
            multipart.addBodyPart(bodypart);
            mail.setContent(multipart);

            Transport transporte = secion.getTransport("smtp");
            transporte.connect("smtp.gmail.com",CENTRO_POKEMON, PASSWORD);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
//            String hola=destinatario+asunto+mensaje;
            System.out.println("Correo enviado a "+destinatario);
        
        } catch (AddressException ex) {
            System.out.println("error "+ex);
            ex.printStackTrace(System.out);
        } catch (MessagingException ex) {
            System.out.println("error de envio de mensaje"+ex);
            ex.printStackTrace(System.out);
        }
        
    }

}
