package web.model;

import javax.persistence.*;
// import hiber.model.Car;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;

  public void setCar(Car car) {
    this.car = car;
  }

  public Car getCar() {
    return this.car;
  }

  public User() {
  }

  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String toString() {
    return "ID: " + this.getId() + " NAME: " + this.getFirstName() + " LASTNAME: " + this.getLastName()
        + " EMAIL: " + this.getEmail();
  }

}
