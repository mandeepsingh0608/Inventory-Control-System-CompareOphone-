package com.example.Inventory.repository;

import com.example.Inventory.entity.features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface featureRepository extends JpaRepository<features, Integer> {
//    @Query("select * from attributes a WHERE a.phone_id =:(select p.id from phones p WHERE p.brand =:brand AND p.model=:model")
//    public List<attributes> findByPhone(@Param("phone_id") phones phone_id, @Param("brand") String brand, @Param("model") String model);
}
