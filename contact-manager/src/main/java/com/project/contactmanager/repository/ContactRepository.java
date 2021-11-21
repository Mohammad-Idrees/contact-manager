package com.project.contactmanager.repository;

import com.project.contactmanager.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(
            value = "select * from tbl_contacts c where c.user_id = ?1",
            nativeQuery = true
    )
    public Page<Contact> findAllByUserId(Long userId, Pageable pageable);
}
