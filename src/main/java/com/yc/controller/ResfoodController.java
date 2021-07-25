package com.yc.controller;

import com.yc.bean.Resfood;
import com.yc.bean.Resorder;
import com.yc.bean.Resuser;
import com.yc.biz.ResfoodBiz;
import com.yc.biz.ResorderBiz;
import com.yc.utils.YcConstants;
import com.yc.vo.CartItem;
import com.yc.vo.JsonModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.yc.utils.YcConstants.RESFOODLIST;

@RestController //@Controller  @ResponseBody
@Slf4j//lombok注解
@Api(description = "小萌神网络订餐系统操作接口",tags = {"菜品操作部分"})
public class ResfoodController {


    @Autowired
    private ResfoodBiz resfoodBiz;

    @Autowired
    private ResorderBiz resorderBiz;

    @RequestMapping(value = "/findAllFoods",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "查询所有菜",notes = "查询所有的菜")
    public JsonModel findAllFoods(HttpSession session, JsonModel jm){//jm由springmvc创建，session一定注入，但是jm注入
        List<Resfood> list=resfoodBiz.findAll();
        session.setAttribute(RESFOODLIST,list);
        //返回jsonModel
        jm.setCode(1);
        jm.setObj(list);
        return jm;
    }


    @RequestMapping(value = "/findFood",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "根据fid查找菜品",notes = "根据fid查找菜品")
    @ApiImplicitParams({@ApiImplicitParam(name = "fid",value = "菜品编号",required = true)})
    public JsonModel findFood(HttpSession session,Resfood food,JsonModel jm){
        List<Resfood> list=null;
        if(session.getAttribute(RESFOODLIST)!=null){
            list=(List<Resfood>) session.getAttribute(RESFOODLIST);
        }else {
            //2.没有则查
            list=resfoodBiz.findAll();
            session.setAttribute(RESFOODLIST,list);
        }
        for(Resfood r:list){
            if(food.getFid().equals(r.getFid())){
                jm.setCode(1);
                jm.setObj(r);
                return jm;
            }
        }
        jm.setCode(0);
        return jm;

    }


    @RequestMapping(value = "confirmOrder",method = RequestMethod.POST)
    public JsonModel confirmOrder(HttpSession session, Resorder resorder,JsonModel jm){
        if(session.getAttribute(YcConstants.LOGINUSER)==null){
            jm.setCode(0);
            jm.setMsg("user has not been logined...");
            return  jm;
        }
        //查询用户id，从session中取出登录用户
        Resuser resuser=(Resuser) session.getAttribute(YcConstants.LOGINUSER);
        resorder.setUserid(resuser.getUserid());
        //准备  Resorderitem数据
        if(session.getAttribute(YcConstants.CART)==null || ((Map<Integer,CartItem>)session.getAttribute(YcConstants.CART)).size()<=0){
            jm.setCode(0);
            jm.setMsg("you have not buy any thing..");
            return jm;
        }
        Map<Integer, CartItem> cart=(Map<Integer, CartItem>) session.getAttribute(YcConstants.CART);
        try{
            //完成订单（将订单和购物车里的订单项存入数据库）
            resorderBiz.completeOrder(resorder,cart);
            //删除session存入的购物车
            session.removeAttribute(YcConstants.CART);
            jm.setCode(1);
        }catch(Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        return jm;
    }







}
