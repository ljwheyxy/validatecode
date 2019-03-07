# validatecode
java web 验证码生成器

## 用法
之前一直在研究验证码的实现，为了以后写项目方便，现将所有工具类打成jar包，以后直接可以引用。

实现方法是：
* 在前端页面中，img标签上，src的属性，设置为一个请求。后台调用 ValidateCode(HttpServletRequest reqeust, HttpServletResponse response, int width, int height, int codeCount, int lineCount)方法即可生成验证码并将验证码返回到前台页面，同时将验证码保存到session中。

* 后台接收到前台用户提交的验证码，调用isCodeRight(HttpServletRequest reqeust, HttpServletResponse response, String validateCode)方法，返回值表示用户提交验证码是否正确。

## 前台代码
```html
<div class="form-group has-feedback">
        <input name="checkCode" onkeydown="javascript:if(event.keyCode==13)  login();" type="text" id="checkCode" maxlength="4" onblur="checkTheCode(this)" style="width:120px;"/>
        <img src="getCode.do" id="CreateCheckCode" align="middle" title="点击刷新验证码" onclick="getCode()"  style="cursor: pointer;">
        <span id="checkCodeSpan" style="float: right;color: red;"></span>
 </div>
<script>
  function getCode(){
        $("#CreateCheckCode").attr('src',"http:localhost:7887/" +
            "getCode.do?nocache=" + new Date().getTime());
    }
</script>
```

##后台代码
```java 
package com.ideacl.checkcode.checkcode.controller;

import com.ideacl.checkcode.utils.CheckCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ideacl.top.ValidateCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chiawei
 * @date 2019-3-6 10:47
 */
@Controller
public class TestController {
    @RequestMapping("/getCode.do")
    public void getCode(HttpServletRequest reqeust, HttpServletResponse response) throws IOException {

        ValidateCode.writeValidateCode(reqeust, response, 250, 50, 4, 150);
        /*String s = null;
        ValidateCode.isCodeRight(reqeust, response, s);*/

    }
}
```

## jar包地址
https://github.com/ljwheyxy/validatecode/tree/master/out/artifacts/validatecode_jar

## 亦可直接引入文件

第一次整理代码至GitHub，请大神指教、、



