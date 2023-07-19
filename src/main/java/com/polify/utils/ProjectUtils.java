package com.polify.utils;

import com.polify.entity.Poll;

public class ProjectUtils {
	
	public static final String API_URL = "/api/v1";

    public static final String REGISTER_USER_URL = "/register-user";
    public static final String VERIFY_USER_URL = "/verify-user";
    public static final String VERIFY_FORGOT_PASSWORD = "/verify-forgot-password";
    public static final String FORGOT_PASSWORD_URL = "/forgot-password";
    public static final String RESET_FORGOT_PASSWORD_URL = "/reset-password";


    public static final String VERIFY_USER_FULL_URL = ProjectUtils.API_URL+ "/verify-user";
    public static final String REGISTER_USER_FULL_URL = ProjectUtils.API_URL + "/register-user";
    public static final String FORGOT_PASSWORD_USER_FULL_URL = ProjectUtils.API_URL + "/forgot-password";
    public static final String VERIFY_FORGOT_PASSWORD_FULL_URL = ProjectUtils.API_URL + "/verify-forgot-password";
    public static final String RESET_FORGOT_PASSWORD_FULL_URL = ProjectUtils.API_URL +"/reset-password";

	public static final String LOGIN_URL = ProjectUtils.API_URL + "/auth/login";

	public static final String COMMUNITY_MEMBERS_URL = ProjectUtils.API_URL + "/community_members";

	public static final String COMMUNITY_URL = ProjectUtils.API_URL + "/community";

	public static final String POLL_URL = ProjectUtils.API_URL + "/poll";

	public static final String POLL_OPTION_URL = ProjectUtils.API_URL + "/poll_option";

	public static final String POST_URL = ProjectUtils.API_URL + "/post";

	public static final String VOTE_URL = ProjectUtils.API_URL + "/vote";

    public static final String USER_URL = ProjectUtils.API_URL + "/user";
	
	public static final String SUCCESS_STATUS = "SUCCESS";
	
	public static final String FAIL_STATUS = "FAIL";
	
	public static final String PENDING_STATUS = "PENDING";
    public static final String MAIL_PASSWORD = "zlnsnijqyuzlhszs";
    public static final String SENDER_EMAIL = "pollify.app@gmail.com";
    public static final String FILE_URL = "http://52.220.220.40:8081";
    public static final String STREAM_URL = ProjectUtils.POLL_URL + "/stream/**";
    public static final String CLIENT_INVITE_URL = "https://newbootcamp.vercel.app/join/community/";

	public static <T> T getValue(T value) {
		return value == null ? null : value;
	}

}
