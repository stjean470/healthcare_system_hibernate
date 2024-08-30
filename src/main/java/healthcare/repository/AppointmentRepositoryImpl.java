package healthcare.repository;
import healthcare.model.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
public class AppointmentRepositoryImpl {
    private SessionFactory sessionFactory;

    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createAppointment(Appointment ap) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ap);
            transaction.commit();
        }
    }

    public Appointment getAppointmentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Appointment.class, id);
        }
    }

    public void updateAppointment(Appointment ap) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(ap);
            transaction.commit();
        }
    }

    public void deleteAppointment(int id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Appointment ap = session.get(Appointment.class, id);
            if (ap != null) {
                session.delete(ap);
            }
            transaction.commit();
        }
    }

    public List<Appointment> getAllAppointments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Appointments", Appointment.class).list();
        }
    }
}
