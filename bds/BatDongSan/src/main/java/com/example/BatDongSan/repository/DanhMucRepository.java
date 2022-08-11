package com.example.BatDongSan.repository;

import com.example.BatDongSan.entity.DanhMuc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository  extends CrudRepository<DanhMuc,Integer> {
}
