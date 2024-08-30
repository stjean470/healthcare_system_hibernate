package healthcare.service;

import healthcare.model.Appointment;
import healthcare.repository.AppointmentRepositoryImpl;

public class AppointmentService {
    private final AppointmentRepositoryImpl appointmentRepository;

    public AppointmentService(AppointmentRepositoryImpl appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.updateAppointment(appointment);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteAppointment(id);
    }


}
