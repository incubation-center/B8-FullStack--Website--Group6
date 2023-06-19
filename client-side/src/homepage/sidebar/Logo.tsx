import React from "react";
import { BsFillPlusCircleFill } from "react-icons/bs";
import Favorite1 from "./Favorite1";
import Favorite2 from "./Favorite2";
import PolliFy from "../../assets/PolliFy.png";

const iconStyle = {
  color: "blue", // Customize color
  // backgroundColor: 'yellow',    // Customize background color
  fontSize: "30px", // Customize size
  opacity: 0.6, // Customize opacity
};

function Logo() {
  return (
    <div className="logo w-1/4 flex bg-white flex-col overflow-hidden">
      <div className="flex flex-col gap-y-10 mt-7 ml-5">
        <img src={PolliFy} alt="Profile 1" className="w-fit h-fit" />
        <div className="search-community-field relative w-full flex">
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
            className="py-4 mr-5 pl-10 border-2 border-gray-300 w-full bg-white rounded-full focus:outline-none focus:border-blue-500"
          />
        </div>
        <div className="create-community flex justify-start items-center gap-x-2">
          <BsFillPlusCircleFill style={iconStyle} />
          <h1>Create Community</h1>
        </div>
        <h1>Favorite</h1>
        <Favorite2 />
        <h1>Favorite</h1>
        <Favorite1 />
      </div>
    </div>
  );
}

export default Logo;
