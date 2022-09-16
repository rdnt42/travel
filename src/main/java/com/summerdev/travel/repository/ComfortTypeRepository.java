package com.summerdev.travel.repository;

import com.summerdev.travel.entity.directory.ComfortType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 16.09.2022
 * Time: 23:05
 */
public interface ComfortTypeRepository extends JpaRepository<ComfortType, Integer> {
}
