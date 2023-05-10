package modelo;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Configuracion {
	private String nombre;
	private String cif;
	private String direccion;
	private int numFactura;
}
