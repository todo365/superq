function insert()
{
	

}

function login() {
	var mydata = {
		"username" : "111",
		"nickname" : "222"
	};
	alert(mydata);
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'http://127.0.0.1/zhaoping/api/accc',
		// processData : false,
		dataType : 'json',
		async : true,
		data : JSON.stringify(mydata),
		success : function(data) {
			alert(data);
		},
		error : function() {
			alert('Err...');
		}
	})
}

function login2() {
	$.get('http://127.0.0.1/zhaoping/api/user/login/18610482001/123456', function() {
		alert($.cookie("phone"))
	});
	// var mydata = {
	// "username":"111",
	// "nickname":"222"
	// };
	// alert(mydata);
	// $.ajax({
	// type : 'GET',
	// contentType : 'application/json',
	// url : 'http://b.test.com/zhaoping/api/getu',
	// processData : false,
	// dataType : 'json',
	// success : function(data) {
	// alert(data);
	// },
	// error : function() {
	// alert('Err...');
	// }
	// // })
}
