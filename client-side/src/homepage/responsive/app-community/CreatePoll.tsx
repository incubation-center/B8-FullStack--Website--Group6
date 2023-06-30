import React from "react";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { AiFillTrophy } from "react-icons/ai";
import Poll1 from "./Poll1";
import Poll2 from "./Poll2";
import SelectFood from "./SelectFood";
import Rating from "./Rating";
import { useNavigate } from "react-router-dom";
import PolliFy from "../../../assets/PolliFy.png";
import { useQuery } from "react-query";

const trophyIcons = {
  color: "blue",
  opacity: 0.7,
  fontSize: "50px",
};
const API_URL = "http://13.251.127.67:8080/api/v1/poll/community/1";

function CreatePoll() {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate("/communitydetail");
  };

  const { isLoading, error, data } = useQuery("pollData", async () => {
    const response = await fetch(API_URL, {
      headers: {
        Authorization:
          "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ5YW1hIiwiZXhwIjoxNjg4MTg5Mzg0fQ.sFseVTDLYz6W5PD2NGjDatXD12i92fjoXjCcRKo6IR1uvLaOOWM0gFb2HmyOvn-kUc4Tk2wVIazeVZbhv-FH7w",
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
    <>
      {data && data.length > 0 ? (
        <div className="container flex flex-col bg-slate-200 gap-y-5 w-screen h-auto font-sans visible sm:visible md:visible lg:visible sm:bg-orange-200 md:bg-green-500 lg:bg-blue-200">
          <Poll1 />
          <Poll2 />
          <SelectFood />
          <Rating />
        </div>
      ) : (
        <div className="bg-white flex flex-col gap-y-8">
          <div className="logo-profile-createPoll flex justify-between items-center mt-5 ml-5 mr-5">
            <img
              src={PolliFy}
              alt="Profile 1"
              className="md:w-fit md:h-6 sm:w-fit sm:h-5 lg:invisible"
            />
            <p className="whitespace-normal font-sans font-bold text-sky-500 text-sm invisible sm:invisible md:invisible lg:visible">
              Welcome to the PitCool bro TED!
            </p>
            <div className="translate flex gap-x-3 items-center lg:hidden">
              <MdTranslate className="w-6 h-6" />
              <IoMdNotificationsOutline className="w-6 h-6" />
              <div className="relative cursor-pointer" onClick={handleClick}>
                <img
                  src={Avatar}
                  alt="Profile 1"
                  className="w-8 h-8 sm:w-6 sm:h-6 rounded-full mr-2 border-2 border-blue-500"
                />
                <span className="bottom-0 left-5 absolute  w-2.5 h-2.5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
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

            <button className="bg-blue-500 hover:bg-blue-700 text-white whitespace-nowrap rounded-full px-4 py-2">
              Create Poll
            </button>
            <AiFillTrophy
              className="mr-5 border-2 border-blue-500 rounded-full p-1"
              style={trophyIcons}
            />
          </div>
          <div>There is currently no poll within your community</div>
        </div>
      )}
    </>
  );
}

export default CreatePoll;
