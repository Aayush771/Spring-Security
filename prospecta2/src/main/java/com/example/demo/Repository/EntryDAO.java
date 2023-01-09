package com.example.demo.Repository;

import com.example.demo.Entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryDAO extends JpaRepository<Entry,String> {
}
