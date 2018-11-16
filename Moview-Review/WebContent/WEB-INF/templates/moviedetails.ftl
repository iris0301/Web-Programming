<H1 ALIGN="CENTER">
	${movie.name}
</H1>
<div style="position: relative;left:20%; top: 20px;">
<h2>Movie Info</h2>
<UL id = "movie_info" movieid='${movie.id}'>
	<li><B>Year</B>: ${movie.year}</li>
	<li><B>Rank</B>: ${movie.rank}</li>
	<li><B>Director</B>: 
		<#list directors as director>
			${director} 
		</#list>
	</li>
</UL>
<h2>Reviews</h2>
<UL>
	<#list reviews as review>
		<li>${review.content} <a onclick = deleteReview('${review.id}')>delete</a></li>
	</#list>
</UL>

<input id = "review" type="text"/> 
<button type="button" id = "add_review">Add a review</button>
</div>
<script>
	$("#add_review").click(function(){
		$.post("ProcessData",{command:"addReview", movieId:$("#movie_info").attr("movieid"),
								review:$("#review").val()},
			function(result){
		    	alert(result);
		    	showDetails($("#movie_info").attr("movieid"));
			});
	});
	function deleteReview(str) {
		$.post("ProcessData",{command:"deleteReview", movieId:$("#movie_info").attr("movieid"),
								id:str},
			function(result){
		    	alert(result);
		    	showDetails($("#movie_info").attr("movieid"));
			});
	}
</script>