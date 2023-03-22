package mx.itson.benito.entidades;

import java.util.Date;
import javax.persistence.*;

/**
 * La clase "Compra" representa una compra realizada por un proveedor para adquirir un artículo en una tienda en línea.
 * Esta entidad utiliza la anotación @Entity para indicar que es una entidad JPA (Java Persistence API) 
 * que se almacenará en una base de datos.
 */
@Entity
public class Compra {

    // Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Identificador único de la compra
    private String folio; // Folio de la compra
    private double iva; // Porcentaje de IVA que se aplicó a la compra
    private double total; // Total de la compra (incluyendo el IVA)
    private Date fecha; // Fecha en que se realizó la compra
    private double cantidad; // Cantidad de artículos comprados
    private String estado; // Estado de la compra (por ejemplo: "pendiente", "pagada", "cancelada")

    // Relación muchos a uno con la entidad "Proveedor"
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    // Relación uno a uno con la entidad "Articulo"
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    /**
     * Constructor por defecto.
     */
    public Compra() {
    }

    /**
     * Constructor con parámetros.
     * @param folio El folio de la compra.
     * @param iva El porcentaje de IVA que se aplicó a la compra.
     * @param total El total de la compra (incluyendo el IVA).
     * @param fecha La fecha en que se realizó la compra.
     * @param proveedor El proveedor que realizó la compra.
     * @param articulo El artículo que se compró.
     * @param estado El estado de la compra.
     */
    public Compra(String folio, double iva, double total, Date fecha, Proveedor proveedor, Articulo articulo, String estado) {
        this.folio = folio;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.estado = estado;
    }

    // Getters y Setters para los atributos de la clase

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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}