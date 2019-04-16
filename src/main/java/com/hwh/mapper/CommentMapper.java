package com.hwh.mapper;

import com.hwh.po.WtAnswer;
import com.hwh.po.WtRelease;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:03
 */
@Repository
public interface CommentMapper {

    //首页查找所有发布的问题
    List<WtRelease> findAllWtRelease();

    List<WtRelease> findAllWtreleasesPage(@Param("currPage") int currPage,@Param("limit") int limt);

    void addWtrelease(WtRelease wr);

    WtRelease getWtreleaseDeteals(String wtid);

    List<WtAnswer> getAllAnswerByWtId(String wtid);

    String getQuestionIDByWtId(String wtid);

    void submitAnswerContent(WtAnswer wa);

    String getAllAnswerNumByWtId(String wtid);

    Long countPage();
}
