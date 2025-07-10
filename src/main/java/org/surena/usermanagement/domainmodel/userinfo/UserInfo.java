package org.surena.usermanagement.domainmodel.userinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.surena.usermanagement.domainmodel.BaseEntity;
import org.surena.usermanagement.utils.PasswordService;

import java.io.Serial;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Comment(value = "کاربر")
@Table(name = "USER_INFO")
@SequenceGenerator(
        name = "default_gen",
        sequenceName = "USER_INFO_SEQ",
        allocationSize = 1)
public class UserInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 2L;

    @NotNull
    @Size(min = 5, max = 50, message = "username length must be between 5 and 50")
    @Column(name = "USERNAME", unique = true, nullable = false, updatable = false, length = 250)
    private String username;

    @NotNull
    @Setter(AccessLevel.NONE)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Size(min = 2, max = 100, message = "firstname length must be between 2 and 100")
    @Column(name = "FIRSTNAME", length = 100)
    private String firstName;

    @Size(min = 2, max = 100, message = "lastname length must be between 2 and 150")
    @Column(name = "LASTNAME", length = 150)
    private String lastName;

    //set hashed password from plain password
    public void setPassword(String plainPassword) {
        this.password = PasswordService.hashPassword(plainPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(username, userInfo.username) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(firstName, userInfo.firstName) &&
                Objects.equals(lastName, userInfo.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, firstName, lastName);
    }
}
