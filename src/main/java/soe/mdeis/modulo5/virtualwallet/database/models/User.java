package soe.mdeis.modulo5.virtualwallet.database.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "virtual-wallet")
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Collection<LogEvent> logEventsById;
    private Collection<Wallet> walletsById;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @OneToMany(mappedBy = "user")
    public Collection<LogEvent> getLogEventsById() {
        return logEventsById;
    }

    public void setLogEventsById(Collection<LogEvent> logEventsById) {
        this.logEventsById = logEventsById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Wallet> getWalletsById() {
        return walletsById;
    }

    public void setWalletsById(Collection<Wallet> walletsById) {
        this.walletsById = walletsById;
    }
}
