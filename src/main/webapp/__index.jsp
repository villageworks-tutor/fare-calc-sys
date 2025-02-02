<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<jsp:include page="./pages/common/layout.jsp">
	<jsp:param value="title" name="乗車運賃計算システム"/>
	<jsp:param name="content">
		<jsp:attribute name="value">
			<article class="article">
				<section class="section section__input">
					<h2 class="section__title">乗車情報入力</h2>
					<ul class="list list__message--error">
						<li>指定された乗車駅は指定された路線に見つかりませんでした。</li>
						<li>指定された降車駅は指定された路線に見つかりませんでした。</li>
					</ul>
					<form class="form" action="fare-calc-sys/calc" method="get">
						<table class="table table__form">
							<tr class="table__row">
								<th class="table__header--borderless">乗車駅</th>
								<td class="table__cell--borderless">
									<select name="line">
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
									<select name="line">
										<option value="JY">JR山手線</option>
										<option value="JU">JR宇都宮線</option>
										<option value="OH">小田急線</option>
									</select>
									<input class="input" type="text" name="distination" placeholder="駅名を入力" />
								</td>
							</tr>
							<tr>
								<td colspan="2" class="table__cell--borderless table__cell--submit">
									<button class="input__button">運賃計算</button>
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
								（<span name="line">JR山手線</span>）
							</td>
							<td class="table__cell">
								<span name="distination">高輪ゲートウェイ</span>
								（<span name="line">JR山手線</span>）
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
		</jsp:attribute>
	</jsp:param>
</jsp:include>
