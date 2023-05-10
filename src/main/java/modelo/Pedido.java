package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
	@Column(name="id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPedido;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	private String metodoPago;
	@Column(name="num_factura")
	private int numFactura;
	private double total;
	private Timestamp fecha;
	@ManyToOne
	@JoinColumn(name = "estado")
	private Estado estado;
}
