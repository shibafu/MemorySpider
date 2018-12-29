package com.tsugaruinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tsugaruinfo.entity.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {
    public UserMaster findByUsername(String username);
    public UserMaster findByEmail(String email);
    public List<UserMaster> findByEnabled(Boolean enabled);

    //Likeであいまい検索
    public List<UserMaster> findByUsernameLike(String usernamename);
    public List<UserMaster> findByEmailLike(String email);

    @Query("SELECT count(*) From UserMaster u WHERE u.username = :username")
    public int countByUsername(@Param("username")String username);
}
