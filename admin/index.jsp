<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品销售管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
  color: white;
}
</style>
<!-- <script >
   function fun(){
           /* 失去焦点  */
           var vas = document.getElementById("na").value;
           if(vas == ""){
	          alert("姓名不能为空");
          }
                
       }
       function fun1(){
          /* 得到焦点 */
         
          var mi = document.getElementById("mi").value;
            if(mi == ""){
               alert("密码不能为空");
                }
       }

</script> -->
</head>

<body style="background: #278296">
<center></center>
<form method="post" action="${pageContext.request.contextPath }/Admin?method=dealwelcome" target="_parent" name='theForm' onsubmit="return validate_form(this)" >
 <div style="text-align:center">
   <em><span style="font-weight:bold;font-size:62px; color:blue">商品管理后台</span></em>
 
 </div>
  <table cellspacing="0" cellpadding="0" style="margin-top: 130px" align="center">
  
  <tr>
    <td style="padding-left: 50px">
      <table>
      <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" id="na" onblur="fun();"/></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" id="mi" onblur="fun1();"/></td>
      </tr>
      <tr><td>&nbsp;</td><td><input type="submit" value="进入管理中心" class="button" /></td></tr>
      </table>
    </td>
  </tr>
  </table>
</form>
<script language="JavaScript">
  document.forms['theForm'].elements['username'].focus();
  
  /**
   * 检查表单输入的内容
   */
  function validate()
  {
    var validator = new Validator('theForm');
    validator.required('username', user_name_empty);
    //validator.required('password', password_empty);
    if (document.forms['theForm'].elements['captcha'])
    {
      validator.required('captcha', captcha_empty);
    }
    return validator.passed();
  }
  

</script>
</body>