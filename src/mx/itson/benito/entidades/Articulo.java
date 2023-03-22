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

/**
 * La clase "Articulo" representa un producto en una tienda en línea.
 * Esta entidad utiliza la anotación @Entity para indicar que es una entidad JPA (Java Persistence API) 
 * que se almacenará en una base de datos.
 */
@Entity
@Table(name = "articulo") // especifica el nombre de la tabla en la base de datos
public class Articulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // especifica el nombre de la columna en la base de datos
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "precio")
    private double precio;
    
    @Column(name = "clave")
    private String clave;
    
    /**
     * La relación entre Articulo y Proveedor es Many-to-One, es decir, muchos Articulos pueden tener un mismo Proveedor.
     * La propiedad cascade indica que cuando se realizan operaciones de persistencia en la entidad "Articulo", 
     * también se deben realizar en la entidad "Proveedor" relacionada.
     */
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "idProveedor") // especifica el nombre de la columna que se utilizará como clave foránea
    private Proveedor proveedor;
    
    // Este constructor se utiliza para crear un objeto "Articulo" sin inicializar sus atributos.
    public Articulo() {
        
    }
 
    /**
     * Este constructor se utiliza para crear un objeto "Articulo" con los atributos "nombre", "precio", "clave" y "proveedor" inicializados.
     * @param nombre el nombre del artículo
     * @param precio el precio del artículo
     * @param clave la clave del artículo
     * @param proveedor el proveedor del artículo
     */
    public Articulo(String nombre, double precio, String clave, Proveedor proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.clave = clave;
        this.proveedor = proveedor;
    }
    
    // Métodos getter y setter
    
    /**
     * @return el id del artículo
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id el nuevo id del artículo
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return el nombre del artículo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @param nombre el nuevo nombre del artículo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return el precio del artículo
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * @param precio el nuevo precio del artículo
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * @return la clave del artículo
     */
    public String getClave() {
        return clave;
    }
    
    /**
     * @param clave la nueva clave del artículo
    */
    public void setClave(String clave) {
    this.clave = clave;
    }

    public Proveedor getProveedor() {
    return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
    }
 
    //Este método se utiliza para mostrar el nombre del artículo en lugar de su dirección de memoria.
    @Override
    public String toString(){
    return this.nombre;
    }
}