package entity.user;

import java.sql.Date;
import java.util.Objects;

/**
 * Java bean stores user information
 */
public class User {
    /**
     * id - user's id
     * role - user's role (admin or user)
     * gender - user's gender (male or female)
     * name - user's name
     * email - user email
     * password - user password
     * birthday - user birthday
     */
    private int id;
    private Role role;
    private Gender gender;
    private String name;
    private String email;
    private String password;
    private Date birthday;

    public User(String role, String gender, String name, String email, String password, Date birthday) {
        if (role == null) {
            role = "USER";
        }
        this.role = Role.valueOf(role);
        this.gender = Gender.valueOf(gender);
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public User(int id, String role, String gender, String name, String email, String password, Date birthday) {
        this.id = id;
        this.role = Role.valueOf(role);
        this.gender = Gender.valueOf(gender);
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
