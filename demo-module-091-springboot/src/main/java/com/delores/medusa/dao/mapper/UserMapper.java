package com.delores.medusa.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.medusa.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author William
 * @date 4/12/21 3:29 PM
 * @description
 */
//@Mapper -- 添加了@Mapper注解之后这个接口在编译时会生成相应的实现类,注入到Spring容器,等价于@MapperScan()
public interface UserMapper extends BaseMapper<User> {

    User getOneUser(Long id);

    IPage<User> getUsers(User user, Page<User> page);

    @Select("select * from medusa.user where name = #{user.name}")
    List<User> getUsers2(@Param("user") User user);

    User findUserByUsername(String username);

    int save(User user);
}
