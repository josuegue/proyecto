/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesPdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIOTC
 */
public class Genera_pdf {
    private static final Document documento = new Document();
    private FileOutputStream archivo;
    private  final String ruta = "C:\\Users\\13237\\OneDrive\\Desktop\\pokedexp1A\\pokedexp1A\\src\\main\\java\\Reportes\\";
    // me genera el archivo pdf 
    public void genera_pdf() {
        try {
            archivo=new FileOutputStream(ruta+nombre_archivo()+".pdf");
//            archivo=FileNotFoundException(getClass().getResource("/Reportes/"+nombre_documento+".pdf"));
            
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.add(Chunk.NEWLINE);
            Paragraph texto = new Paragraph("Hola ya generaste un pdf.... \n ah perroooooo");
            texto.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(texto);
            documento.close();
            System.out.println("pdf generado");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (DocumentException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
    private String nombre_archivo(){
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String resutado;
        String nombre=dtf2.format(LocalDateTime.now());
        String [] datos = nombre.split("/| |:");
        resutado= "Reporte"+"_"+datos[0]+"-"+datos[1]+"-"+datos[2]+"_"+datos[3]+"."+datos[4]+"."+datos[5];
        return resutado;
    }
    
    
}
