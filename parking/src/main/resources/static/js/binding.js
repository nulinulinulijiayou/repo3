$(document).ready(function () {
	var n = 0;
	$(".License_plate").hide().first().show();
	$(".Shape").click(
		function () {
			n = $(this).index() - 1;
			$(".License_plate").hide().eq(n).show();
		}
	);
	$(".place_input").focus(
		function () {
			$(".place_container01").css("-webkit-transform", "translateY(0)")
			$(".place_container02").css("-webkit-transform", "translateY(400px)")
		}
	);
	$(".Botton").click(
		function () {
			$(".place_container01").css("transform", "translateY(400px)")
		}
	);
	$(".Botton01").click(
		function () {
			$(".place_container02").css("transform", "translateY(400px)")
		}
	);
	$(".number_input").focus(
		function () {
			$(".place_container02").css("transform", "translateY(0)")
			$(".place_container01").css("transform", "translateY(400px)")
		}
	);
	$(".place").click(
		function () {
			$(".place").removeClass("current")
			$(this).addClass("current")
		}
	);
	let aPlace = $('.place'),
		aInput = $("input"),
		inputLen = aInput.length,
		placeLen = aPlace.length,
		that = null,
		index = null;
	for (let i = 0; i < inputLen; i++) {
		aInput[i].onfocus = function () {
			// console.log(1);
			that = this;
			index = i;
		}
	}
	for (let i = 0; i < placeLen; i++) {
		aPlace[i].onclick = function () {
			// console.log(that);
			that.value = this.innerHTML;
			if (that.value.length >= 1) {
				let count = Math.min(++index, inputLen - 1);
				aInput[count].focus();
			}
		}
	}
	$(".Botton02").click(
		function () {
			$(".plate-box input").val("")
			var x = $(".plate-box .number_input").index()
			if (x != 0) {
				aInput[0].focus();
			}
		}
	);
});

