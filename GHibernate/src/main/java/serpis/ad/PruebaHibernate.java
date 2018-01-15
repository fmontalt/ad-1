package serpis.ad;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.logging.LoggerProvider;

public class PruebaHibernate {

	private static EntityManagerFactory entityManagerFactory; 
 
	public static void main(String[] args) {
		//Logger.getLogger("org.hibernate").setLevel(Level.ALL);

		//System.setProperty("java.util.logging.config.file", "/home/luimen/ad/GHibernate/bin/META-INF/log4j.properties");
		
		
		entityManagerFactory = 
			Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
		
		//showAll();
		
		//modify(2L);
		//remove(19L);
		
		//newCategoria();
		
		showAll();
		
		entityManagerFactory.close();
	}
	
	
	private static void newCategoria() {
		System.out.println("creando categoría nueva");
		Categoria categoria = new Categoria(); 
		categoria.setNombre("nuevo " + new Date()); //TODO cambiar Date por Calendar
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("Creando: " + categoria);
		entityManager.persist(categoria);
		System.out.println("Creada: " + categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void modify(long id) {
		System.out.println("modificando categoría " + id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre("modificado " + new Date()); //TODO cambiar Date por Calendar
		entityManager.persist(categoria);
		entityManager.getTransaction().commit();
	}

	private static void remove(long id) {
		System.out.println("eliminando categoría " + id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		//Categoria categoria = entityManager.find(Categoria.class, id);
		Categoria categoria = entityManager.getReference(Categoria.class, id);
		entityManager.remove(categoria);
		entityManager.getTransaction().commit();
	}

	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager
				.createQuery("from Categoria order by id", Categoria.class)
				.getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}

}
