package healthcare.repository;

import healthcare.model.Appointment;
import healthcare.model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorRepositoryImpl {
    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void createDoctor(Doctor doctor) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        }
    }

    public Doctor getDoctorById(int id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Doctor.class, id);
        }
    }


    public void updateDoctor(Doctor doctor) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(doctor);
            transaction.commit();
        }
    }

    public void deleteDoctor(int id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor d = session.get(Doctor.class, id);
            if (d != null) {
                session.delete(d);
            }
            transaction.commit();
        }
    }


    public List<Doctor> getAllDoctors() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctors", Doctor.class).list();
        }
    }
}
