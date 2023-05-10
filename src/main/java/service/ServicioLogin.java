package service;

import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

import dao.ConexionDAO;
import modelo.Usuario;

public class ServicioLogin {
	public static Usuario comprobarUsuario(String email,String password){
		return ConexionDAO.comprobarDatos(email, password);
	}
	public static int introducirUsuario(Usuario usuario) {
		return ConexionDAO.insertarUsuario(usuario);
	}
	
	public static Usuario comprobarEmail(ArrayList<Usuario> usuarios,String email) {
		Usuario user=null;
		if(!email.isEmpty()) {
			if(usuarios.size()>0) {
				for(Usuario usuario:usuarios) {
					if(email.equals(usuario.getEmail())) {
						user=usuario;
					}
				}
			}
		}
		return user;
	}
	public static boolean comprobarPasswordLogin(String passwordCod,String password) {
		StrongPasswordEncryptor encriptador = new StrongPasswordEncryptor();
		return encriptador.checkPassword(password, passwordCod);
	}
}
