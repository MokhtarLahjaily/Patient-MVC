package ma.mokhtar.hospitalmvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Nom is required")
    @Size(min = 3, max = 20)
    private String nom;
    @NotEmpty(message = "Prenom is required")
    @Size(min = 3, max = 20)
    private String prenom;
    @Column(unique = true)
    private String email;
    @Column(length = 255)
    private String password;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @DecimalMin("1000")
    private int score;
    private boolean malade;

}
