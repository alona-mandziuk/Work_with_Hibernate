package org.example.hw6.task1;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

class HibernateUtil {
    private static SessionFactory factory;
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class.getName());

    static {
        try {
            factory = new Configuration()
                    .configure("HW6_hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            LOG.error(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
