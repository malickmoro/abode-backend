/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.repository;

import com.morosa.abode.entity.Nurse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author plutus
 */
@Repository
public interface NurseRepository extends JpaRepository<Nurse, String> {

    @Query("SELECT n FROM Nurse n JOIN n.services s WHERE s IN :services GROUP BY n.id")
    List<Nurse> findByAnyService(@Param("services") List<String> services);

}
