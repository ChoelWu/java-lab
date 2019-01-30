package com.choel.servlet;

import com.choel.java.CartItem;
import com.choel.java.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetShoppingCartServlet")
public class GetShoppingCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingcart");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>display shopping cart</title></head>");
        out.println("<body>");
        if (cart == null) {
            out.println("<h1>您的购物车为空</h1><br>");
            out.println("<a href='/display_item.html'>浏览商品，添加商品到购物车</a><br>");
            return;
        } else {
            out.println("<h1>显示购物车内容</h1><br>");
            out.println("<a href='/display_item.html'>浏览商品，添加商品到购物车</a><br>");
            PrintCartItem(out, cart);
        }
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    private void PrintCartItem(PrintWriter out, ShoppingCart cart) {
        ArrayList<CartItem> items = cart.getCart();
        CartItem item = null;
        out.println("<table width='500' border='1' align='left'>");
        out.println("<tr>");
        out.println("<td width='200'>商品名称</td>");
        out.println("<td width='100'>价格</td>");
        out.println("<td width='100'>数量</td>");
        out.println("<td width='100'>小计</td>");
        out.println("</tr>");
        for (CartItem item1 : items) {
            item = item1;
            out.println("<tr>");
            out.println("<td>" + item.getName() + "</td>");
            out.println("<td>" + item.getPrice() + "元</td>");
            out.println("<td>" + item.getQuality() + "</td>");
            out.println("<td>" + item.getSum() + "元</td>");
            out.println("</tr>");
        }
        out.println("<tr>");
        out.println("<td colspan='4'>总计：" + cart.getTotal() + "元</td>");
        out.println("</tr>");
        out.println("</table>");
    }
}
