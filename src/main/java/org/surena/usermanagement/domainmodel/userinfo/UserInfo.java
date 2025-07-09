package org.surena.usermanagement.domainmodel.userinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.surena.usermanagement.domainmodel.BaseEntity;

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

    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "USERNAME", unique = true, nullable = false, updatable = false, length = 250)
    private String username;

    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRSTNAME", length = 100)
    private String firstName;

    @Column(name = "LASTNAME", length = 150)
    private String lastName;
}
