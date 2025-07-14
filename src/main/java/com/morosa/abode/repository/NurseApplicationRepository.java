/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.repository;

import com.morosa.abode.entity.NurseApplication;
import com.morosa.abode.entity.enums.ApplicationStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author plutus
 */
@Repository
public interface NurseApplicationRepository extends JpaRepository<NurseApplication, String> {

    Optional<NurseApplication> findByApplicationId(String applicationId);

    List<NurseApplication> findByStatus(ApplicationStatus status);
}
