package com.delores.medusa.dao.repository;

import com.delores.medusa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author William
 * @date 4/16/21 6:42 PM
 * @description
 */
@Repository // 可写可不写
public interface UserRepository extends WiselyRepository<User, Long> {


}
