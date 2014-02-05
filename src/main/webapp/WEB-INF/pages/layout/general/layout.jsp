<%@ include file="/WEB-INF/pages/layout/common/taglibs.jsp"%>

<cw:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="application" />

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="global.page_title"></spring:message></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${contextPath}/css/bootstrap-app-custom.css"
	rel="stylesheet">
<script src="${contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/twitter-api.js"></script>

</head>
<body>
	<tiles:insertAttribute name="header" />
	<a href="https://github.com/gaspo53/twitter-api" target="_blank">
		<img
			style="position: absolute; top: 50px; right: 0; border: 0;"
			src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"
			alt="Fork me on GitHub">
	</a>

	<div class="container">
		<div class="main-container">
			<tiles:insertAttribute name="body" ignore="true" />
		</div>
	</div>
</body>
</html>

