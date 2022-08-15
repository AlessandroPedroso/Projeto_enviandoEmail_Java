package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AppTest {
	
	private String userName = "alessandro.devpedroso@gmail.com";
	private String senha = "aqui vai o token"; /*gerar o token nas configurações do gmail em ativação de verificação de duas etapas*/
	private String nomeCliente = "Alessandro Schuquel Pedroso";
	
	@org.junit.Test
	public void testeEmail(){
		
		try {
		/*Olhe as configurações smtp do seu email*/
		
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
		Address[] toUser = InternetAddress.parse("alessandropedrosoti@gmail.com");		
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeCliente)); /*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Email de destino*/
		message.setSubject("Chegou e-mail enviado com java"); /*Assunto do e-mail*/
		message.setText("Olá você acaba de receber um e-mail do " + userName + " enviado com Java do curso Formação Java web do Alex");/*Texto corpo do e-mail*/
		
		Transport.send(message);
		
		
		/*Caso o email não esteja sendo enviado então
		 * coloque um tempo de espera mas isso só pode ser usado para teste*/
		Thread.sleep(10000);
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
