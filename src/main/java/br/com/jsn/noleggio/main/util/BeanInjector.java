package br.com.jsn.noleggio.main.util;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Renan Baggio
 * @see <a href="http://insanitydesign.com/wp/2013/10/22/cdi-diy-manual-injection/">Referência externa</a>
 */
public class BeanInjector implements Serializable {
	private static final long serialVersionUID = 4254206298495289747L;

	/**
	 * Retorna o resource BeanManager (gerenciador do CDI)
	 * @return BeanManager
	 */
	private static BeanManager getBeanManager() {
		try {
			return (BeanManager) new InitialContext().lookup("java:comp/BeanManager");

		} catch (NamingException e) {
			return null;
		}
	}

	/**
	 * Retorna uma instância da classe recebida
	 * @param classe Class da classe do objeto desejado
	 * @return Instância da classe solicitada
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> classe) {
		BeanManager beanManager = getBeanManager();

		if (beanManager != null) {
			Set<Bean<?>> beans = beanManager.getBeans(classe);

			if (beans != null && !beans.isEmpty()) {
				Bean<T> bean = (Bean<T>) beanManager.getBeans(classe).iterator().next();
				CreationalContext<T> ctx = beanManager.createCreationalContext(bean);
				return (T) beanManager.getReference(bean, classe, ctx);
			}
		}
		return null;
	}
}