package io.hala.whistleon.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "auth_code")
public class AuthCode {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "code")
  private String code;

  public void updateCode(String code) {
    this.code = code;
  }
}
