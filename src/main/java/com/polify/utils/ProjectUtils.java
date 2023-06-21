package com.polify.utils;

public class ProjectUtils {
	
	public static final String API_URL = "/api/v1";
	
		public static final String REGISTER_USER_URL = "/register-user";
	
	public static final String REGISTER_USER_FULL_URL = ProjectUtils.API_URL + "/register-user";
	
	public static final String LOGIN_URL = ProjectUtils.API_URL + "/auth/login";

	public static final String COMMUNITY_MEMBERS_URL = ProjectUtils.API_URL + "/community_members";

	public static final String COMMUNITY_URL = ProjectUtils.API_URL + "/community";

	public static final String POLL_URL = ProjectUtils.API_URL + "/poll";

	public static final String POLL_OPTION_URL = ProjectUtils.API_URL + "/poll_option";

	public static final String POST_URL = ProjectUtils.API_URL + "/post";

	public static final String VOTE_URL = ProjectUtils.API_URL + "/vote";
	
	public static final String SUCCESS_STATUS = "SUCCESS";
	
	public static final String FAIL_STATUS = "FAIL";
	
	public static final String PENDING_STATUS = "PENDING";
	
	public static <T> T getValue(T value) {
		return value == null ? null : value;
	}

}
