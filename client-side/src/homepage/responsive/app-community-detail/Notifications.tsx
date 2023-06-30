import React from "react";
import { FiSettings } from "react-icons/fi";
import { IoIosNotificationsOutline } from "react-icons/io";
import { MdOutlineGroups } from "react-icons/md";
import { CgToggleOff, CgToggleOn } from "react-icons/cg";
import AddPermission from "../../popup/AddPermission";

const notificationIcons = {
  color: "gray",
};

function Notifications() {
  const [isOpen, setIsOpen] = React.useState(false);
  const [isToggleOn, setIsToggleOn] = React.useState(false);

  const openModal = () => {
    setIsOpen(true);
  };

  const closeModal = () => {
    setIsOpen(false);
  };

  const handleToggle = () => {
    setIsToggleOn((prevState) => !prevState);
  };

  return (
    <div className="flex flex-col gap-y-5 mt-5">
      <div className="flex justify-start items-center ml-5 gap-x-5">
        <FiSettings className="w-6 h-6" style={notificationIcons} />
        <h1>Setting</h1>
      </div>
      <div className="notifications-toggle flex justify-between  items-center ml-5 mr-5">
        <div className="notifications flex gap-x-4 items-center">
          <IoIosNotificationsOutline
            className="w-7 h-7"
            style={notificationIcons}
          />
          <h1>Notification</h1>
        </div>
        <div
          className="toggle-btn"
          onClick={handleToggle}
          style={notificationIcons}
        >
          {isToggleOn ? (
            <CgToggleOn color="blue" className="w-6 h-fit" />
          ) : (
            <CgToggleOff className="w-6 h-fit" />
          )}
        </div>
      </div>
      <div
        className="flex justify-start items-center ml-5 gap-x-5 cursor-pointer"
        onClick={openModal}
      >
        <MdOutlineGroups className="w-6 h-6" style={notificationIcons} />
        <h1>Add Permission</h1>
      </div>
      <AddPermission isOpen={isOpen} onClose={closeModal} />
      <div className="border border-gray-100 mt-5 w-full"></div>
    </div>
  );
}

export default Notifications;
