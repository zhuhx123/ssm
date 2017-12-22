package cn.com.ssm.common.mapper;

import cn.com.ssm.common.model.ImAddress;
import cn.com.ssm.common.model.ImAddressExample;
import com.ivymei.framework.system.base.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImAddressMapper extends BaseMapper {
    int countByExample(ImAddressExample example);

    int deleteByExample(ImAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImAddress record);

    int insertSelective(ImAddress record);

    List<ImAddress> selectByExample(ImAddressExample example);

    ImAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImAddress record, @Param("example") ImAddressExample example);

    int updateByExample(@Param("record") ImAddress record, @Param("example") ImAddressExample example);

    int updateByPrimaryKeySelective(ImAddress record);

    int updateByPrimaryKey(ImAddress record);
}