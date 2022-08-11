package com.example.BatDongSan.repository;

import com.example.BatDongSan.entity.DanhMuc;
import com.example.BatDongSan.entity.LoaiNhaDat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoaiNhaDatRepository  extends CrudRepository<LoaiNhaDat,Integer> {
    List<LoaiNhaDat> findLoaiNhaDatsByDanhMuc(DanhMuc danhMuc);
}
