package com.huylq.mechatting.repository.htx;

import com.huylq.mechatting.entity.htx.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
