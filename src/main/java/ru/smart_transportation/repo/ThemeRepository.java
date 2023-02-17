package ru.smart_transportation.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smart_transportation.entity.Role;
import ru.smart_transportation.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {

}

