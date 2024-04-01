package kr.co.rland.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;

@Mapper
public interface MenuRepository {

    List<MenuView> findAll(Long categoryId, String query, int offset, int size);

    Menu findById(long id);
    List<Menu> findAllByName(String name);
    void deleteById(long id);

    int save(Menu menu);
    int update(Menu menu);
    int delete(long id);
    
    void addMenu(Menu menu);

    int count(String query, Long categoryId);
}
