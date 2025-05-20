package com.karzhou.fraction.specification;

import com.karzhou.fraction.entity.Figure;

public interface Specification<T> {
    boolean isSatisfiedBy(Figure figure);
}

