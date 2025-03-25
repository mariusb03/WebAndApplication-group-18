package SivertMarius.MappeAppWeb.DB.model.CoursesDB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Topics {
  @Id
  private int topic_id;
  private String category_name;

}
