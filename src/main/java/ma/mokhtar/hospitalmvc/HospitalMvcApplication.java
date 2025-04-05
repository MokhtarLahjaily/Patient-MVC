package ma.mokhtar.hospitalmvc;

import ma.mokhtar.hospitalmvc.entities.Patient;
import ma.mokhtar.hospitalmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class HospitalMvcApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HospitalMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner init(PatientRepository patientRepository) {
        return args -> {
            // Création de patients avec emails uniques
            Patient p1 = new Patient(); // NoArgsConstructor
            p1.setNom("Lahjaily");
            p1.setPrenom("Mokhtar");
            p1.setEmail("mokhtar@example.com");
            p1.setPassword(passwordEncoder.encode("12345678"));
            p1.setScore(1200);
            p1.setDateNaissance(new Date());
            p1.setMalade(false);

            // Utilisation du pattern Builder au lieu du constructeur avec tous les arguments
            Patient p2 = Patient.builder()
                    .nom("Ayman")
                    .prenom("Yasser")
                    .email("ayman@example.com")
                    .password(passwordEncoder.encode("12345678"))
                    .dateNaissance(new Date())
                    .score(1800)
                    .malade(true)
                    .build();

            Patient p3 = Patient.builder()
                    .nom("Leo")
                    .prenom("Messi")
                    .email("leo@example.com")
                    .password(passwordEncoder.encode("12345678"))
                    .dateNaissance(new Date())
                    .score(1200)
                    .malade(false)
                    .build();

            Patient p4 = Patient.builder()
                    .nom("Cristiano")
                    .prenom("Ronaldo")
                    .email("cristiano@example.com")
                    .password(passwordEncoder.encode("12345678"))
                    .dateNaissance(new Date())
                    .score(1200)
                    .malade(false)
                    .build();

            // Création d'un compte admin
            Patient admin = Patient.builder()
                    .nom("Admin")
                    .prenom("User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin"))
                    .dateNaissance(new Date())
                    .score(2000)
                    .malade(false)
                    .build();

            // Génération de patients aléatoires pour les tests
            Random random = new Random();
            for (int i = 1; i <= 100; i++) {
                Patient randomPatient = Patient.builder()
                        .nom("User" + i)
                        .prenom("Test" + i)
                        .email("user" + i + "@example.com")
                        .password(passwordEncoder.encode("password" + i))
                        .dateNaissance(new Date())
                        .score(3000+random.nextInt(1000))
                        .malade(random.nextBoolean())
                        .build();
                patientRepository.save(randomPatient);
            }

            // Sauvegarde des patients principaux
            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);
            patientRepository.save(p4);
            patientRepository.save(admin);

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
