package utilities;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class Dao extends HibernateDaoSupport {
	
	public Session getSession() {
		return this.getSessionFactory().openSession();
	}

}
