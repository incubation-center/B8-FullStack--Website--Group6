import React from "react";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { AiOutlineSearch } from "react-icons/ai";
import { AiFillTrophy } from "react-icons/ai";
import Poll1 from "./Poll1";
import Poll2 from "./Poll2";
import SelectFood from "./SelectFood";
import Rating from "./Rating";
import { RootState } from "../../../redux/store";
import { useNavigate } from "react-router-dom";
import CreatePollPopup from "../../popup/CreatePollPopup";
import { useDispatch, useSelector } from "react-redux";
import { openCreatePollPopup } from "../../../redux/slices/CreatePoll";
import { useQuery } from "react-query";
import { apiURL, accessToken } from "../../../config/config";
import PolliFy from "../../../assets/PolliFy.png";

const trophyIcons = {
  color: "blue",
  opacity: 0.7,
  fontSize: "50px",
};

const API_URL = "http://13.251.127.67:8080/api/v1/poll/community/1";

function CreatePoll() {
  const dispatch = useDispatch();
  const isCreatePollPopupOpen = useSelector(
    (state: RootState) => state.createPoll.isCreatePollPopupOpen
  );

  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/communitydetail");
  };

  const handleCreatePoll = () => {
    console.log("Create Poll Clicked");
    dispatch(openCreatePollPopup());
  };

  const { isLoading, error, data } = useQuery("pollData", async () => {
    const response = await fetch(`${apiURL}/api/v1/poll/community/1`, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    });

  //   if (!response.ok) {
  //     throw new Error("Failed to fetch poll data");
  //   }

    return response.json();
  });

  // if (isLoading) {
  //   return <div>Loading...</div>;
  // }

  // if (error) {
  //   return <div>Error: {error.toString()}</div>;
  // }

  return (
    <div className="bg-gray-100 w-full lg:w-full md:w-screen sm:w-full font-san h-screen">
      <div className="bg-white flex flex-col pl-10 pr-12 py-6 gap-y-7">
        <div className="logo-profile-createPoll flex justify-between items-center">
          <div className="logo-text">
            <p className="whitespace-normal text-lg hidden text-gray-700 lg:block">
              Welcome to the PitCool bro
              <span className="text-blue-custom font-bold">TED </span>!
            </p>
            <img src={PolliFy} alt="pollify" className="w-fit h-7 lg:hidden" />
          </div>
          <div className="translate flex gap-x-3 items-center lg:hidden">
            <MdTranslate className="w-6 h-6" />
            <IoMdNotificationsOutline className="w-6 h-6" />
            <div className="relative cursor-pointer" onClick={handleClick}>
              <img
                src={Avatar}
                alt="Profile 1"
                className="w-10 h-10 rounded-full mr-2 border-2 border-blue-500"
              />
              <span className="bottom-1 left-8 absolute  w-3 h-3 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
            </div>
          </div>
        </div>
        <div className="create-poll w-full flex flex-row justify-around items-center gap-x-3">
          <div className="search-field relative w-11/12 flex">
            <div className="w-full absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <AiOutlineSearch className="text-gray-400 w-5 h-5" />
            </div>
            <input
              type="text"
              placeholder="Search community"
              className="py-2 px-4 pl-9 border-2 border-gray-300 w-full rounded-full focus:outline-none focus:border-blue-500"
            />
          </div>

          <button
            onClick={handleCreatePoll}
            className="bg-blue-custom hover:opacity-70 text-white whitespace-nowrap rounded-full px-4 py-2.5"
          >
            Create Poll
          </button>
          <div className="border border-blue-custom rounded-full w-12 h-11">
            <AiFillTrophy className="w-full h-full text-blue-custom p-1" />
          </div>
        </div>
        {isCreatePollPopupOpen && <CreatePollPopup />}
      </div>
      <div className="flex flex-col gap-y-5 h-[87%] lg:h-[85%] overflow-auto py-10 pl-5 pr-4 home-scrolling">
        <Poll1 />
        <Poll1 />
        <Poll2 />
        <SelectFood />
        <Rating />
        <Poll1 />
        <Poll1 />
        <Poll2 />
        <SelectFood />
        <Rating />
      </div>

      {/* <div className="flex justify-center items-center">
        There is currently no poll within your community
      </div> */}
    </div>
  );
}

export default CreatePoll;
