package SivertMarius.MappeAppWeb.DB.model.CoursesDB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Titles {
  @Id
  private int title_id;
  private String title_name;
}
