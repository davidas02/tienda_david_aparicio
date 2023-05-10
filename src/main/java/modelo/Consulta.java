package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String titulo;
	private String consulta;
}
