package com.optum.eem.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
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

  @ApiModelProperty(example = "2012-12-25 01:45:59", dataType = "java.time.LocalDateTime")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime joinDate;
}
