package modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "configuracion")
public class Configuracion {
	private String nombre;
	@Id
	private String cif;
	private String direccion;
	@Column(name = "factura_id")
	private int numFactura;
}
