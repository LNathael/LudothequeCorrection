package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="ADRESSES")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Exclude
	private Integer noAdresse;
	
	@Column(length = 100, nullable = false)
	@NonNull private String rue;
	
	@Column(length = 5, nullable = false)
	@NonNull private String codePostal;
	
	@Column(length = 100, nullable = false)
	@NonNull private String ville;


    public Object getComplement() {
        return null;
    }
}
