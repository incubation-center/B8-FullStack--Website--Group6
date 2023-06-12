import React from "react";
// import { AiOutlineSearch } from "react-icons/ai";
// import Poll1 from "./Poll1";

function Sidebar() {
  return (
    <>
        <div className="sidebar w-64 bg-gray-200 p-4 flex flex-col">
          <div className="logo">
            <img src="logo.png" alt="Logo" className="w-full h-auto" />
          </div>
          <div className="search-field mt-4">
            <input type="text" placeholder="Search" className="w-full p-2" />
          </div>
          <div className="create-community-button mt-4">
            <button className="w-full p-4 bg-blue-500 text-white border-none cursor-pointer">
              Create Community
            </button>
          </div>
          <div className="favorite-text mt-4">
            <h3>Favorites</h3>
          </div>
          <div className="favorite-profile">
            <img
              src="profile1.png"
              alt="Profile 1"
              className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
            />
            <img
              src="profile2.png"
              alt="Profile 2"
              className="w-8 h-8 rounded-full mr-2"
            />
            <img
              src="profile3.png"
              alt="Profile 3"
              className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
            />
            <img
              src="profile4.png"
              alt="Profile 4"
              className="w-8 h-8 rounded-full mr-2"
            />
          </div>
        </div>
      {/* <Poll1 /> */}
    </>
  );
}

export default Sidebar;
