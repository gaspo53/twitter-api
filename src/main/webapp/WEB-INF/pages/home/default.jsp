<%@ include file="/WEB-INF/pages/layout/common/taglibs.jsp"%>

<div class="page-header">
    <h1><spring:message code="layout.page_header.title"></spring:message></h1>
</div>
<p class="lead"><spring:message code="layout.page_header.help"></spring:message></p>

<form class="form" action="${contextPath}" method="get">
    <fieldset>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group">
                    <input  type="text" class="form-control" id="usernameInput" name="username"
                            placeholder="@gaspo53" autocomplete="on" autofocus="autofocus" value="${username}">
                </div>
                <!-- /input-group -->
            </div>
            <button type="submit" class="ganar-decada btn btn-primary btn-lg btn-block top-block">
                <spring:message code="labels.query"></spring:message>
            </button>
        </div>
        <!-- /.row -->
    </fieldset>
</form>

 <cw:if test="${fn:length(handlerMethods) > 0}">
	<div class="alert alert-success block bold">
	    <spring:message code="layout.api.mappings.title"></spring:message>
	</div>      
	<cw:forEach items="${handlerMethods}" var="entry">
	  <div>
	    <span class="http-method">GET&nbsp;</span><a href="${contextPath}${entry}?token=${token}" target="_blank" class="mapping-link">${entry}</a>      
	  </div>
	</cw:forEach>
    <div class="alert alert-warning block">
        <spring:message code="messages.api.token.title" arguments="${token}"></spring:message>
    </div> 
</cw:if>
