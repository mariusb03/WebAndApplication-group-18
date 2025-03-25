package SivertMarius.MappeAppWeb.DB.model.CoursesDB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CourseSessions {
  @Id
  private int sessions_id;
  private String start_date;
  private String end_date;
  private int hours_per_week;
}
