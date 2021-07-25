package com.yc.controller;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import com.yc.utils.YcConstants;
import com.yc.vo.CartItem;
import com.yc.vo.JsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.yc.utils.YcConstants.RESFOODLIST;

@RestController
public class ResorderController {

    @Autowired
    private ResfoodBiz resfoodBiz;

    //添加食物到购物车
    @RequestMapping(value = "/orderJson",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel orderJsonOp(HttpServletRequest req, JsonModel jm, HttpSession session){
        commonOrder(req,session);
        jm.setCode(1);
        return jm;
    }

    private void commonOrder(HttpServletRequest req, HttpSession session) {
        //取出要加入购物车的商品id数量
        int fid=Integer.parseInt(req.getParameter("fid"));
        int num=Integer.parseInt(req.getParameter("num"));
        //取出菜品列表，以查找对应的菜品
        List<Resfood> list=null;
        //常量可以直接写
        if(session.getAttribute(RESFOODLIST)!=null){
            list= (List<Resfood>) session.getAttribute(RESFOODLIST);
        }else{
            //2没有，则查
            list=resfoodBiz.findAll();
            session.setAttribute(RESFOODLIST,list);
        }
        Resfood food=null;
        for(Resfood r:list){
            if(r.getFid()==fid){
                food=r;
                break;
            }
        }
        //购物车跟用户有关，所以存session
        Map<Integer, CartItem> cart=null;
        //是否第一次购买，如果是则   session中没有存购物车
        if(session.getAttribute(YcConstants.CART)!=null){
            cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        }else {
            cart=new HashMap<>();
        }
        //看这个购物车是否有fid
        CartItem ci=null;
        if(cart.containsKey(fid)){
            //证明用户已经购买了这个菜，则数量增加
            ci=cart.get(fid);
            int newnum=ci.getNum()+num;
            ci.setNum(newnum);
        }else {
            //还咩有买过，则创建一个cartItem存到map中
            ci=new CartItem();
            ci.setFood(food);
            ci.setNum(num);
        }
        if(ci.getNum()<=0){
            cart.remove(fid);
        }else {
            ci.getSmallCount();//计算小计
            //将cartitem存到map中
            cart.put(fid,ci);
        }
        //将cart存到session中
        session.setAttribute(YcConstants.CART,cart);
    }


    //获取购物车信息
    @RequestMapping(value = "/getCartInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel getCartInfoOp(HttpServletRequest req,HttpSession session,JsonModel jm){
        List<CartItem> list=new ArrayList<>();
        if(session.getAttribute(YcConstants.CART)!=null){//从session中取出购物车对象
            jm.setCode(1);
            //变更，将对象改为list，方便后面循环取值
            Map<Integer,CartItem> cart=null;
            cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
            Set<Integer> sets=cart.keySet();
            Iterator<Integer> iterator=sets.iterator();
            while (iterator.hasNext()){
                //x这里就是cart中的键  fid
                int x=iterator.next();
                list.add(cart.get(x));
            }
            jm.setObj(list);
        }else {
            jm.setCode(0);
        }
        return jm;

    }

    //删除购物车中的某fid食物
    @RequestMapping(value = "/delorder",method = RequestMethod.GET)
    public JsonModel delorder(HttpSession session,JsonModel jm,Resfood food){
        int fid=food.getFid();
        //购物车跟用户相关，所以存session
        Map<Integer,CartItem> cart=null;
        if(session.getAttribute(YcConstants.CART)!=null){
            cart= (Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        }else {
            cart=new HashMap<>();
        }
        if(cart.containsKey(fid)){
            cart.remove(fid);
            jm.setCode(1);
        }else{
            jm.setCode(0);
        }
        //将cart存到session中
        session.setAttribute(YcConstants.CART,cart);
        return jm;
    }


    //清理购物车
    @RequestMapping(value = "/clearAll",method = {RequestMethod.GET,RequestMethod.POST})
    public JsonModel clearAllOp(HttpSession session,JsonModel jm){
        session.removeAttribute(YcConstants.CART);
        jm.setCode(1);
        return jm;
    }
}
