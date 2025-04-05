package ma.mokhtar.hospitalmvc.repositories;

import ma.mokhtar.hospitalmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(String nom,String prenom, Pageable pageable);

}
