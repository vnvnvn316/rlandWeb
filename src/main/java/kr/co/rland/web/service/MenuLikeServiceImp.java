package kr.co.rland.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.rland.web.entity.MenuLike;
import kr.co.rland.web.repository.MenuLikeRepository;

public class MenuLikeServiceImp implements MenuLikeService {

    @Autowired
    private MenuLikeRepository repository;

    @Override
    public MenuLike add(MenuLike menuLike) {
        return null;
    }

    @Override
    public String cancel(MenuLike menuLike) {
        return null;
    }

    
}
