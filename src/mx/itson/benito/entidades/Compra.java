package mx.itson.benito.entidades;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Compra {

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

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
    private String estado;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "idProveedor")
private Proveedor proveedor;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "idArticulo")
private Articulo articulo;


    public Compra() {
    }

    public Compra(String folio, double iva, double total, Date fecha, Proveedor proveedor, Articulo articulo, String estado) {
        this.folio = folio;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.estado = estado;
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

     // Método para calcular el subtotal
    public double calcularSubtotal() {
        return cantidad * articulo.getPrecio(); // Multiplica la cantidad de artículos por el precio unitario del artículo
    }

    // Método para calcular el total (incluyendo el IVA)
    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        double ivaMonto = subtotal * (iva / 100); // Calcula el monto del IVA
        return subtotal + ivaMonto; // Suma el subtotal y el monto del IVA para obtener el total
    }
    
    
}