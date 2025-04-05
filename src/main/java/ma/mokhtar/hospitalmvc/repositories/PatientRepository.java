package ma.mokhtar.hospitalmvc.repositories;

import ma.mokhtar.hospitalmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsIgnoreCase(String kw, Pageable pageable);
    Optional<Patient> findByNom(String nom);
    Optional<Patient> findByEmail(String email);


}
