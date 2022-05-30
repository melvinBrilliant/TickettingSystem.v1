package com.itc25.ticketingsystem.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JobID", nullable = false)
    private Integer id;

    @Column(name = "Title", nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "jobID")
    private Set<Employee> employees = new LinkedHashSet<>();

    public int findId(){
        return this.id;
    }

    public Job(Integer id){
        this.id = id;
    }
}