package com.example.BatDongSan.service;


import com.example.BatDongSan.entity.TinDangBan;
import com.example.BatDongSan.repository.TinDangBanRepository;
import com.example.BatDongSan.request.TimKiemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimKiemService {
    @Autowired
    private TinDangBanRepository tinDangBanRepository;
    public List<TinDangBan> timKiemTinDangBan(TimKiemRequest timKiemRequest){
          String[] dienTich = timKiemRequest.getDienTich().trim().split("-");
          String[] giaBan = timKiemRequest.getMuaGia().trim().split("-");
          return (List<TinDangBan>) tinDangBanRepository.timKiemTinDangBan(Double.parseDouble(giaBan[0]),Double.parseDouble(giaBan[1]),timKiemRequest.getKeyword(),timKiemRequest.getThanhPho(),timKiemRequest.getQuanHuyen(),timKiemRequest.getPhuongXa(),Double.parseDouble(dienTich[0]),Double.parseDouble(dienTich[1]),timKiemRequest.getLoaiNha_id());
    }
}
