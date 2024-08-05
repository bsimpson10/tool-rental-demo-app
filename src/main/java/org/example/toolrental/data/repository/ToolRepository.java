package org.example.toolrental.data.repository;

import org.example.toolrental.data.entity.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends CrudRepository<Tool, String> {
    Tool findByCode(String code);
}
