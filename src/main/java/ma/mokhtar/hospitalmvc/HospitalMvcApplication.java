package ma.mokhtar.hospitalmvc;

import ma.mokhtar.hospitalmvc.entities.Patient;
import ma.mokhtar.hospitalmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HospitalMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PatientRepository patientRepository) {
        return args -> {
            Patient p1 = new Patient(); // NoArgsConstructor
            p1.setNom("Lahjaily");
            p1.setPrenom("Mokhtar");
            p1.setScore(0);
            p1.setDateNaissance(new Date());
            p1.setMalade(false);


            Patient p2 = new Patient(null, "Lahjaily", "Mokhtar", new Date(), 1200, false); // AllArgsConstructor


            Patient p3 = Patient.builder()
                    .nom("Leo")
                    .prenom("Messi")
                    .dateNaissance(new Date())
                    .score(1200)
                    .malade(false)
                    .build(); // Builder

            // Save patients
            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);
            // Find all patients
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });
            // Find patient by id
            /*patientRepository.findById(1L).ifPresent(p -> {
                System.out.println("Patient with id 1: " + p.toString());
            });*/
        };

    }

}
