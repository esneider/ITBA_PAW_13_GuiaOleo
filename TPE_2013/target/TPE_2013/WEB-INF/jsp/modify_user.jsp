<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="modify-user offset2 span8">
    <div class="offset2 span4 section-header clearfix">
        <h2>Modify your data</h2>
    </div>

	<div>
		<form class="modifyUserForm form-horizontal" action="modify_user" enctype="multipart/form-data" method="POST">

			<c:import url="user_data_fields.jsp" />

            <div class="control-group controls">
                <input class="btn btn-info" type="submit" value="Modify">
            </div>

		</form>
	</div>
</div>
