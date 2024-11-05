import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Games {

    @Id //indica uma PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod;
    private String name;
    private double value;
}
