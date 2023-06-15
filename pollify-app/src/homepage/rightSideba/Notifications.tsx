import React from "react";
import { FiSettings } from "react-icons/fi";
import { IoIosNotificationsOutline } from "react-icons/io";
import { MdOutlineGroups } from "react-icons/md";
import { CgToggleOff } from "react-icons/cg";

const notificationICons = {
  color: "gray",
};

function Notifications() {
  return (
    <div className="flex flex-col gap-y-5 mt-10">
      <div className="flex justify-start items-center ml-5 gap-x-5">
        <FiSettings className="w-6 h-6" style={notificationICons} />
        <h1>Setting</h1>
      </div>
      <div className="notifications flex justify-between items-center ml-5 mr-5">
        <div className="toggle-btn flex gap-x-4">
          <IoIosNotificationsOutline
            className="w-7 h-7"
            style={notificationICons}
          />
          <h1>Notification</h1>
        </div>
        <CgToggleOff className="w-7 h-7" style={notificationICons} />
      </div>
      <div className="flex justify-start items-center ml-5 gap-x-5">
        <MdOutlineGroups className="w-6 h-6" style={notificationICons} />
        <h1>Add Permission</h1>
      </div>
      <div className="border-t-2 border-gray-300 mt-10 w-5/6 ml-4"></div>
    </div>
  );
}

export default Notifications;
