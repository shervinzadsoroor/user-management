package org.surena.usermanagement.domainmodel.userinfo;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
}
