package com.onycom.score.mapper;

import com.onycom.score.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScoreMapper {
    List<Score> findByStudentId(Long studentId);
    void insert(Score score);
}
