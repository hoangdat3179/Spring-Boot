package com.example.BatDongSan.repository;
import com.example.BatDongSan.entity.TinDangStatus;
import com.example.BatDongSan.entity.TinDangThue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinDangThueRepository extends CrudRepository<TinDangThue,Integer> {
    Page<TinDangThue> findAllByTitleContains(String keyword, Pageable pageable);

    Page<TinDangThue> findAllByTinDangStatus(TinDangStatus tinDangStatus, Pageable pageable);

    Page<TinDangThue> findAllByGiaBanBetweenAndTinDangStatus(double start, double end,TinDangStatus tinDangStatus, Pageable pageable);

    Page<TinDangThue> findAllByDienTichBetweenAndTinDangStatus(double start, double end,TinDangStatus tinDangStatus, Pageable pageable);
}
