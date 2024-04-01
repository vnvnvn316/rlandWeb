package kr.co.rland.web.config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import kr.co.rland.web.entity.Menu;

public class LamdaTest {
    
    public static void main(String[] args) {
        
        //객체정렬
        List<Menu> list = new ArrayList<>();

        list.add(Menu.builder().id(1).korName("라라").build());
        list.add(Menu.builder().id(3).korName("나나").build());
        list.add(Menu.builder().id(5).korName("가가").build());
        list.add(Menu.builder().id(4).korName("다다").build());

        //익명클래스 사용
        Comparator<Menu> aa = new Comparator<Menu>(){

            @Override
            public int compare(Menu o1, Menu o2) {
                return (int)(o1.getId() - o2.getId());
            }
            
        };

        Comparator<Menu> bb = (Menu o1, Menu o2) ->{
            return (int)(o1.getId() - o2.getId());
        };

        Comparator<Menu> cc  = (o1,o2) -> (int)(o1.getId() - o2.getId());

        list.sort((o1,o2) -> (int)(o1.getId() - o2.getId()));
        
        list.sort(aa);

    }

}
