<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myNoteApp">
<head>
<script type="text/javascript" src="js/angular.min.1.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div ng-controller="myNoteCtrl">
		<h2>My Note</h2>
		<p>
			<textarea ng-model="message" cols="40" rows="10"></textarea>
		</p>
		<p>
			<button ng-click="save()">Save</button>
			<button ng-click="clear()">Clear</button>
		</p>
		<p>
			Number of characters left: <span ng-bind="left()"></span>
		</p>
	</div>
	<script src="bo/myNoteApp.js"></script>
	<script src="bo/myNoteCtrl.js"></script>
</body>
</html>