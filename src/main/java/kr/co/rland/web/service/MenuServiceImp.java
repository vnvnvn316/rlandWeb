package kr.co.rland.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.repository.MenuRepository;

@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Override
    public void addMenu(Menu menu) {
        repository.addMenu(menu);
    }

    @Override
    public void deleteMenu(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MenuView> getList(Integer page) {
        return getList(page, null, null);
    }

    @Override
    public List<MenuView> getList(Integer page, Long categoryId) {
        return getList(page, categoryId, null);
    }
    
    @Override
    public List<MenuView> getList(Integer page, String query) {
        return getList(page,null, query);
    }

    @Override
    public List<MenuView> getList(Integer page, Long categoryId, String query ) {
        int size=6;
        int offset=(page-1)*size;

        List<MenuView> list = 
            repository.findAll(categoryId, query, offset,size);
    return list;
    }

    @Override
    public int getCount() {
        int count = repository.count(null, null);
        return count;
    }

    @Override
    public int getCount(Long categoryId) {
        int count = repository.count(null, categoryId);
        return count;
    }

    @Override
    public int getCount(String query) {
        int count = repository.count(query,null);
        return count;
    }

    @Override
    public Menu getById(Long menuId) {
        Menu menu = repository.findById(menuId);
        return menu;
    }


    public Menu add(Menu menu){
        repository.save(menu);
        return menu;
    }
    
}