import React from "react";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline } from "react-icons/io";
import { BsQrCode } from "react-icons/bs";

function SidebarRight() {
  return (
    <>
      <div className="flex flex-col bg-slate-100">
        <div className="flex">
          <MdTranslate />
          <IoMdNotificationsOutline />
          <h1>TED</h1>
          <img
            src="profile1.png"
            alt="Profile 1"
            className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
          />
        </div>
        <div className="Moringa flex-col justify-center items-center">
          <img
            src="../assets/images/moringa.png"
            alt="moringa"
            className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
          />
          <h1>Moringa</h1>
          <div className="flex">
            <BsQrCode />
            <button
              id="copyButton"
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            >
              Copy Link
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default SidebarRight;
