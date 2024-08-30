package healthcare;

import healthcare.model.Appointment;
import healthcare.model.Doctor;
import healthcare.model.Patient;
import healthcare.repository.AppointmentRepositoryImpl;
import healthcare.repository.DoctorRepositoryImpl;
import healthcare.service.AppointmentService;
import healthcare.service.DoctorService;
import healthcare.service.PatientService;
import healthcare.repository.PatientRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        //PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        //PatientService patientService = new PatientService(patientRepository);
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Doctors");
        System.out.println("3. Manage Appointments");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (choice) {
                case 1:
                    // Application calls the service layer, not the repository directly
                    managePatients(sessionFactory, scanner);
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    manageDoctors(sessionFactory, scanner);
                    break;
                case 3:
                    // Application calls the service layer, not the repository directly
                    manageAppointments(sessionFactory, scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } finally {
            scanner.close();
            sessionFactory.close();
        }
    }

    private static void managePatients(SessionFactory sf, Scanner scanner) {
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sf);
        PatientService patientService = new PatientService(patientRepository);
        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // Application calls the service layer, not the repository directly
                Patient newPatient = new Patient();
                System.out.print("Enter first name: ");
                newPatient.setFirstName(scanner.nextLine());
                scanner.nextLine();
                System.out.print("Enter last name: ");
                newPatient.setLastName(scanner.nextLine());
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                newPatient.setDateOfBirth(scanner.nextLine());
                System.out.print("Enter email: ");
                newPatient.setEmail(scanner.nextLine());
                System.out.print("Enter phone number: ");
                newPatient.setPhoneNumber(scanner.nextLine());
                patientService.createPatient(newPatient);  // Use service here
                System.out.println("Patient created successfully.");
                break;
            case 2:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientService.getPatientById(patientId);  // Use service here
                if (patient != null) {
                    System.out.println("Patient ID: " + patient.getPatientId());
                    System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                    System.out.println("Date of Birth: " + patient.getDateOfBirth());
                    System.out.println("Email: " + patient.getEmail());
                    System.out.println("Phone: " + patient.getPhoneNumber());
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 3:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                scanner.nextLine();  // consume newline
                patient = patientService.getPatientById(patientId);  // Use service here
                if (patient != null) {
                    System.out.print("Enter new first name: ");
                    patient.setFirstName(scanner.nextLine());
                    System.out.print("Enter new last name: ");
                    patient.setLastName(scanner.nextLine());
                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                    patient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter new email: ");
                    patient.setEmail(scanner.nextLine());
                    System.out.print("Enter new phone number: ");
                    patient.setPhoneNumber(scanner.nextLine());
                    patientService.updatePatient(patient);  // Use service here
                    System.out.println("Patient updated successfully.");
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 4:
                // Application calls the service layer, not the repository directly
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                patientService.deletePatient(patientId);  // Use service here
                System.out.println("Patient deleted successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageDoctors(SessionFactory sessionFactory, Scanner scanner) {
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);
        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                Doctor doctor = new Doctor();
                System.out.println("Enter first name: ");
                doctor.setFirstName(scanner.nextLine());
                System.out.println("Enter last name: ");
                doctor.setLastName(scanner.nextLine());
                System.out.println("Enter specialty: ");
                doctor.setSpecialty(scanner.nextLine());
                System.out.println("Enter email: ");
                doctor.setEmail(scanner.nextLine());
                doctorService.createDoctor(doctor);
                System.out.println("Doctor created successfully");
                break;

            case 2:
                //Tryna find the patient
                System.out.println("Enter the doctor id: ");
                int docId = scanner.nextInt();
                doctor = doctorService.getDoctorById(docId);
                if (doctor != null) {
                    System.out.println(doctor.toString());
                }else {
                    System.out.println("Doctor not found");
                }

                break;

            case 3:
                System.out.println("Enter the doctor id: ");
                int id = Integer.parseInt(scanner.nextLine());
                doctor = doctorService.getDoctorById(id);
                if (doctor != null) {
                    System.out.println("Enter new first name: ");
                    doctor.setFirstName(scanner.nextLine());
                    System.out.println("Enter new last name: ");
                    doctor.setLastName(scanner.nextLine());
                    System.out.println("Enter new specialty: ");
                    doctor.setSpecialty(scanner.nextLine());
                    System.out.println("Enter new email: ");
                    doctor.setEmail(scanner.nextLine());
                    doctorRepository.updateDoctor(doctor);
                    System.out.println("Doctor updated successfully");
                }
                break;

            case 4:
                System.out.println("Enter the id of Doctor you wish to delete: ");
                id = scanner.nextInt();
                doctorService.deleteDoctor(id);
                System.out.println("Doctor successfully deleted");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void manageAppointments(SessionFactory sessionFactory, Scanner scanner) {
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice) {
            case 1:
                Appointment ap = new Appointment();
                System.out.println("Enter Patient ID: ");
                ap.setPatientId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter Doctor ID: ");
                ap.setDoctorId(Integer.parseInt(scanner.nextLine()));
                System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
                ap.setAppointmentDate(scanner.nextLine());
                System.out.println("Enter notes: ");
                ap.setNotes(scanner.nextLine());
                appointmentService.createAppointment(ap);
                System.out.println("Appointment successfully created");
                break;

            case 2:
                System.out.println("Enter the Apppointment ID of the Appointment you wish to select");
                int id = scanner.nextInt();
                Appointment appointment = appointmentService.getAppointmentById(id);
                if(appointment != null) {
                    System.out.println(appointment.toString());
                    System.out.println("Apointment successfully retrieved");
                }else {
                    System.out.println("Appointment not found");
                }
                break;

            case 3:
                System.out.println("Enter the ID: ");
                id = scanner.nextInt();
                appointment = appointmentService.getAppointmentById(id);
                if (appointment != null) {
                    System.out.println("Enter new Patient ID: ");
                    appointment.setPatientId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Enter new Doctor ID: ");
                    appointment.setDoctorId(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Enter new Appointment Date (YYYY-MM-DD): ");
                    appointment.setAppointmentDate(scanner.nextLine());
                    System.out.println("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());
                    appointmentRepository.updateAppointment(appointment);
                    System.out.println("Appointment successfully updated");
                }
                break;

            case 4:
                System.out.println("Enter the id: ");
                id = scanner.nextInt();
                appointmentService.deleteAppointment(id);
                System.out.println("Doctor successfully deleted");
                break;

            default:
                System.out.println("Invalid choice");
        }
    }

}
