//点击一行时选中此行的checkbox
function selectCheckbox(e){
	var checkboxObj = $(e).find("input[name='grid_checkbox']");
	var value = checkboxObj.is(":checked");
	if(value == true){
		checkboxObj.prop("checked",false);
		$(e).css("background", "");
	}else{
		checkboxObj.prop("checked",true);
		$(e).css("background", "#fbec87");
	}
}
