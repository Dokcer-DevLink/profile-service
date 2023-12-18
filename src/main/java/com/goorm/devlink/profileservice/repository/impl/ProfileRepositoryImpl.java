package com.goorm.devlink.profileservice.repository.impl;

import com.goorm.devlink.profileservice.repository.ProfileRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

//import static com.goorm.devlink.profileservice.entity.QProfileEntity.profileEntity;

public class ProfileRepositoryImpl implements ProfileRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ProfileRepositoryImpl(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

//    @Override
//    public List<ProfileEntity> findProfileListByProfileTypeAndKeyword(ProfileType profileType, String keyword) {
//
//        List<ProfileEntity> profileList = queryFactory.selectFrom(ProfileEntity.profileEntity)
//                .where(profileEntity.profileType.eq(profileType), searchKeywordCondition(keyword))
//                .orderby(profileEntity.createdDate.desc())
//                .fetch();
//
//        return profileList;
//    }
}
