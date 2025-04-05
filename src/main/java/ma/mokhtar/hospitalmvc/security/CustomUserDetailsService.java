package ma.mokhtar.hospitalmvc.security;

import ma.mokhtar.hospitalmvc.entities.Patient;
import ma.mokhtar.hospitalmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(patient.getEmail())
                .password(patient.getPassword())
                .roles("USER") // Vous pouvez personnaliser les rôles ici
                .build();
    }
}