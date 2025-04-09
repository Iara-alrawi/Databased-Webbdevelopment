package se.gritacademy.webbutvecklinguppgift.dao;

import se.gritacademy.webbutvecklinguppgift.model.Media;
import se.gritacademy.webbutvecklinguppgift.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class MediaDAO {
    public List<Media> searchMedia(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Media> mediaList = null;

        try {

            String hql = "FROM Media WHERE title LIKE :query OR author LIKE :query";
            Query<Media> q = session.createQuery(hql, Media.class);

            q.setParameter("query", "%" + query + "%");

            mediaList = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return mediaList;
    }


}
