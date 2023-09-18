package sprols.internship.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ListeDesMateriel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idListeMateriel;
    @OneToMany(mappedBy = "listeDesMateriel")
    private List<ListeMaterielItem> listeMaterielItemList = new ArrayList<>();
    @OneToMany(mappedBy = "listeMats")
    private List<ListeMaterielItem> listeMaterielItemApprovisionneList;

}
