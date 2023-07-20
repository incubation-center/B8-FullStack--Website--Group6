import React, { useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../utils/api";

function CommunityInvitationHandler() {
  const navigate = useNavigate();
  const { inviteLink } = useParams();

  let userLoggedIn = false;

  // Grabbing the access token
  const accessToken = localStorage.getItem("accessToken");
  if (accessToken !== "" || accessToken !== null) {
    userLoggedIn = true;
  }

  const validateToken = async () => {
    const accessToken = localStorage.getItem("accessToken");
    const headers = {
      Authorization: `${accessToken}`,
    };

    try {
      const response = await api.post(
        `/community/validate?token=${inviteLink}`,
        {},
        {
          headers,
        }
      );
      if (response.status === 200) {
        alert("success");
        if (response.data.isMember === false) {
          navigate(`/community/${response.data.id}`);
        } else {
          navigate(`/community/${response.data.id}`);
        }
      }
    } catch (error) {
      console.log("An error occured: ", error);
    }
  };

  useEffect(() => {
    // You may want to check if the user is logged in and get their authentication status here

    // Replace this with your authentication check

    if (userLoggedIn) {
      // Redirect the user to join the community if logged in
      // call an API to validate the inviteLink and join the community
      // Once the user is joined,  redirect them to the community page or dashboard

      validateToken();
    } else {
      // If the user is not logged in,  redirect them to the login page or show a message
      navigate(`/user/sign_in?redirect=${inviteLink}`);
    }
  }, []);

  return <div>Processing invitation...</div>;
}

export default CommunityInvitationHandler;
