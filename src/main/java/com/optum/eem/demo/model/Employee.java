package com.optum.eem.demo.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
  private int employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Date joinDate;
}
