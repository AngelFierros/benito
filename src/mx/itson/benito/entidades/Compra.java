package mx.itson.benito.entidades;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String folio;
    private double iva;
    private double total;
    private Date fecha;

    @ManyToOne //se utiliza para indicar que el atributo proveedor es una relación muchos a uno con la entidad Proveedor.
    @JoinColumn(name = "proveedor_id")
    private String proveedor;
    
    @JoinColumn(name = "articulo_id")
    private String articulo;

    public Compra() {
    }

    public Compra(String folio, double iva, double total, Date fecha, String proveedor, String articulo) {
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the articulo
     */
    public String getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }
    
}