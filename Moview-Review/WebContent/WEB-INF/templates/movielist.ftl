<center>
<form role="form" class="form-inline">
	<div class="form-group">
		<label for="name">Choose a genre</label>
		<select value = "" class="form-control" id = "genre">
			<#list genres as item>
				<option>${item}</option>
			</#list>
		</select>
	</div>
	<button type="button" class="btn btn-primary" id = "showMovies">Submit</button>
</form>
</center>
<script>
	$("#showMovies").click(function(){
		$.post("ProcessData",{command:"getMoviesByGenre", genre:$("#genre").val()},function(result){
		    $("#willbedone1").html(result);
		});
	});
</script>
