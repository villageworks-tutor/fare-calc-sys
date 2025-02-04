<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="app.bean.Line"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>運賃算定システム</title>
	<!-- コンテキストパスの宣言：絶対パスでURLを表示するためにあらかじめ宣言しておく -->
	<base href="${pageContext.request.contextPath}/" />
	<link rel="stylesheet" href="assets/css/style.css" />
</head>
<body class="content">
	<div class="content__wrapper">
	<!-- ページヘッダ領域 -->
	<jsp:include page="/pages/common/header.jsp" />
	<!-- メインコンテンツ領域 -->
	<main class="main">
		<!-- ページコンテンツ領域 -->
		<article class="article">
			<section class="section section__input">
				<h2 class="section__title">乗車情報入力</h2>
				<c:if test="${not empty errors}">
				<ul class="list list__message--error">
					<c:forEach items="${errors}" var="error">
					<li>${error}</li>
					</c:forEach>
				</ul>
				</c:if>
				<form class="form" action="calc" method="get">
					<input type="hidden" name="action" value="calc" />
					<table class="table table__form">
						<tr class="table__row">
							<th class="table__header--borderless">乗車駅</th>
							<td class="table__cell--borderless">
								<select name="boardingLine">
									<c:forEach items="${lines}" var="line">
										<c:choose>
											<c:when test="${line.code eq ticket.boarding.code}">
												<option value="${line.code}" selected>${line.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${line.code}">${line.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input class="input" type="text" name="boarding" value="${boarding}"
								 placeholder="駅名を入力" />
							</td>
						</tr>
						<tr class="table__row">
							<th class="table__header--borderless">降車駅</th>
							<td class="table__cell--borderless">
								<select name="destinationLine">
									<c:forEach items="${lines}" var="line">
										<c:choose>
											<c:when test="${line.code eq ticket.destination.code}">
												<option value="${line.code}" selected>${line.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${line.code}">${line.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input class="input" type="text" name="destination" value="${destination}"
								 placeholder="駅名を入力" />
							</td>
						</tr>
						<tr>
							<td colspan="2" class="table__cell--borderless table__cell--submit">
								<!-- JavaScriptで実装のため、運賃計算の実装が済むまでコメントアウト
								<button class="input__button ul_small">乗車駅と降車駅を入れ替える</button>
								-->
								<button class="input__button ul_bold">運賃計算</button>
							</td>
						</tr>
					</table>
				</form>
			</section>
			<section class="section section__result">
				<c:if test="${not empty ticket}">
				<table class="table table__list">
					<caption class="table__caption">計算結果</caption>
					<tr class="table__row">
						<th class="table__header">乗車駅</th>
						<th class="table__header">降車駅</th>
						<th class="table__header">乗車距離</th>
						<th class="table__header">乗車運賃</th>
					</tr>
					<tr class="table__row">
						<td class="table__cell">
							<span id="borading">${ticket.boarding.name}</span>
							（<span id="boarding_line">${boardingLineName}</span>）
						</td>
						<td class="table__cell">
							<span id="destination">${ticket.destination.name}</span>
							（<span id="destination_line">${destinationLineName}</span>）
						</td>
						<td class="table__cell">
							<span id="distance">${ticket.distance}</span>km
						</td>
						<td class="table__cell">
							<span id="fare">${ticket.fare}</span>円
						</td>
					</tr>
				</table>
				</c:if>
			</section>
		</article>
	</main>
	<!-- ページフッタ領域 -->
	<jsp:include page="/pages/common/footer.jsp" />
	</div>
</body>
</html>