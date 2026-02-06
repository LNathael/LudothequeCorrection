package fr.eni.ludotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="GENRES")
@Builder
public class Genre {
	@Id
	@NonNull private Integer noGenre;
	@NonNull private String libelle;

}
