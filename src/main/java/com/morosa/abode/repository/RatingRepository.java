/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.morosa.abode.repository;

import com.morosa.abode.entity.Nurse;
import com.morosa.abode.entity.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author plutus
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findByNurse(Nurse nurse);

}
