package enviando.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {
	
	private String userName = "alessandro.devpedroso@gmail.com";
	private String senha = "aqui vai o token"; /*gerar o token nas configurações do gmail em ativação de verificação de duas etapas*/
	
	private String _listaDestinatarios = "";
	private String _nomeRemetente = "";
	private String _assuntoEmail = "";
	private String _textoEmail = "";
	
	public ObjetoEnviaEmail(String listaDestinatario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		
		this._listaDestinatarios = listaDestinatario;
		this._nomeRemetente = nomeRemetente;
		this._assuntoEmail = assuntoEmail;
		this._textoEmail = textoEmail;
	}
	
	public void EnviarEmail(boolean envioHtml) throws Exception {
		

		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true");/*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail google*/
		properties.put("mail.smtp.port", "465");/*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao smtp*/
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		/*Estabelecendo os destinatarios*/
		
		/*Lista de e-mail para o destinatario*/
		Address[] toUser = InternetAddress.parse(_listaDestinatarios);		
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, _nomeRemetente)); /*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject(_assuntoEmail); /*Assunto do e-mail*/
		
		if(envioHtml) {
			message.setContent(_textoEmail, "text/html; charset=utf-8");
		}else {
			
			message.setText(_textoEmail);/*Texto corpo do e-mail*/
		}
		
		
		Transport.send(message);
	}
	
	public void EnviarEmailAnexo(boolean envioHtml) throws Exception {
			
	
			Properties properties = new Properties();
			
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/*Autorização*/
			properties.put("mail.smtp.starttls", "true");/*Autenticação*/
			properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail google*/
			properties.put("mail.smtp.port", "465");/*Porta do servidor*/
			properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao smtp*/
			
			Session session = Session.getInstance(properties, new Authenticator() {
				
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(userName, senha);
				}
			});
			
			/*Estabelecendo os destinatarios*/
			
			/*Lista de e-mail para o destinatario*/
			Address[] toUser = InternetAddress.parse(_listaDestinatarios);		
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, _nomeRemetente)); /*Quem está enviando*/
			message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
			message.setSubject(_assuntoEmail); /*Assunto do e-mail*/
			
			/*Parte 1 do e-mail que é texto e a descricao do e-mail*/
			MimeBodyPart corpoEmail = new MimeBodyPart();
			
			if(envioHtml) {
				corpoEmail.setContent(_textoEmail, "text/html; charset=utf-8");
			}else {
				
				corpoEmail.setText(_textoEmail);/*Texto corpo do e-mail*/
			}
			
			/*Parte 2 do e-mail que são os anexo em pdf*/
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			/*Onde é passado o simuladorPDF você passa o seu arquivo gravado no banco de dados*/
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladorDePDF(), "application/pdf")));
			anexoEmail.setFileName("anexoemail.pdf");
			
			/*Junta as duas partes do email*/
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(corpoEmail);
			multipart.addBodyPart(anexoEmail);
			
			message.setContent(multipart);
			
			
			
			Transport.send(message);
		}
	
	public void EnviarEmailListaAnexo(boolean envioHtml) throws Exception {
		
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true");/*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com");/*Servidor gmail google*/
		properties.put("mail.smtp.port", "465");/*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Expecifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexão ao smtp*/
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		/*Estabelecendo os destinatarios*/
		
		/*Lista de e-mail para o destinatario*/
		Address[] toUser = InternetAddress.parse(_listaDestinatarios);		
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, _nomeRemetente)); /*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject(_assuntoEmail); /*Assunto do e-mail*/
		
		
		/*Parte 1 do e-mail que é texto e a descricao do e-mail*/
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if(envioHtml) {
			corpoEmail.setContent(_textoEmail, "text/html; charset=utf-8");
		}else {
			
			corpoEmail.setText(_textoEmail);/*Texto corpo do e-mail*/
		}
		
		List<FileInputStream> listaArquivos = new ArrayList<FileInputStream>();
		listaArquivos.add(simuladorDePDF());/*certificado*/
		listaArquivos.add(simuladorDePDF());/*nota fiscal*/
		listaArquivos.add(simuladorDePDF());/*documento de texto*/
		listaArquivos.add(simuladorDePDF());/*imagem*/
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : listaArquivos) {
			
			/*Parte 2 do e-mail que são os anexo em pdf*/
			MimeBodyPart anexoEmail = new MimeBodyPart();
			
			/*Onde é passado o simuladorPDF você passa o seu arquivo gravado no banco de dados*/
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoemail"+index+".pdf");
			
			/*Junta as duas partes do email*/
			multipart.addBodyPart(anexoEmail);
			
			index++;
		}
		
		message.setContent(multipart);
		
		Transport.send(message);
	}
	
	
	/*Esse método simula o PDF ou qualquer arquivo que possa ser enviado por anexo no email
	 * você pode pegar o arquivo do seu banco de dados base64, byte[], stream de arquivos.
	 * pode estar e um banco de dados, ou em uma pasta
	 * Retorna um PDF em Branco com o texto do paragrafo de exemplo.
	 * */
	
	private FileInputStream simuladorDePDF() throws Exception {
		
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do pdf"));
		document.close();
		
		return new FileInputStream(file);
	}
	
}
