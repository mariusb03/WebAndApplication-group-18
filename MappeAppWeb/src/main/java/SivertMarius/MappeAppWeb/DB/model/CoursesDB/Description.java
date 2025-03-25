package SivertMarius.MappeAppWeb.DB.model.CoursesDB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Description {
  @Id
  private int desc_id;
  private String description;
}
