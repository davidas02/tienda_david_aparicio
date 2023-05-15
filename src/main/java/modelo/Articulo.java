package modelo;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "producto")
public class Articulo implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Blob imagen;
	private int cantidad;
    private int stock;
    private double impuesto;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    private boolean baja;
    public Articulo(){
    	
    }
    
    // Constructor
    public Articulo(int id, String nombre, String descripcion, double precio, Blob imagen,int stock,double impuesto,Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.stock=stock;
        this.impuesto=impuesto;
        this.categoria=categoria;
    }
    public int getStock() {
		return stock;
	}

	public double getImpuesto() {
		return impuesto;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

	public int getCantidad() {
		// TODO Auto-generated method stub
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		// TODO Auto-generated method stub
		this.cantidad = cantidad;
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria idCategoria) {
		this.categoria = idCategoria;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	
	
}
