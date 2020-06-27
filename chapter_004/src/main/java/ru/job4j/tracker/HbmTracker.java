package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Реализация трекера на базе Hibernate
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @since 27.06.2020
 * @version 1.0
 */
public class HbmTracker implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update ru.job4j.tracker.Item set name = :name where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", item.getName());
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return (result > 0);
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from ru.job4j.tracker.Item where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return (result > 0);
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ru.job4j.tracker.Item where name = :name");
        query.setParameter("name", key);
        List result = query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ru.job4j.tracker.Item where id = :id");
        query.setParameter("id", id);
        Item result = (Item) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
