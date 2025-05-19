package webApp.model.userdb;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for users.
 */
@Entity
@Table(name = "users") // optional: explicitly set table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String role = "USER"; // Default role

    @Column
    private String profilePictureUrl;

    // Relationships (e.g. to courses)
    /*@ManyToMany
    @JoinTable(
            name = "user_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private Set<Courses> enrolledCourses = new HashSet<>();*/

    // Constructors
    public User() {}

    public User(String fullName, String email, String password, String role, String profilePictureUrl) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profilePictureUrl = profilePictureUrl;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    /*public Set<Courses> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<Courses> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }*/
}