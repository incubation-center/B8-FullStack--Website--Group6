import React, { useEffect, useState } from "react";
import PolliFy from "../../../assets/PolliFy.png";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline, IoIosArrowBack } from "react-icons/io";
import QrCode from "../../../assets/icons/qr-code.svg";
import NotificationIcon from "../../../assets/icons/notification.svg";
import Ellipse1007 from "../../../assets/community/Ellipse1007.png";
import { BsQrCode } from "react-icons/bs";
import CommunitySetting from "./CommunitySetting";
import CommunityMembers from "./CommunityMembers";
import PopupModal from "../../popup/PopupModal";
import { setIsCommunityProfileOpen } from "../../../redux/slices/Community";
import { useLocation } from "react-router-dom";
import Setting from "../../../assets/icons/icons8-delete.svg";
import api from "../../../utils/api";
import { setUserData } from "../../../redux/slices/Community";
import { Dispatch } from "redux";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../../redux/store";
import { setCommunityMembers } from "../../../redux/slices/Community";
import axios from "axios";
interface RoleData {
  email: string;
  role: string;
}

function CommunityProfile() {
  const dispatch = useDispatch();
  const [hasAccess, setHasAccess] = useState(false);
  const { isCommunityProfileOpen } = useSelector(
    (state: RootState) => state.community
  );
  const { id, community } = useSelector(
    (state: RootState) => state.userCommunity
  );
  const { communityMembers } = useSelector(
    (state: RootState) => state.community
  );
  const [role, setRole] = useState<RoleData[]>([]);
  const communityId = localStorage.getItem("communityId");
  const user = localStorage.getItem("user-info");

  const userObject = JSON.parse(user !== null ? user : "");
  const userEmail = userObject.email;
  console.log(userEmail);

  useEffect(() => {
    const fetchCommunityMembers = async () => {
      const accessToken = localStorage.getItem("accessToken");
      const headers = {
        Authorization: `${accessToken}`,
      };
      // if (inCommunityId !== 0) {
      try {
        const response = await api.get(
          `/community_members/community/${communityId}`,
          {
            headers,
          }
        );
        if (response.status === 200) {
          const communityMembersData = response.data.user;

          // console.log("me", communityMembersData);

          console.log("role", role);
          setRole(communityMembersData);
        }
      } catch (error) {
        console.log("An error occured: ", error);
      }
      // }
    };

    fetchCommunityMembers();
  }, [communityId]);

  const { username } = useSelector((state: RootState) => state.userCommunity);
  const location = useLocation();
  const communityName = location.state?.communityName || "";
  const communityImage = location.state?.communityImage || "";

  const currentProfile = communityMembers.find((member) => member.id === id);

  const inActiveCommunity =
    communityId !== null
      ? community.find((obj) => obj.id.toString() === communityId.toString())
      : null;

  useEffect(() => {
    if (currentProfile?.role === "admin" || currentProfile?.role === "owner") {
      setHasAccess(true);
    } else {
      setHasAccess(false);
    }
  }, [hasAccess, communityMembers]);

  const handleBackToPoll = () => {
    dispatch(setIsCommunityProfileOpen(false));
  };
  const owner = role.filter((role: any) => role === "owner");
  console.log("owner", owner);
  const handleLeaveAndDelete = async () => {
    // Implement the logic for deleting the specific community here
    const accessToken = localStorage.getItem("accessToken");

    const UserData: any = role.find((item: any) => item.email === userEmail);
    console.log("role", UserData);

    const userRole = UserData?.role;
    console.log("Role:", userRole);

    const headers = {
      Authorization: `${accessToken}`,
      "Content-Type": "application/json", // Add this header to indicate JSON data
    };

    if (userRole === "owner") {
      try {
        const response = await fetch(
          `https://pollify.appifier.online/api/v1/community/delete/${communityId}`,
          {
            method: "POST",
            headers,
          }
        );

        if (response.status === 200) {
          // Handle successful deletion
          console.log("Community deleted successfully!");
          setTimeout(() => {
            window.location.reload();
          }, 500);
        } else {
          // Handle errors, if any
          console.log("Failed to delete the community.");
        }
      } catch (error) {
        console.log("An error occurred while deleting the community: ", error);
      }
    } else {
      // Implement the logic for leaving the community as a poller here
      try {
        const response = await fetch(
          `https://pollify.appifier.online/api/v1/community_members/leave/community/${communityId}`,
          {
            method: "POST",
            headers,
          }
        );

        if (response.status === 200) {
          // Handle successful deletion
          console.log("Community deleted successfully!");
          setTimeout(() => {
            window.location.reload();
          }, 500);
        } else {
          // Handle errors, if any
          console.log("Failed to delete the community.");
        }
      } catch (error) {
        console.log("An error occurred while deleting the community: ", error);
      }
    }
  };
  return (
    <div
      className={` ${
        isCommunityProfileOpen ? "w-full" : "w-0"
      } absolute z-10 duration-300 right-0 lg:relative font-sans bg-white lg:w-2/6 h-full lg:flex lg:flex-col overflow-hidden`}
    >
      <div className="logo-profile-createPoll flex justify-between lg:justify-end items-center mt-5 ml-5 mr-5">
        <div
          className="flex items-center gap-x-2 lg:hidden cursor-pointer"
          onClick={handleBackToPoll}
        >
          <IoIosArrowBack className="w-6 h-6 text-blue-custom" />
          <span className="text-lg">Polls</span>
        </div>
        <div className="translate flex gap-x-3 items-center lg:justify-end">
          <MdTranslate className="w-6 h-6" />
          <div className="pr-2">
            <img
              className="w-6 h-6 text-gray-500"
              src={NotificationIcon}
              alt="Notification Icon"
            />
          </div>
          <p className="lg:text-[17px] lg:font-sans lg:font-bold">{username}</p>
          <div className="relative">
            {/* <img
              src={Avatar}
              alt="Profile 1"
              className="w-10 h-10 rounded-full mr-2 border-2 border-blue-500"
            /> */}
            <div className="flex justify-center items-center w-9 h-9 rounded-full mr-2 border border-blue-500">
              <span className="font-bold text-xl uppercase">
                {username.slice(0, 2)}
              </span>
            </div>
            <span className="bottom-1 left-7 absolute w-3 h-3 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
          </div>
        </div>
      </div>
      <div className="border border-gray-200 mt-8 lg:hidden"></div>
      {/* <div className="Moringa flex flex-col gap-y-3 mt-12 justify-center items-center">
        <img
          src={communityImage}
          alt="moringa"
          className="w-16 h-16 rounded-full mr-2 border border-blue-custom cursor-pointer"
        />
        <h1 className="text-[15px]">{communityName}</h1>
        <div className="flex justify-center items-center gap-x-4">
          <div className="flex justify-center items-center w-10 h-10 p-1 bg-blue-100 rounded-lg">
            <img className="w-full h-full" src={QrCode} alt="QR Code Icon" />
          </div>
        </div>
      </div> */}

      {communityId && (
        <div className="">
          <div className="Moringa flex flex-col gap-y-3 mt-12 justify-center items-center">
            <div className="flex flex-col items-center gap-y-2">
              {inActiveCommunity?.image === null ? (
                <div className="flex justify-center items-center w-16 h-16 rounded-full border border-blue-500 cursor-pointer">
                  <span className="font-bold text-4xl uppercase">
                    {inActiveCommunity?.name[0]}
                  </span>
                </div>
              ) : (
                <img
                  src={inActiveCommunity?.image}
                  alt="moringa"
                  className="w-16 h-16 rounded-full object-cover border border-blue-custom cursor-pointer"
                />
              )}
              <h1 className="text-lg text-center">{inActiveCommunity?.name}</h1>
            </div>
            <div className="flex justify-center items-center gap-x-4">
              <div className="flex justify-center items-center w-10 h-10 p-1 bg-blue-100 rounded-lg">
                <img
                  className="w-full h-full"
                  src={QrCode}
                  alt="QR Code Icon"
                />
              </div>
              <button
                id="copyButton"
                type="button"
                className="bg-blue-custom hover:opacity-70 text-white font-bold py-2 px-5 rounded-lg"
              >
                Copy Link
              </button>
            </div>
          </div>
          {/* <PopupModal isOpen={isOpen} onClose={closeModal} /> */}
          <div className="px-4 mb-3">{hasAccess && <CommunitySetting />}</div>
          <div
            className="flex justify-start items-center gap-x-4 cursor-pointer text-red-500"
            onClick={() => handleLeaveAndDelete()}
          >
            <img
              className="w-6 h-6 text-gray-500"
              src={Setting}
              alt="Setting Icon"
            />
            <h1>
              {role.find((item) => item.email === userEmail)?.role === "owner"
                ? "Leave and Delete"
                : "Leave"}
            </h1>
          </div>
          <span className="pl-4 pt-4">Current Members</span>
          <div className="mr-1  overflow-hidden hover:overflow-auto community-scrolling">
            <CommunityMembers />
          </div>
        </div>
      )}
    </div>
  );
}

export default CommunityProfile;
