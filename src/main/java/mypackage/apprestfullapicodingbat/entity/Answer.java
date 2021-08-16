package mypackage.apprestfullapicodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false,unique = true)
    private String textCheckAnswer;

    @Column(nullable=false)
    private boolean success;

    private String description;

    @OneToOne
    private User user;

    @OneToOne
    private Task task;



}
