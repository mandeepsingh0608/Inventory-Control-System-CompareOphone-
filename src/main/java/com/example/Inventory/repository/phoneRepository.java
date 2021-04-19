package com.example.Inventory.repository;

import com.example.Inventory.entity.phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface phoneRepository extends JpaRepository<phones, Integer> {

//  @Query("from phones p WHERE p.brand =:brand OR p.model=:model")
//    public List<phones> findAllPhoneByBrandOrByModel(@Param("brand") String brand,@Param("model") String model);

  @Query("from phones p WHERE p.brand =:brand AND p.model=:model")
  public phones findAllPhoneByBrandANDByModel(@Param("brand") String brand, @Param("model") String model);

    @Query("from phones p WHERE p.brand =:brand")
    public List<phones> findAllPhoneByBrand(@Param("brand") String brand);
//
//   @Query("from phones p WHERE p.model=:model")
//   public List<phones> findAllPhoneByModel(@Param("model") String model);


}
