<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>${param.title}</title>
	<link rel="stylesheet" href="/fare-calc-sys/assets/css/style.css">
</head>
<body class="content">
	<div class="conteｎt__wrapper">
	<jsp:include page="/pages/common/header.jsp" />
	<main class="main">
		${content}
	</main>
	<jsp:include page="/pages/common/header.jsp" />
	</div>
</body>
</html>