<%@ page language="java" contentType="text/html; charset=GB18030"
         pageEncoding="UTF-8"%>
<%
    String verifyCode = (String)session.getAttribute("VerifyCode");
    String command = request.getParameter("command");
    String anthCode = request.getParameter("authCode");

    if ("ok".equalsIgnoreCase(command)) {
        if (anthCode != null && anthCode.equalsIgnoreCase(verifyCode)) {
            System.out.println("测试成功！");
        } else{
            System.out.println("验证码错误！");
        }

    }

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script language=JavaScript>
        function change(field) {
            // 采用ajax的话可以使用这个代码，另外还需要从服务器端得到当前验证码
            var timenow = new Date().getTime();
            field.src="servlet/SimpleCaptchaServlet?d=" + timenow;

            // document.URL=location.href;
        }

    </script>
</head>
<body>
<form action="register">
    <input type="text" value="" >
    <input type="text" value="" >
    <input type="hidden" name="command" value="ok">
    <input id="verifyCode" name="verifyCode" type="text" size="6" maxlength="4">
    <img src="verifyCode" alt="验证码" onClick="change(this);">
    <input name="ok" type="submit" value="确定">
</form>
</body>
</html>
