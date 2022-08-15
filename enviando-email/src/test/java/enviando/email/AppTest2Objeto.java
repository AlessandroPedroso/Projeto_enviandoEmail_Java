package enviando.email;

import org.junit.Test;

public class AppTest2Objeto {

	@Test
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá,<br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo o acesso ao curso de Java <br/><br/>");
		stringBuilderTextoEmail.append("para ter acesso clique no botão abaixo.<br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login:</b> alessandro1234 <br/>");
		stringBuilderTextoEmail.append("<b>Senha:</b> 12356487987 <br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://projetojavaweb.com/certificado-aluno/login\" style=\"color:#2525a7;padding:14px 25px; text-alig:center; text-decoration:none; display:inline-block; border-radius:30px; font-size:20px; font-family:courier; border:3px solid green; background-color:#99DA39;\">Acessar portal do aluno</a> <br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size:8px;\">Ass: Alessandro Schuquel Pedroso</span>");
		
		
		ObjetoEnviaEmail enviaEmail =  new ObjetoEnviaEmail("alessandropedrosoti@gmail.com,alessandro.devpedroso@gmail.com", "Alessandro Schuquel Pedroso", "Testando e-mail com java Anexo", stringBuilderTextoEmail.toString());
		
		enviaEmail.EnviarEmail(true);
		
		
		Thread.sleep(2000);
		
		System.out.println("teste");
	}
}
