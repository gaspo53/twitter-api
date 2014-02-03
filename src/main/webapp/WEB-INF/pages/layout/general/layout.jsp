<%@ include file="/WEB-INF/pages/layout/common/taglibs.jsp"%>

<cw:set var="contextPath" value="${pageContext.request.contextPath}" scope="application" />

<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="global.page_title"></spring:message></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="${contextPath}/css/bootstrap-app-custom.css" rel="stylesheet">
        <script src="${contextPath}/js/jquery-1.10.2.min.js"></script>
        <script src="${contextPath}/js/bootstrap.min.js"></script>
        
    </head>
<body>
    <tiles:insertAttribute name="header" />
   
    <div class="container">
        <div class="main-container">
          <tiles:insertAttribute name="body" ignore="true" />
        </div>
    </div>
</body>
</html>

