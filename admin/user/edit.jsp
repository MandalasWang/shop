<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/Admin?method=submitedit&uid=${user.uid}" method="post">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="6"
						height="26">
						<strong><STRONG>用户管理</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
				<td width="8%" align="left" bgColor="#f5fafe" >
						用户名：
					</td>
					<td  bgColor="#ffffff" width="10%" >
						<input type="text" name="username"  id="userAction_save_do_logonName" value="${user.username }" />
					</td>
					<td width="8%" align="center" bgColor="#f5fafe"  >
						账户密码：
					</td>
					<td  bgColor="#ffffff" width="10%">
						<input type="text" name="password" id="userAction_save_do_logonpassword" value="${user.password }"/>
					</td>
					<td width="8%" align="right" bgColor="#f5fafe" >
						是否授权登录后台：
					</td>
					<td  bgColor="#ffffff"  width="10%">
						<select name="state">
						<option value="1">是</option>
			            <option value="0">否</option>
			            </select>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="6">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>