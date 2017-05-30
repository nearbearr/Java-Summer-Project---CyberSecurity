/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cybersecurity.email;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Administrator
 */
public class SMTPAuthenticator extends Authenticator{
    private String username,password;
	public SMTPAuthenticator(String username,String password){
		this.username=username;
		this.password=password;
		
	}
    
	public javax.mail.PasswordAuthentication getPasswordAuthentication(){
		
		return new PasswordAuthentication(this.username,this.password);
	}

}
