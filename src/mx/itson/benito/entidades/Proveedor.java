package mx.itson.benito.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
public class Proveedor {

    // Atributos de la clase Proveedor
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="clave")
    private String clave;
    
    @Column(name="email")
    private String email;
    
    @Column(name="contacto")
    private String contacto;
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="telefono")
    private String telefono;

    // Constructor vacío
    public Proveedor() {
        
    }

    // Constructor con parámetros
    public Proveedor(String nombre, String clave, String email, String contacto, String direccion, String telefono) {
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.contacto = contacto;
        this.direccion = direccion;
        this.telefono = telefono;
    }
 // Getters y setter

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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}