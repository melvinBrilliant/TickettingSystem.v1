package com.itc25.ticketingsystem.repositories;

import com.itc25.ticketingsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    // untuk 6a
    @Query(value = """
            select *
            from Employee
            where JobID = 2
            """, nativeQuery = true)
    List<Employee> findAllTechnicalSupport();

    // untuk 6b
    @Query(value = """
            select e.*
            from Employee e
            join Ticket t on t.AppointedTo = e.EmployeeID
            where e.JobID = 2 and t.[Status] = 'IN_PROGRESS'
            """, nativeQuery = true)
    List<Employee> findAllTechnicalSupportOnDuty();

    // untuk 6c
    @Query(value = """
            select tt.EmployeeID, tt.FirstName, tt.LastName, tt.BirthDate, tt.Phone, tt.JobID
            from (
                select *
                from Employee e
                left join Ticket t on t.AppointedTo = e.EmployeeID
                where e.JobID = 2
            ) tt
            where
                tt.TicketID is null or
                tt.[Status] = 'CANCELED' or
                tt.[Status] = 'COMPLETED'
            """, nativeQuery = true)
    List<Employee> findAllTechnicalSupportOffDuty();
}
