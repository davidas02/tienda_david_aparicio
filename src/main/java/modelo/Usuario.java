package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jasypt.util.password.StrongPasswordEncryptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="rol_id")
	private Rol rol;
	private String email;
	private String clave;
	private String nombre;
	private String apellidos;
	private boolean baja;
	public static String encriptarPassword(String password) {
		StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
		String encryptedPassword = encriptador.encryptPassword(password);
		
		return encryptedPassword;
	}

}
