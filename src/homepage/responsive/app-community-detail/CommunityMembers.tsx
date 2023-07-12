import React, { useState, useEffect } from "react";
import api from "../../../utils/api";
import { User } from "../../../types/redux/community";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../../redux/store";
import { setCommunityMembers } from "../../../redux/slices/Community";
import Avatar from "../../../assets/userProfile/Avatar.png";
import Avatar1 from "../../../assets/userProfile/Avatar-1.png";
import Avatar2 from "../../../assets/userProfile/Avatar-2.png";
import Avatar3 from "../../../assets/userProfile/Avatar-3.png";
import Avatar4 from "../../../assets/userProfile/Avatar-4.png";
import Avatar5 from "../../../assets/userProfile/Avatar-5.png";
import Avatar6 from "../../../assets/userProfile/Avatar-6.png";
import Avatar7 from "../../../assets/userProfile/Avatar-7.png";
import Avatar8 from "../../../assets/userProfile/Avatar-8.png";
import Avatar9 from "../../../assets/userProfile/Avatar-9.png";

function CommunityMembers() {
  const dispatch = useDispatch();

  const { inCommunityId, communityMembers } = useSelector(
    (state: RootState) => state.community
  );

  useEffect(() => {
    const communityId = localStorage.getItem("communityId");
    const fetchCommunityMembers = async () => {
      const accessToken = localStorage.getItem("accessToken");
      const headers = {
        Authorization: `${accessToken}`,
      };
      if (inCommunityId !== 0) {
        try {
          const response = await api.get(
            `/community_members/community/${inCommunityId}`,
            {
              headers,
            }
          );
          if (response.status === 200) {
            const communityMembersData = response.data.user;
            dispatch(setCommunityMembers(communityMembersData));
          }
        } catch (error) {
          console.log("An error occured: ", error);
        }
      }
    };

    fetchCommunityMembers();
  }, [inCommunityId]);

  // console.log("communityMembersData", communityMembers);

  return (
    <div className="profile flex flex-col h-80 px-4">
      {communityMembers.map((user: any, index: any) => {
        return (
          <React.Fragment key={index}>
            <div className="profile-line flex flex-col justify-center border-b border-gray-300 py-4">
              <div className="flex items-center">
                <img
                  src={Avatar1}
                  alt="Profile 1"
                  className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
                />
                <h1>{user.username}</h1>
              </div>
            </div>
          </React.Fragment>
        );
      })}
    </div>
  );
}

export default CommunityMembers;
