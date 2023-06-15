import React from "react";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { BsQrCode } from "react-icons/bs";
import Notifications from "./Notifications";
import UserProfile from "./UserProfile";
import Ellipse1007 from "../../assets/community/Ellipse1007.png";
import Avatar from "../../assets/Avatar.png";

const qrIcons = {
  color: 'blue', 
  opacity: 0.7,
}

function CommunityProfile() {
  return (
    <>
      <div className="flex flex-col w-1/4 overflow-hidden bg-white">
        <div className="flex gap-x-7 justify-center items-center mt-9">
          <MdTranslate className="w-6 h-6" />
          <IoMdNotificationsOutline className="w-6 h-6" />
          <h1 className="font-bold">TED</h1>

          <div className="relative">
            <img
              src={Avatar}
              alt="Profile 1"
              className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
            />
            <span className="bottom-0 left-7 absolute  w-3.5 h-3.5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
          </div>
        </div>
        <div className="Moringa flex flex-col  gap-y-6 mt-10 justify-center items-center">
          <img
            src={Ellipse1007}
            alt="moringa"
            className="w-16 h-16 rounded-full mr-2 border-2 border-blue-500"
          />
          <h1 className="text-[15px]">Moringa</h1>
          <div className="flex justify-center items-center gap-x-6">
            <BsQrCode className="w-8 h-8" style={qrIcons}/>
            <button
              id="copyButton"
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-lg"
            >
              Copy Link
            </button>
          </div>
        </div>
        <div className="border-t-2 border-gray-300 mt-10 w-5/6 ml-4"></div>
        <Notifications />
        <UserProfile />
      </div>
    </>
  );
}

export default CommunityProfile;
