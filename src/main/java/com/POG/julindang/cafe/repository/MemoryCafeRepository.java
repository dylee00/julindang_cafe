package com.POG.julindang.cafe.repository;

import com.POG.julindang.cafe.domain.Cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MemoryCafeRepository {
    private static Map<Long, Cafe> store = new ConcurrentHashMap<>();
    private static Long id = 0L;

    public void clear(){
        store.clear();
    }

    private List<Cafe> findAll(){
        return new ArrayList<>(store.values());
    }

    private List<Cafe> findByCafeName(String cafeName){
        return store.values().stream()
                .filter(e -> e.getCafeName().equals(cafeName))
                .collect(Collectors.toList());
    }

    public List<Cafe> findByBeverageName(String beverageName){
        return store.values().stream()
                .filter(e -> e.getCafeName().equals(beverageName))
                .collect(Collectors.toList());
    }

    public void save(Cafe cafe){
        store.put(++id, cafe);
    }
}
