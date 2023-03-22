package mx.itson.benito.utilerias;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * La clase `HibernateUtil` es una utilidad para obtener una instancia de la
 * fábrica de sesiones de Hibernate.
 *
 * Esta clase utiliza una instancia de `SessionFactory` para crear y obtener
 * sesiones de Hibernate, lo que permite interactuar con la base de datos
 * utilizando objetos de entidades de Hibernate.
 *
 * También se proporcionan métodos para establecer y obtener la instancia de la
 * fábrica de sesiones y el registro de servicios de Hibernate, lo que permite
 * personalizar la configuración de Hibernate según sea necesario.
 *
 * La configuración de Hibernate se toma de un archivo llamado
 * `hibernate.cfg.xml`, que se espera que se encuentre en la ruta de acceso
 * predeterminada del proyecto. Si se requiere una configuración personalizada,
 * puede crearse un archivo de configuración diferente y especificarlo mediante
 * el método `configure(String)`.
 *
 * Si se produce un error durante la creación de la fábrica de sesiones de
 * Hibernate, se registra un mensaje de error en la consola indicando que no se
 * pudo crear la sesión de Hibernate y se muestra el mensaje de excepción.
 */
public class HibernateUtil {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            factory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            System.out.println("No se pudo crear la sesión de Hibernate debido al error: " + ex);
        }
    }

    /**
     * Obtiene la instancia de la fábrica de sesiones de Hibernate.
     *
     * @return la instancia de la fábrica de sesiones de Hibernate
     */
    public static SessionFactory getSessionFactory() {
        return factory;
    }

    /**
     * Establece la instancia de la fábrica de sesiones de Hibernate.
     *
     * @param factory la instancia de la fábrica de sesiones de Hibernate
     */
    public static void setSessionFactory(SessionFactory factory) {
        HibernateUtil.factory = factory;
    }

    /**
     * Obtiene el registro de servicios de Hibernate.
     *
     * @return el registro de servicios de Hibernate
     */
    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    /**
     * Establece el registro de servicios de Hibernate.
     *
     * @param serviceRegistry el registro de servicios de Hibernate
     */
    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        HibernateUtil.serviceRegistry = serviceRegistry;
    }
}
