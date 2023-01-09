package com.example.demo.Service;

import com.example.demo.Entity.Entry;
import com.example.demo.Repository.EntryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {
    @Autowired
    private EntryDAO entryDAO;
    @Override
    public String saveEntries(List<Entry> entries) {
        for(Entry i:entries) entryDAO.save(i);
        return "entry is saved";
    }
}
