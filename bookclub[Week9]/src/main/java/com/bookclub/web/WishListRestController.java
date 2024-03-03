package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishListDao;
import com.bookclub.service.impl.MongoWishListDao;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishListRestController {
    WishListDao wishlistDao = new MongoWishListDao();
    @Autowired
    private void setWishlistDao(WishListDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<WishlistItem> showWishlist(Authentication authentication) {
        String username = authentication.getName();
        return wishlistDao.list(username);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
