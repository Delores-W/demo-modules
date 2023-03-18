package com.delores.medusa.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.delores.medusa.model.AuthToken;
import org.apache.ibatis.annotations.Param;

/**
 * @author William
 * @date 5/4/21 11:51 AM
 * @description
 */
public interface AuthTokenMapper extends BaseMapper<AuthToken> {

    int save(AuthToken authToken);

    AuthToken selectByAccessToken(String accessToken);

    void invalidateByToken(@Param("accessToken") String accessToken);

    void deleteInactiveToken();

    void invalidateTokenByUser(Long id);
}
