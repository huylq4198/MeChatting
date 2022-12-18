package com.huylq.mechatting.entity.htx;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_seq")
    @SequenceGenerator(name = "class_seq")
    private Long class_id;

    private String class_name;

    @ManyToMany(mappedBy = "classList")
    private List<StudentEntity> studentList;
}
