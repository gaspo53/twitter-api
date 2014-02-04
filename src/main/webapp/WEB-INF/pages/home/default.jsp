<%@ include file="/WEB-INF/pages/layout/common/taglibs.jsp"%>

<div class="page-header">
    <h1><spring:message code="layout.page_header.title"></spring:message></h1>
</div>
<p class="lead"><spring:message code="layout.page_header.help"></spring:message></p>

<div class="form">
    <fieldset>
        <div class="row">
            <div class="col-lg-12">
                <div class="form-group">
                    <input  type="text" class="form-control" id="usernameInput" 
                            placeholder="@gaspo53" autocomplete="on" autofocus="autofocus">
                </div>
                <!-- /input-group -->
            </div>
            <!-- /.col-lg-6 -->
            <button type="submit" class="ganar-decada btn btn-primary btn-lg btn-block top-block">
                <spring:message code="labels.query"></spring:message>
            </button>
        </div>
        <!-- /.row -->
    </fieldset>
</form>
<hr />
