function toLogin(login_type) {
	form1.action = "oauth2/login?loin_type="+login_type;
	form1.submit();
}
