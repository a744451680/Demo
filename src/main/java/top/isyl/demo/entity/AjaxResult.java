package top.isyl.demo.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一返回消息
 */
public class AjaxResult<T> implements Serializable{

    /**
     * 消息编码
     */
    private Integer code = 0;

    /**
     * 消息
     */
    private String msg = "";

    /**
     * 描述
     */
    private String desc ="";

    /**
     * 结果
     */
    private T res = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }


    public AjaxResult() {
    }

    /**
     * ajax 返回消息
     * @param code
     * @param msg
     */
    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 设置成功
     */
    public AjaxResult<T> success(){
        this.code = 0;
        this.msg = "success";
        this.desc="成功！";
        return this;
    }

    /**
     * 设置失败
     */
    public AjaxResult<T> fail(){
        this.code = -1;
        this.msg = "fail";
        this.desc="失败！";
        return this;
    }

    /**
     * 设置熔断消息
     */
    public AjaxResult<T> hystrix(){
        this.code = -2;
        this.msg = "hystrix";
        this.desc="网络延迟，稍后重试！";
        return this;
    }



    /**
     * 设置code
     * @param code
     */
    public AjaxResult<T> code(Integer code){
        this.code = code;
        return this;
    }
    /**
     * 设置消息
     * @param msg
     */
    public AjaxResult<T> msg(String msg){
        this.msg = msg;
        return this;
    }
    /**
     * 设置描述
     * @param desc
     */
    public AjaxResult<T> desc(String desc){
        this.desc = desc;
        return this;
    }

    /**
     * 设置结果
     * @param res
     */
    public AjaxResult<T> res(T res){
        this.res = res;
        return this;
    }

    public static void main(String[] args){
        System.out.println(JSON.toJSON(new AjaxResult<>(1001,"error").desc("hello").res(new Object())).toString());
    }
}
