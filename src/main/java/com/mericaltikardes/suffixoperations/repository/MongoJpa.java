package com.mericaltikardes.suffixoperations.repository;

import com.mericaltikardes.suffixoperations.model.SuffixDatas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoJpa extends MongoRepository<SuffixDatas, String> {
}
