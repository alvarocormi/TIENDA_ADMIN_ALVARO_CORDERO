package curso.java.tienda.service;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {
    private static final String DESTINATARIO = "alvarocormin@gmail.com";
    private static final String REMITENTE = "tienda-online-curso@outlook.com";
    private static final String CONTRASENA = "CursoJava2024";
    
    public static boolean enviarCorreo(String nombre, String email, String numero, String mensajeTexto, String asunto) {

        try {
            // Propiedades de la conexión
            Properties prop = new Properties();
            // Configuración del servidor SMTP
            prop.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
            prop.setProperty("mail.smtp.starttls.enable", "true");
            prop.setProperty("mail.smtp.port", "587");
            prop.setProperty("mail.smtp.user", REMITENTE);
            prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            // Crear una sesión
            Session sesion = Session.getDefaultInstance(prop);

            // Crear un mensaje
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(REMITENTE));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(DESTINATARIO));
            mensaje.setSubject(asunto);
            mensaje.setText("Nombre: " + nombre + "\n" +
                            "Email: " + email + "\n" +
                            "Número: " + numero + "\n" +
                            "Mensaje: " + mensajeTexto);

            // Crear un objeto Transport para enviar el mensaje
            Transport t = sesion.getTransport("smtp");
            t.connect(REMITENTE, CONTRASENA);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            
            return true; // Envío exitoso

        } catch (AddressException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false; // Error al enviar el correo
    }
}
