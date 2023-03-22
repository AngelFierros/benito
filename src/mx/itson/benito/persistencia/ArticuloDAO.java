package mx.itson.benito.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.benito.entidades.Articulo;
import mx.itson.benito.entidades.Proveedor;
import mx.itson.benito.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * La clase ArticuloDAO representa un objeto que se encarga de realizar
 * operaciones de persistencia en la base de datos para la entidad Articulo.
 */
public class ArticuloDAO {

    /**
     * Obtiene todos los artículos registrados en la base de datos.
     *
     * @return una lista con todos los artículos registrados
     */
    public static List<Articulo> obtenerTodos() {
        List<Articulo> articulos = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Articulo> criteriaQuery = session.getCriteriaBuilder().createQuery(Articulo.class);
            criteriaQuery.from(Articulo.class);

            articulos = session.createQuery(criteriaQuery).getResultList();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulos;
    }

    /**
     * Guarda un nuevo artículo en la base de datos.
     *
     * @param nombre el nombre del artículo a guardar
     * @param precio el precio del artículo a guardar
     * @param clave la clave del artículo a guardar
     * @param proveedor el proveedor del artículo a guardar
     * @return true si se pudo guardar el artículo correctamente, false en caso
     * contrario
     */
    public static boolean guardar(String nombre, Double precio, String clave, Proveedor proveedor) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Articulo a = new Articulo();
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setClave(clave);
            a.setProveedor(proveedor);

            session.save(a);

            session.getTransaction().commit();

            resultado = a.getId() != 0;
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

        return resultado;
    }

    /**
     * Edita un artículo existente en la base de datos.
     *
     * @param id el identificador del artículo a editar
     * @param nombre el nuevo nombre del artículo
     * @param precio el nuevo precio del artículo
     * @param clave la nueva clave del artículo
     * @param proveedor el nuevo proveedor del artículo
     * @return true si se pudo editar el artículo correctamente, false en caso
     * contrario
     */
    public static boolean editar(int id, String nombre, Double precio, String clave, Proveedor proveedor) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo articulo = obtenerPorId(id);
            if (articulo != null) {
                articulo.setNombre(nombre);
                articulo.setPrecio(precio);
                articulo.setClave(clave);
                articulo.setProveedor(proveedor);

                session.saveOrUpdate(articulo);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Obtiene un artículo de la base de datos mediante su identificador.
     *
     * @param id el identificador del artículo a obtener
     * @return el objeto Articulo correspondiente al identificador dado, o null
     * si no existe
     */
    public static Articulo obtenerPorId(int id) {
        Articulo articulo = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulo = session.get(Articulo.class, id);

        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return articulo;
    }

    /**
     *
     * Elimina un artículo existente en la base de datos.
     *
     * @param id el identificador del artículo a eliminar
     *
     * @return true si se pudo eliminar el artículo correctamente, false en caso
     * contrario
     */
    public static boolean eliminar(int id) {
        boolean resultado = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Articulo articulo = obtenerPorId(id);
            if (articulo != null) {
                session.delete(articulo);
                session.getTransaction().commit();
                resultado = true;
            }
        } catch (HibernateException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
