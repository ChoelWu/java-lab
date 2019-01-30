package com.choel.java;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingCart {
    private ArrayList<CartItem> cart;

    public ShoppingCart() {
        cart = new ArrayList<CartItem>();
    }

    /**
     * 返回购物车中的所有商品
     *
     * @return 购物车商品
     */
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    /**
     * 添加一种商品到购物车中，若已存在则修改该商品的数量
     *
     * @param item 需要新增的商品对象
     */
    public void addCartItem(CartItem item) {
        CartItem oldItem = null;
        if (item != null) {
            for (CartItem aCart : cart) {
                oldItem = aCart;
                if (oldItem.getId().equals(item.getId())) {
                    oldItem.setQuality(oldItem.getQuality() + item.getQuality());
                    return;
                }
            }
            cart.add(item);
        }
    }

    /**
     * 从购物车中删除商品
     *
     * @param id 需要删除的商品编号
     * @return 删除成功返回true，失败返回false
     */
    public boolean removeCartItem(String id) {
        CartItem item = null;
        for (int i = 0; i < cart.size(); i++) {
            item = cart.get(i);
            if (item.getId().equals(id)) {
                cart.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * 计算所购商品的总价
     *
     * @return 商品的总价
     */
    public double getTotal() {
        Iterator<CartItem> it = cart.iterator();
        double sum = 0.0;
        CartItem item = null;
        while (it.hasNext()) {
            item = it.next();
            sum += item.getSum();
        }
        return sum;
    }
}
