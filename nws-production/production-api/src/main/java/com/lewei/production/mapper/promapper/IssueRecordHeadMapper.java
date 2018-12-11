package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.IssueRecordHead;
import com.lewei.production.util.Pagination;

import java.util.List;

@MyBatis
public interface IssueRecordHeadMapper {
    int deleteByPrimaryKey(Integer id);
    List<IssueRecordHead> listByPage(Pagination pagination);

    int insert(IssueRecordHead record);

    int insertSelective(IssueRecordHead record);

    IssueRecordHead selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IssueRecordHead record);

    int updateByPrimaryKey(IssueRecordHead record);

    List<IssueRecordHead> selectByOrno(IssueRecordHead issueRecordHead);
}