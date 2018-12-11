package com.lewei.production.mapper.promapper;

import com.lewei.production.annotation.MyBatis;
import com.lewei.production.model.IssueRecordHead;
import com.lewei.production.model.IssueRecordLine;
import com.lewei.production.util.Pagination;

import java.util.List;

@MyBatis
public interface IssueRecordLineMapper {

    List<IssueRecordLine> listByPage(Pagination pagination);

    int deleteByPrimaryKey(Integer id);

    int insert(IssueRecordLine record);

    int insertSelective(IssueRecordLine record);

    IssueRecordLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IssueRecordLine record);

    int updateByPrimaryKey(IssueRecordLine record);
}