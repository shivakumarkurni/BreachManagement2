package com.breach.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.breach.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
