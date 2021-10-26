package com.techshop.web.model.repository;

import com.techshop.web.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a WHERE a.idUser=:idUser")
    Optional<List<Address>> getAllByIdUser(@Param("idUser") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.address=:address, a.city=:city, a.phone=:phone,a.region=:region WHERE a.idUser=:idUser AND a.address=:address")
    void updateByIdUser(@Param("idUser") Integer id, @Param("address") String address,@Param("city") String city,@Param("region") String region,@Param("phone") String phone);

    @Modifying
    @Transactional
    @Query("DELETE FROM Address a WHERE a.address=:address AND a.idUser=:idUser")
    void deleteByIdUser(@Param("address") String address,@Param("idUser") Integer id);

    @Query("SELECT a FROM Address a WHERE a.address=:address")
    Address findIdByAddress(@Param("address") String address);
}