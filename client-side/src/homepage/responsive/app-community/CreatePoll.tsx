import React from "react";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
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
    const response = await fetch(API_URL, {
      headers: {
        Authorization:
          "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5YW1hIiwiZXhwIjoxNjg4NTUzMTQ0fQ.Lvxn-n2Ok9AnMSsP_2xUkLwYzXQKWJssn8w91RQMN85axeO2IR0RmZ_CTqraZDRtbzoujAe15J1PaHPdaxrQhg",
      },
    });

    if (!response.ok) {
      throw new Error("Failed to fetch poll data");
    }

    return response.json();
  });
  console.log("data", data);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.toString()}</div>;
  }

  return (
    <div className="bg-slate-200 gap-y-5 w-full lg:w-full  md:w-screen sm:w-full h-auto font-sans">
      <div className="bg-white flex flex-col gap-y-8">
        <div className="logo-profile-createPoll flex justify-between items-center mt-5 mx-5">
          <div className="logo-text">
            <p className="whitespace-normal font-sans font-bold hidden lg:block">
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
        <div className="create-poll w-full flex flex-row justify-around items-center gap-x-2 mb-5">
          <div className="search-field relative w-11/12 ml-5 flex">
            <div className="w-full absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <svg
                aria-hidden="true"
                className="w-5 h-5 text-gray-500 dark:text-gray-400"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                ></path>
              </svg>
            </div>
            <input
              type="text"
              placeholder="Search community"
              className="py-2 px-4 pl-8 border-2 b border-gray-300 w-full rounded-full focus:outline-none focus:border-blue-500"
            />
          </div>

          <button
            onClick={handleCreatePoll}
            className="bg-blue-500 hover:bg-blue-700 text-white whitespace-nowrap rounded-full px-4 py-2"
          >
            Create Poll
          </button>
          <AiFillTrophy
            className="mr-5 border-2 border-blue-500 rounded-full p-1"
            style={trophyIcons}
          />
        </div>
        {isCreatePollPopupOpen && <CreatePollPopup />}
      </div>
      <div className="flex flex-col gap-y-5 mt-5 ">
        <Poll1 />
        <Poll2 />
        <SelectFood />
        <Rating />
      </div>

      <div className="flex justify-center items-center">
        There is currently no poll within your community
      </div>
    </div>
  );
}

export default CreatePoll;
