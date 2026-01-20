package com.hotel.repository;

import com.hotel.entity.ManageWebsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageWebsiteRepository extends JpaRepository<ManageWebsite, Integer> {
}