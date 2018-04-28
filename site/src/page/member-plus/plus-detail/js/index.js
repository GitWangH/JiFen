
$(function(){
	var currIndex = parseInt(window.location.search.split('=')[1]);

	console.log(currIndex);

	var width = $('.tab').width();

	var item_width = width * 2/9 ;

	$('.tab-item').width(item_width);

	$('.tab-content').width(item_width*9);

	$('.content').addClass('hide').eq(currIndex).removeClass('hide');

	changeClass($('.tab-content'),$('.tab-content').children().eq(currIndex),currIndex);

	$('.tab-item').click(function() {
		var index = $(this).index();

		changeClass($('.tab-content'),$(this),index);

		$('.content').removeClass('hide').addClass('hide').eq(index).removeClass('hide');
	})
	$('.tab').scrollLeft(currIndex*item_width);
	
})

function changeClass($parent,$item,index){


	var childs = $parent.children();

	for(let i=0; i<childs.length;i++){
		$(childs[i]).removeClass('select');
		$(childs[i]).children().eq(0).removeClass('tab-s'+(i+1));
		$(childs[i]).children().eq(1).removeClass('color_w');
	}

	$item.addClass('select');
	$item.children().eq(0).addClass('tab-s'+(index+1));
	$item.children().eq(1).addClass('color_w');
}