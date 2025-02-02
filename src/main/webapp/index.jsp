<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>運賃計算システム</title>
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
					<table class="table table__form">
						<tr class="table__row">
							<th class="table__header--borderless">乗車駅</th>
							<td class="table__cell--borderless">
								<select name="boardingLine">
									<option value="JY">JR山手線</option>
									<option value="JU">JR宇都宮線</option>
									<option value="OH">小田急線</option>
								</select>
								<input class="input" type="text" name="boarding" placeholder="駅名を入力" />
							</td>
						</tr>
						<tr class="table__row">
							<th class="table__header--borderless">降車駅</th>
							<td class="table__cell--borderless">
								<select name="destinationLine">
									<option value="JY">JR山手線</option>
									<option value="JU">JR宇都宮線</option>
									<option value="OH">小田急線</option>
								</select>
								<input class="input" type="text" name="destination" placeholder="駅名を入力" />
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
							<span name="borading">東京</span>
							（<span name="boarding_line">JR山手線</span>）
						</td>
						<td class="table__cell">
							<span name="destination">高輪ゲートウェイ</span>
							（<span name="destination_line">JR山手線</span>）
						</td>
						<td class="table__cell">
							<span name="distance">5.9</span>km
						</td>
						<td class="table__cell">
							<span name="fare">170</span>円
						</td>
					</tr>
				</table>
			</section>
		</article>
	</main>
	<!-- ページフッタ領域 -->
	<jsp:include page="/pages/common/footer.jsp" />
	</div>
</body>
</html>