package mx.itson.benito.entidades;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Articulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private double precio;
    private String clave;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;
    
    //Este constructor se utiliza para crear un objeto "Articulo" sin inicializar sus atributos.

    public Articulo() {
        
    }
 
    //Este constructor se utiliza para crear un objeto "Articulo" con los atributos "nombre", "precio", "clave" y "proveedor" inicializados.
    public Articulo(String nombre, double precio, String clave, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.clave = clave;
        this.proveedor = proveedor;
    }
    
    // Métodos getter y setter
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
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public String getClave() {
        return clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}