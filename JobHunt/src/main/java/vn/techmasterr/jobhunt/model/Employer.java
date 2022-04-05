package vn.techmasterr.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
   private String name;
   private String website; 
   private String email; 
   private String address;
}
