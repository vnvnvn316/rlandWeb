package kr.co.rland.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
    public List<MenuView> getList(Long memberId,Integer page) {
        return getList(memberId, page, null, null);
    }

    @Override
    public List<MenuView> getList(Long memberId,Integer page, Long categoryId) {
        return getList(memberId, page, categoryId, null);
    }
    
    @Override
    public List<MenuView> getList(Long memberId,Integer page, String query) {
        return getList(memberId, page,null, query);
    }

    @Override
    public List<MenuView> getList(Long memberId,Integer page, Long categoryId, String query ) {
        int size=6;
        int offset=(page-1)*size;

        List<MenuView> list = 
            repository.findAll(memberId,categoryId, query, offset,size);
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


    public int add(Menu menu, List<String> fileNames){
        int affected = repository.save(menu);
        return affected;
    }

    @Override
    @Transactional()
    public void test() {
        Menu menu = Menu
                    .builder()
                    .id(8L)
                    .korName("김치치즈볶음밥")
                    .build();
        repository.update(menu);
        menu.setId(9L);
        menu.setKorName("새우볶음밥");
        repository.update(menu);
    }

    @Override
    @Transactional
    public void test2() {

       System.out.println("------------test1----------");
       Menu m1 = repository.findById(8L);
       System.out.println(m1);
       
       try {
            Thread.sleep(10000); //10초
       } catch (Exception e) {
        // TODO: handle exception
       }
       
       Menu m2 = repository.findById(9L);
       System.out.println(m2);
       System.out.println("--------test2-------------");
    
    }
    
}
