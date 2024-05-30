package com.example.testtask1.repo;

import com.example.testtask1.controller.task.StatusFilter;
import com.example.testtask1.controller.task.StatusTask;
import com.example.testtask1.repo.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

public interface TaskRepo extends JpaRepository<TaskEntity, Long>, JpaSpecificationExecutor<TaskEntity> {

    default Page<TaskEntity> findAll(Pageable pageRequest, StatusFilter filter, Specification<TaskEntity> specification) {
        return findAll(
                where(specification)
                        .and(withStatus(filter.getStatusTask()))
                        .and(withUserName(filter.getUserName()))
                ,  pageRequest
        );
    }

    Optional<TaskEntity> findByIdAndStatus(Long id, StatusTask statusTask);

    static Specification<TaskEntity> withStatus(StatusTask statusTask) {
        return (root, query, builder) -> {
            if (statusTask == null) {
                return builder.conjunction();
            } else {
                return builder.equal(root.get("status").as(String.class), statusTask);
            }
        };
    }

    static Specification<TaskEntity> withUserName(String userName) {
        return (root, query, builder) -> {
            if (userName == null) {
                return builder.conjunction();
            } else {
                return builder.like(root.get("userName").as(String.class), userName);
            }
        };
    }
}
