<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Gerenciamento de Salas Cronos</title>
        <link href="login/css/style.login.css" rel="stylesheet" type="text/css" media="screen" />
        <script language="JavaScript" src="login/js/jquery-1.8.2.js" type="text/javascript"></script>
        <script type="text/javascript">

            function carregando() {
                $('#loading').css('visibility', 'visible');
                $('.error').css('visibility', 'hidden');
            }

        </script>
        </head>

        <body>
        <div id="login">
          <div class="form">
            <form action="./LoginCerto" method="post" name="form_login" id="form_login">
              <div class="login">
                <input  type="text" maxlength="40" name="login" class="login" value="" />
                <input  type="password" maxlength="40" name="senha" class="senha"  value=""/>
              </div>
              <input type="submit" class="submit" value="" onclick="carregando();"/>
            </form>
            <div class="clear10"></div>
            <div id="loading">
              <div class="imagem" ></div>
              <div class="texto"> carregando</div>
            </div>
            
            <c:if test="${erroLogin == 'erro'}">	
            <div class="error">
              <div class="icon_erro"></div>
              <div class="msg_erro">Usuario ou senha incorretos</div>
            </div>
              <c:remove var="erroLogin"  scope="session"/>
             </c:if> 
            
          </div>
        </div>
</body>
</html>
