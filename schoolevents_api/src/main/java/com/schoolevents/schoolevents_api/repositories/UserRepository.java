package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.DTO.UserStadisticsDTO;
import com.schoolevents.schoolevents_api.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);

    @Query(value = "SELECT\n" +
            "    au.id AS user_id,\n" +
            "    au.full_name,\n" +
            "    COUNT(s.id) AS signs_total\n" +
            "FROM\n" +
            "    app_user au\n" +
            "JOIN\n" +
            "    sign s ON au.id = s.user_id\n" +
            "GROUP BY\n" +
            "    au.id, au.full_name\n" +
            "ORDER BY\n" +
            "    signs_total DESC\n" +
            "LIMIT 1;",nativeQuery = true)
    UserStadisticsDTO getUserStadistic();

    User save(User user);
    void deleteById(Long id);
}
