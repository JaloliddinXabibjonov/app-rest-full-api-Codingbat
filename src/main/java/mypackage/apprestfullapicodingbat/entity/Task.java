package mypackage.apprestfullapicodingbat.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String question;

    private String solution;
    private String hint;


    @ManyToOne
    private Department department;

}
