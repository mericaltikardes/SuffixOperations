package com.mericaltikardes.suffixoperations.repository;

import com.mericaltikardes.suffixoperations.model.SuffixDatas;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

public interface MongoJpa extends MongoRepository<SuffixDatas, Date> {


}
