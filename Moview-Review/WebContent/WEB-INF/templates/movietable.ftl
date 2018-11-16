<center>
<div class="container">
	<div class="row clearfix" >
		<div id = "table_div" class= "col-md-12 column">
			<table class="table table-condensed" align = "center">
				<thead>
					<tr >
						<th>id</th>
						<th>name</th>
						<th>year</th>
						<th>rank</th>
						<th>operate</th>
					</tr>
				</thead>
				<tbody>
					<#list movies as movie>
						<tr>
							<td>${movie.id}</td>
							<td>${movie.name}</td>
							<td>${movie.year}</td>
							<td>${movie.rank}</td>
							<td>
								<a class="btn btn-default" onclick = "showDetails('${movie.id}')" role="button">Details</a>
								<a class="btn btn-default" onclick = "deleteMovie('${movie.id}')" role="button">Delete</a>
							</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</div>
</div>
</center>
<script>
	function showDetails(str) {
		$.post("ProcessData",{command:"showDetails",id:str},function(result){
		    $("#willbedone0").html(result);
		    $("#willbedone1").html("");
		    $("#home").attr("class","");
		});
	}
	function deleteMovie(str) {
		$.post("ProcessData",{command:"deleteMovie",id:str},function(result){
		    alert(result);
		    $("#showMovies").click();
		});
	}
</script>