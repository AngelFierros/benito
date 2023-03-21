package mx.itson.benito.entidades;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Compra {

    /**
     * @return the cantidad
     */
    public double getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folio;
    private double iva;
    private double total;
    private Date fecha;
    private double cantidad;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "idProveedor")
private Proveedor proveedor;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "idArticulo")
private Articulo articulo;


    public Compra() {
    }

    public Compra(String folio, double iva, double total, Date fecha, Proveedor proveedor, Articulo articulo) {
        this.folio = folio;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.articulo = articulo;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

   
    
    
}