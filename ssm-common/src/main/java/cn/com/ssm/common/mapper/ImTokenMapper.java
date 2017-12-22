package cn.com.ssm.common.mapper;

import cn.com.ssm.common.model.ImToken;
import cn.com.ssm.common.model.ImTokenExample;
import com.ivymei.framework.system.base.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImTokenMapper extends BaseMapper {
    int countByExample(ImTokenExample example);

    int deleteByExample(ImTokenExample example);

    int deleteByPrimaryKey(Integer tId);

    int insert(ImToken record);

    int insertSelective(ImToken record);

    List<ImToken> selectByExample(ImTokenExample example);

    ImToken selectByPrimaryKey(Integer tId);

    int updateByExampleSelective(@Param("record") ImToken record, @Param("example") ImTokenExample example);

    int updateByExample(@Param("record") ImToken record, @Param("example") ImTokenExample example);

    int updateByPrimaryKeySelective(ImToken record);

    int updateByPrimaryKey(ImToken record);
}