package kr.co.rland.web.service;

import java.util.List;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;

public interface MenuService {

    List<MenuView> getList(Long memberId, Integer page);
    List<MenuView> getList(Long memberId, Integer page, Long categoryId);
    List<MenuView> getList(Long memberId,Integer page, String query);
    List<MenuView> getList(Long memberId, Integer page,Long categoryId, String query);

    void addMenu(Menu menu);
    void deleteMenu(long id);
    
    int getCount();
    int getCount(Long categoryId);
    int getCount(String query);
    
    Menu getById(Long id);

    int add(Menu menu, List<String> fileNames);

    


}
