package com.choel.servlet;

import com.choel.java.CartItem;
import com.choel.java.ShoppingCart;
import com.choel.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddShoppingCartServlet")
public class AddShoppingCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingcart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("shoppingcart", cart);
        }

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String quality = request.getParameter("quality");
        String price = request.getParameter("price");
        if (StringUtil.validateNull(id) || StringUtil.validateNull(name) || StringUtil.validateNull(price)) {
            printError(request, response);
            return;
        }
        id = StringUtil.filterHtml(id);
        name = StringUtil.filterHtml(name);

        try {
            if (StringUtil.validateNull(quality)) {
                cart.addCartItem(new CartItem(id, name, 1, Double.parseDouble(price)));
            } else {
                cart.addCartItem(new CartItem(id, name, Integer.parseInt(quality), Double.parseDouble(price)));
            }
        } catch (NumberFormatException e) {
            printError(request, response);
            return;
        }
        response.sendRedirect("/servlet/getShoppingCart");
    }

    private void printError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>add items error</title></head>");
        out.println("<body>");
        out.println("<h1>缺少商品参数或商品参数不正确，未能成功添加商品到购物车</h1><br>");
        out.println("<a href='/display_item.html'>继续浏览商品，添加商品到购物车</a><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
    }
}
