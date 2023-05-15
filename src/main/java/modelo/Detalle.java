package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Detalle implements Serializable{
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Articulo producto;
	private int unidades;
	@Column(name = "preciounidad")
	private double precioPorUnidad;
	@Column(name="impuesto")
	private double impuestos;
	private double total;
	public double getTotal() {
		return precioPorUnidad*unidades;
	}
}
